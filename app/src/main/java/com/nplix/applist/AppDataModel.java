package com.nplix.applist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by PK on 1/15/2018.
 */

public class AppDataModel extends AndroidViewModel {
    public AppLiveData getAppLiveData() {
        return appLiveData;
    }

    private AppLiveData appLiveData;
    public AppDataModel(@NonNull Application application) {
        super(application);
        appLiveData=new AppLiveData(application);
    }



    public class AppLiveData extends MutableLiveData<List<AppData>>{
        private PackageManager packageManager;
        List<AppData> appList;

        private final Context context;

        public AppLiveData(Context context){
            this.context=context;
            packageManager=context.getPackageManager();
            LoadAppInfo();
        }

        private void LoadAppInfo() {
            // Retrieve all known applications.
            List<ApplicationInfo> apps = packageManager.getInstalledApplications(
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            if (apps == null) {
                apps = new ArrayList<ApplicationInfo>();
            }
            appList = new ArrayList<AppData>(apps.size());

            for (int i=0; i<apps.size(); i++) {
                AppData entry = new AppData();
                entry.setPackageName(apps.get(i).packageName);
                entry.setAppName(apps.get(i).loadLabel(context.getPackageManager()).toString());
                entry.setFile(new File(apps.get(i).sourceDir));
                if(entry.getFile().exists()){
                  entry.setIcon(apps.get(i).loadIcon(context.getPackageManager()));
                }
                appList.add(entry);
                Log.d("LiveData",entry.getAppName());
            }
            setValue(appList);

        }

    }
}
