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

public class WHOQOLANS extends AppCompatActivity {
    String t1;
    String t2;
    String [] temp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whoqolans);

        Bundle bundle = getIntent().getExtras();


        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");

        int cou=0;
        String temp="";
        temp=bundle.getString("選擇");
        temp2=temp.split(" ");
        String rs="";

        for(int i=0;i<temp2.length;i++){
            if(i==temp2.length-1){
                rs+="第"+Integer.toString(i+1)+"題\t:\t"+temp2[i];
            }
            else{
                rs+="第"+Integer.toString(i+1)+"題\t:\t"+temp2[i]+"\n";
            }
        }



        BootstrapButton whoqolsave = (BootstrapButton)findViewById(R.id.whoqolsave);
        whoqolsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS whoqol(interviewerid VARCHAR,testerid VARCHAR,progress VARCHAR,C1 VARCHAR,C2 VARCHAR,C3 VARCHAR,C4 VARCHAR, C5 VARCHAR,C6 VARCHAR,C7 VARCHAR,C8 VARCHAR,C9 VARCHAR,C10 VARCHAR,C11 VARCHAR,C12 VARCHAR,C13 VARCHAR,C14 VARCHAR,C15 VARCHAR,C16 VARCHAR,C17 VARCHAR,C18 VARCHAR,C19 VARCHAR,C20 VARCHAR,C21 VARCHAR,C22 VARCHAR,C23 VARCHAR,C24 VARCHAR,C25 VARCHAR,C26 VARCHAR,C27 VARCHAR,C28 VARCHAR,generateddate VARCHAR);");

                ContentValues contentValues = new ContentValues();
                contentValues.put("interviewerid", t1);
                contentValues.put("testerid", t2);
                contentValues.put("progress", "已完成");

                for(int i=0;i<temp2.length;i++){
                    contentValues.put("C"+(i+1), temp2[i]);
                }



                String date =(new SimpleDateFormat("yyyy/MM/dd")).format(new Date());
                contentValues.put("generateddate", date);

                mydatabase.insert("whoqol", null, contentValues);

                mydatabase.close();
                //寫檔
                //寫入另一個資料表who 和一開始main活動資料有主建上的外鍵關係


                SQLiteDatabase db = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR);");
                db.execSQL("Update  main set whoqolprogress = '"+"whoqol完成"+"' where interviewerid = '"+t1+"' and testerid = '"+t2+"'");
                db.close();
                //並更新資料表main裡的who進度為已完成



                Intent intent = new Intent();
                intent.setClass(WHOQOLANS.this, list.class);

                Bundle bundle = new Bundle();

                bundle.putString("測試者id", t1);//

                intent.putExtras(bundle);

                startActivity(intent);
                WHOQOLANS.this.finish();
                //回到list 並攜帶測試者id
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(WHOQOLANS.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("測試者id",t1);
            bundle.putString("受訪者id",t2);
            intent.putExtras(bundle);
            startActivity(intent);
            WHOQOLANS.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }
}
