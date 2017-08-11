package com.example.user.myapplication;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.IntentFilter;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.user.myapplication.NetWorkStateReceiver;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import static android.database.sqlite.SQLiteDatabase.openDatabase;


public class MainActivity extends AppCompatActivity {
    NetWorkStateReceiver netWorkStateReceiver;//網路狀態
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);

        for(int i=1 ; i<=4 ; i++)
        {
            String abc = "button"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID);
        }

        //setupCustomStyle(R.id.button);
        //setupCustomStyle(R.id.button2);
        //setupCustomStyle(R.id.button3);
        //setupCustomStyle(R.id.button4);

        BootstrapButton qq=(BootstrapButton) findViewById(R.id.button2);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Questionnaire.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });


    }
    //註冊廣播 監聽網路狀態
    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver(this);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        super.onResume();
    }
    //撤銷廣播
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        super.onPause();
    }
    private void setupCustomStyle(int id) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(3.0f);
    }


}