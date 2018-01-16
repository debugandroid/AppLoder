package com.nplix.applist;

import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * Created by PK on 1/15/2018.
 */

public class AppData {
    private String appName;
    private String packageName;
    private Drawable Icon;

    public File getFile() {
        return mFile;
    }

    public void setFile(File mFile) {
        this.mFile = mFile;
    }

    private File mFile;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return Icon;
    }

    public void setIcon(Drawable icon) {
        Icon = icon;
    }
}
