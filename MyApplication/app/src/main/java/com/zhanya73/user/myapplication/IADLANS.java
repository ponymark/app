package com.zhanya73.user.myapplication;

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

public class IADLANS extends AppCompatActivity {
    String t1;
    String t2;
    String [] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iadlans);

        Bundle bundle = getIntent().getExtras();

        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.iadlanswer);
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");

        int cou=0;
        String temp="";
        temp=bundle.getString("選擇");
        result=temp.split(" ");
        for(int y=0;y<result.length;y++){
            if((y==0||y==1||y==3||y==5||y==6)&&(result[y].equals("1")||result[y].equals("0")))
            {
                if(y==0||y==1||y==3)
                {
                    cou++;
                }
            }
            else if((y==2||y==4||y==7)&&result[y].equals("0"))
            {
                if(y==2||y==4)
                {
                    cou++;
                }
            }
        }
        if(cou>=3)
        {
            p1.setText("評估結果:輕度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        }
        else
        {
            p1.setText("評估結果:無失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.INFO);
        }

        /*
        //寫檔
        SQLiteDatabase mydatabase = openOrCreateDatabase("data",MODE_PRIVATE,null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS iadl(id VARCHAR,ans VARCHAR);");
        mydatabase.execSQL("INSERT INTO iadl VALUES('"+
                ""+//流水號?
                "','"+
                temp+
                "');");
                */

        BootstrapButton adlsave = (BootstrapButton)findViewById(R.id.iadlsave);
        adlsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){



                SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS iadl(interviewerid VARCHAR,testerid VARCHAR,progress VARCHAR,B1 VARCHAR,B2 VARCHAR,B3 VARCHAR,B4 VARCHAR,B5 VARCHAR,B6 VARCHAR,B7 VARCHAR,B8 VARCHAR,generateddate VARCHAR);");

                ContentValues contentValues = new ContentValues();
                contentValues.put("interviewerid", t1);
                contentValues.put("testerid", t2);
                contentValues.put("progress", "已完成");

                for(int i=0;i<result.length;i++){
                    contentValues.put("B"+(i+1), result[i]);
                }
                String date =(new SimpleDateFormat("yyyy/MM/dd")).format(new Date());
                contentValues.put("generateddate", date);
                mydatabase.insert("iadl", null, contentValues);

                mydatabase.close();
                //寫檔
                //寫入另一個資料表adl 和一開始main活動資料有主建上的外鍵關係


                SQLiteDatabase db = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR,generateddate VARCHAR);");
                db.execSQL("Update  main set iadlprogress = '"+"iadl完成"+"' where interviewerid = '"+t1+"' and testerid = '"+t2+"'");
                db.close();
                //並更新資料表main裡的adl進度為已完成



                Intent intent = new Intent();
                intent.setClass(IADLANS.this, list.class);

                Bundle bundle = new Bundle();

                bundle.putString("測試者id", t1);//

                intent.putExtras(bundle);

                startActivity(intent);
                IADLANS.this.finish();
                //回到list 並攜帶測試者id

            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(IADLANS.this, MainActivity.class);
            startActivity(intent);
            IADLANS.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }

}
