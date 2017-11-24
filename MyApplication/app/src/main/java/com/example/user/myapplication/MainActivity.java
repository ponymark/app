package com.example.user.myapplication;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.content.IntentFilter;
import android.widget.LinearLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.example.user.myapplication.NetWorkStateReceiver;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import static android.database.sqlite.SQLiteDatabase.openDatabase;


public class MainActivity extends AppCompatActivity {
    //NetWorkStateReceiver netWorkStateReceiver;//網路狀態

    String t1;
    String t2;
    String taskname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");
        BootstrapLabel bt1=(BootstrapLabel )findViewById(R.id.self);
        bt1.setText("測試者:"+t1+"\t\t受訪者:"+t2);


        for(int i=1 ; i<=4 ; i++)
        {
            String abc = "button"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID);
        }

        BootstrapButton qq1=(BootstrapButton) findViewById(R.id.button1);
        qq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, personaldata.class);
                Bundle bundle = new Bundle();
                bundle.putString("測試者id",t1);
                bundle.putString("受訪者id",t2);
                intent.putExtras(bundle);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        BootstrapButton qq=(BootstrapButton) findViewById(R.id.button2);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Questionnaire.class);
                Bundle bundle = new Bundle();
                bundle.putString("測試者id",t1);
                bundle.putString("受訪者id",t2);
                intent.putExtras(bundle);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });


        BootstrapButton qq4=(BootstrapButton) findViewById(R.id.button4);
        qq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BootstrapButton qq44=(BootstrapButton) v;
                if(qq44.getText().equals("可以上傳")){
                    qq44.setBootstrapBrand(DefaultBootstrapBrand.DANGER);//到時候改成跳出上傳進度視窗
                }
            }
        });


        SQLiteDatabase mydatabase2 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
        mydatabase2.execSQL("CREATE TABLE IF NOT EXISTS task(interviewerid VARCHAR,taskname VARCHAR);");
        Cursor resultSet2 = mydatabase2.rawQuery("Select * from task where interviewerid = '"+t1+"'",null);

        if(resultSet2.getCount()!=0){
            resultSet2.moveToFirst();
            do {
                taskname=resultSet2.getString(1);
            } while (resultSet2.moveToNext());
        }

        resultSet2.close();
        mydatabase2.close();


        if(taskname.equals("基本資料")) {
            qq.setVisibility(View.GONE);
            qq4.setVisibility(View.GONE);
            BootstrapButton qq3 = (BootstrapButton) findViewById(R.id.button3);
            qq3.setVisibility(View.GONE);
            LinearLayout lo1 = (LinearLayout) findViewById(R.id.lo1);
            LinearLayout lo2 = (LinearLayout) findViewById(R.id.lo2);
            lo2.setVisibility(View.GONE);
        }
        else if(taskname.equals("問卷")) {
            qq1.setVisibility(View.GONE);
            qq4.setVisibility(View.GONE);
            BootstrapButton qq3 = (BootstrapButton) findViewById(R.id.button3);
            qq3.setVisibility(View.GONE);
            LinearLayout lo1 = (LinearLayout) findViewById(R.id.lo1);
            LinearLayout lo2 = (LinearLayout) findViewById(R.id.lo2);
            lo1.setVisibility(View.GONE);
        }
        else if(taskname.equals("錄音")) {
            qq.setVisibility(View.GONE);
            qq1.setVisibility(View.GONE);
            qq4.setVisibility(View.GONE);
            BootstrapButton qq3 = (BootstrapButton) findViewById(R.id.button3);
            //qq3.setVisibility(View.GONE);
            LinearLayout lo1 = (LinearLayout) findViewById(R.id.lo1);
            LinearLayout lo2 = (LinearLayout) findViewById(R.id.lo2);
            lo2.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, list.class);

            Bundle bundle = new Bundle();

            bundle.putString("測試者id",t1 );//
            //bundle.putString("受訪者id",t2);//
            intent.putExtras(bundle);
            startActivity(intent);
            MainActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    /*
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

    */


    private void setupCustomStyle(int id) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(3.0f);
    }


}
