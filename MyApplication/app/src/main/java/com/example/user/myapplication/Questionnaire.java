package com.example.user.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class Questionnaire extends AppCompatActivity {
    String t1;
    String t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        Bundle bundle = getIntent().getExtras();
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");

        for(int i=1 ; i<=3 ; i++)
        {
            String abc = "q"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID);
        }

        BootstrapButton qq=(BootstrapButton) findViewById(R.id.q1);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Questionnaire.this, ADL.class);
                Bundle bundle = new Bundle();
                bundle.putInt("題號",0);
                bundle.putInt("分數",0);
                bundle.putString("選擇","");
                bundle.putString("上一題","不需要");//
                bundle.putString("測試者id",t1);
                bundle.putString("受訪者id",t2);
                intent.putExtras(bundle);
                startActivity(intent);
                Questionnaire.this.finish();
            }
        });


        BootstrapButton qq2=(BootstrapButton) findViewById(R.id.q2);
        qq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Questionnaire.this, IADL2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("題號",0);
                //bundle.putInt("分數",0);
                bundle.putString("選擇","");
                bundle.putString("上一題","不需要");//
                bundle.putString("測試者id",t1);
                bundle.putString("受訪者id",t2);
                intent.putExtras(bundle);
                startActivity(intent);
                Questionnaire.this.finish();
            }
        });

        BootstrapButton qq3=(BootstrapButton) findViewById(R.id.q3);
        qq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Questionnaire.this, WHOQOL1.class);
                Bundle bundle = new Bundle();
                bundle.putInt("題號",0);
                //bundle.putInt("分數",0);
                bundle.putString("選擇","");
                bundle.putString("上一題","不需要");//
                bundle.putString("測試者id",t1);
                bundle.putString("受訪者id",t2);
                intent.putExtras(bundle);
                startActivity(intent);
                Questionnaire.this.finish();
            }
        });


        SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR);");
        Cursor resultSet = mydatabase.rawQuery("Select * from main where interviewerid = '"+t1+"' and testerid ='"+t2+"'",null);
        if(resultSet.getCount()!=0){
        resultSet.moveToFirst();
        do {
            if(resultSet.getString(3).equals("adl完成")){
                BootstrapButton ttt = (BootstrapButton) findViewById(R.id.q1);
                ttt.setVisibility(View.GONE);
            }
            if(resultSet.getString(4).equals("iadl完成")){
                BootstrapButton ttt = (BootstrapButton) findViewById(R.id.q2);
                ttt.setVisibility(View.GONE);
            }
            if(resultSet.getString(5).equals("whoqol完成")){
                BootstrapButton ttt = (BootstrapButton) findViewById(R.id.q3);
                ttt.setVisibility(View.GONE);
            }
        } while (resultSet.moveToNext());
        }
        resultSet.close();
        mydatabase.close();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(Questionnaire.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("測試者id",t1);
            bundle.putString("受訪者id",t2);
            intent.putExtras(bundle);
            startActivity(intent);
            Questionnaire.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }

    private void setupCustomStyle(int id) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(3.0f);
    }
}
