package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ADLANS extends AppCompatActivity {
    String t1;
    String t2;
    String [] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adlans);

        Bundle bundle = getIntent().getExtras();

        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.adlanswer);
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");


        int cou=0;
        String temp="";
        temp=bundle.getString("選擇");
        result=temp.split(" ");
        for(int y=0;y<result.length;y++){
            if((y==0||y==3||y==6)&&(result[y].equals("5")||result[y].equals("0")))
            {
                cou++;
            }
            else if((y==7||y==8)&&(result[y].equals("10")||result[y].equals("5")||result[y].equals("0")))
            {
                cou++;
            }
            else if(y==1&&result[y].equals("0"))
            {
                cou++;
            }
        }
        if(cou<3&&cou>0)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":輕度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        }
        else if(cou<5&&cou>2)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":中度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
        }
        else if(cou>4)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":重度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }
        else
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":無失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.INFO);
        }

        BootstrapButton adlsave = (BootstrapButton)findViewById(R.id.adlsave);
        adlsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){



                SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS adl(interviewerid VARCHAR,testerid VARCHAR,progress VARCHAR,A1 VARCHAR,A2 VARCHAR,A3 VARCHAR,A4 VARCHAR, A5 VARCHAR,A6 VARCHAR,A7 VARCHAR,A8 VARCHAR,A9 VARCHAR,A10 VARCHAR,generateddate VARCHAR);");

                ContentValues contentValues = new ContentValues();
                contentValues.put("interviewerid", t1);
                contentValues.put("testerid", t2);
                contentValues.put("progress", "已完成");

                for(int i=0;i<result.length;i++){
                    contentValues.put("A"+(i+1), result[i]);
                }



                String date =(new SimpleDateFormat("yyyy/MM/dd")).format(new Date());
                contentValues.put("generateddate", date);

                mydatabase.insert("adl", null, contentValues);

                mydatabase.close();
                //寫檔
                //寫入另一個資料表adl 和一開始main活動資料有主建上的外鍵關係


                SQLiteDatabase db = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR);");
                db.execSQL("Update  main set adlprogress = '"+"adl完成"+"' where interviewerid = '"+t1+"' and testerid = '"+t2+"'");
                db.close();
                //並更新資料表main裡的adl進度為已完成



                Intent intent = new Intent();
                intent.setClass(ADLANS.this, list.class);

                Bundle bundle = new Bundle();

                bundle.putString("測試者id", t1);//

                intent.putExtras(bundle);

                startActivity(intent);
                ADLANS.this.finish();
                //回到list 並攜帶測試者id

            }
        });


        /*
        if(bundle.getInt("分數")>=65)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":中度依賴");
            p1.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        }
        else if(bundle.getInt("分數")<=60&&bundle.getInt("分數")>=35)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":重度依賴");
            p1.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
        }
        else if(bundle.getInt("分數")<=30)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":極重度依賴");
            p1.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }
        */

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(ADLANS.this, list.class);

            Bundle bundle = new Bundle();

            bundle.putString("測試者id", t1);//

            intent.putExtras(bundle);

            startActivity(intent);
            ADLANS.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }

}
