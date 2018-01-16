package com.nplix.applist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
AppDataModel appDataModel;
RecyclerView recyclerView;
AppsAdapter appsAdapter;
List<AppData> appsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appsAdapter=new AppsAdapter(appsList,this,false,false);
        recyclerView.setAdapter(appsAdapter);
        appDataModel= ViewModelProviders.of(this).get(AppDataModel.class);

        appDataModel.getAppLiveData().observe(this, new Observer<List<AppData>>() {
            @Override
            public void onChanged(@Nullable List<AppData> appData) {
                appsAdapter.setData(appData);
                appsAdapter.notifyDataSetChanged();
                Log.d("MainActivity:", "Data has updated");
            }
        });


    }
}
