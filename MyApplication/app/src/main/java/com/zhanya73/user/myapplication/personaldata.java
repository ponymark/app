package com.zhanya73.user.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapDropDown;
import com.beardedhen.androidbootstrap.BootstrapEditText;

public class personaldata extends AppCompatActivity {

    String t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldata);

        Bundle bundle = getIntent().getExtras();
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");


        final BootstrapDropDown spinner1 = (BootstrapDropDown) findViewById(R.id.ttt2);
        spinner1.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner1.setText(spinner1.getDropdownData()[id]);
            }

        });

        final BootstrapDropDown spinner2 = (BootstrapDropDown) findViewById(R.id.ttt4);
        spinner2.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner2.setText(spinner2.getDropdownData()[id]);
                if(id==spinner2.getDropdownData().length-1){
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.JJJ2);
                    otherwork.setEnabled(true);
                }
                else{
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.JJJ2);
                    otherwork.setEnabled(false);
                    otherwork.setText("");
                }
            }

        });


        final BootstrapDropDown spinner3 = (BootstrapDropDown) findViewById(R.id.d92);
        spinner3.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner3.setText(spinner3.getDropdownData()[id]);
                if(id==spinner3.getDropdownData().length-1){
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.d912);
                    otherwork.setEnabled(true);
                }
                else{
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.d912);
                    otherwork.setEnabled(false);
                    otherwork.setText("");
                }
            }

        });

        final BootstrapDropDown spinner4 = (BootstrapDropDown) findViewById(R.id.d102);
        spinner4.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner4.setText(spinner4.getDropdownData()[id]);
            }

        });

        final BootstrapDropDown spinner5 = (BootstrapDropDown) findViewById(R.id.d112);
        spinner5.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner5.setText(spinner5.getDropdownData()[id]);
                if(id==spinner5.getDropdownData().length-1){
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.d1112);
                    otherwork.setEnabled(true);
                }
                else{
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.d1112);
                    otherwork.setEnabled(false);
                    otherwork.setText("");
                }
            }

        });

        final BootstrapDropDown spinner6 = (BootstrapDropDown) findViewById(R.id.d122);
        spinner6.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner6.setText(spinner6.getDropdownData()[id]);
            }

        });

        final BootstrapDropDown spinner7 = (BootstrapDropDown) findViewById(R.id.d13a2);
        spinner7.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner7.setText(spinner7.getDropdownData()[id]);
            }

        });

        final BootstrapDropDown spinner8 = (BootstrapDropDown) findViewById(R.id.d13b2);
        spinner8.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner8.setText(spinner8.getDropdownData()[id]);
            }

        });

        final BootstrapDropDown spinner9 = (BootstrapDropDown) findViewById(R.id.d13c2);
        spinner9.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner9.setText(spinner9.getDropdownData()[id]);
            }

        });

        final BootstrapDropDown spinner10 = (BootstrapDropDown) findViewById(R.id.d152);
        spinner10.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner10.setText(spinner10.getDropdownData()[id]);
            }

        });

        BootstrapButton personaldatasave = (BootstrapButton)findViewById(R.id.personaldatasave);
        personaldatasave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){



                String ss="CREATE TABLE IF NOT EXISTS "
                        + "personaldata("
                        +"interviewerid VARCHAR,"
                        +"testerid VARCHAR,"
                        + "progress VARCHAR,"
                        + "D1A VARCHAR,"
                        + "D1B VARCHAR,"
                        + "D1C VARCHAR,"
                        + "D2 VARCHAR,"
                        + "D3 VARCHAR,"
                        + "D4 VARCHAR,"
                        + "D5 VARCHAR,"
                        + "D6A VARCHAR,"
                        + "D6B VARCHAR,"
                        + "D6C VARCHAR,"
                        + "D7 VARCHAR,"
                        + "D7_1 VARCHAR,"
                        + "D8 VARCHAR,"
                        + "D9 VARCHAR,"
                        + "D9_1 VARCHAR,"
                        + "D10 VARCHAR,"
                        + "D11 VARCHAR,"
                        + "D11_1 VARCHAR,"
                        + "D12 VARCHAR,"
                        + "D13A VARCHAR,"
                        + "D13B VARCHAR,"
                        + "D13C VARCHAR,"
                        + "D14A VARCHAR,"
                        + "D14B VARCHAR,"
                        + "D15 VARCHAR"
                        +");";
                BootstrapEditText phone = (BootstrapEditText) findViewById(R.id.d42);
                BootstrapEditText job = (BootstrapEditText) findViewById(R.id.JJJ2);
                BootstrapEditText welfare = (BootstrapEditText) findViewById(R.id.d912);
                BootstrapEditText income = (BootstrapEditText) findViewById(R.id.d102);
                BootstrapEditText d1112 = (BootstrapEditText) findViewById(R.id.d1112);
                BootstrapEditText d14a2 = (BootstrapEditText) findViewById(R.id.d14a2);
                BootstrapEditText d14b2 = (BootstrapEditText) findViewById(R.id.d14b2);






                SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                mydatabase.execSQL(ss);

                ContentValues contentValues = new ContentValues();
                contentValues.put("interviewerid", t1);
                contentValues.put("testerid", t2);
                contentValues.put("progress", "已完成");
                contentValues.put("D1A","");
                contentValues.put("D1B","");
                contentValues.put("D1C","");
                contentValues.put("D2","");
                contentValues.put("D3","");
                contentValues.put("D4",phone.getText().toString());
                contentValues.put("D5","");
                contentValues.put("D6A","");
                contentValues.put("D6B","");
                contentValues.put("D6C","");
                contentValues.put("D7",spinner2.getText().toString());
                contentValues.put("D7_1",job.getText().toString());
                contentValues.put("D8",spinner1.getText().toString());
                contentValues.put("D9",spinner3.getText().toString());
                contentValues.put("D9_1",welfare.getText().toString());
                contentValues.put("D10",income.getText().toString());
                contentValues.put("D11",spinner5.getText().toString());
                contentValues.put("D11_1",d1112.getText().toString());
                contentValues.put("D12",spinner6.getText().toString());
                contentValues.put("D13A",spinner7.getText().toString());
                contentValues.put("D13B",spinner8.getText().toString());
                contentValues.put("D13C",spinner9.getText().toString());
                contentValues.put("D14A",d14a2.getText().toString());
                contentValues.put("D14B",d14b2.getText().toString());
                contentValues.put("D15",spinner10.getText().toString());

                mydatabase.insert("personaldata", null, contentValues);

                mydatabase.close();
                //寫檔
                //寫入另一個資料表personaldata和一開始main活動資料有主建上的外鍵關係


                SQLiteDatabase db = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR);");
                db.execSQL("Update  main set personaldataprogress = '"+"personaldata完成"+"' where interviewerid = '"+t1+"' and testerid = '"+t2+"'");
                db.close();
                //並更新資料表main裡的personaldata進度為已完成



                Intent intent = new Intent();
                intent.setClass(personaldata.this, list.class);

                Bundle bundle = new Bundle();

                bundle.putString("測試者id", t1);//

                intent.putExtras(bundle);

                startActivity(intent);
                personaldata.this.finish();
                //回到list 並攜帶測試者id

            }
        });
    }
}
