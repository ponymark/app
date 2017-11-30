package com.zhanya73.user.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class input extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        String abc = "input"+String.valueOf(3);
        int resID = getResources().getIdentifier(abc, "id", getPackageName());
        setupCustomStyle(resID,2.0f);

        String abc2 = "clearalldata";
        int resID2 = getResources().getIdentifier(abc2, "id", getPackageName());
        setupCustomStyle(resID2,2.0f);

        init();

        BootstrapButton bb=(BootstrapButton) findViewById(R.id.input3);
        bb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                BootstrapEditText trueinput = (BootstrapEditText) findViewById(R.id.input2);
                if(trueinput.getText().toString().equals("")==false) {



                    int flag=0;

                    SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS task(interviewerid VARCHAR,taskname VARCHAR);");
                    Cursor resultSet = mydatabase.rawQuery("Select * from task where interviewerid = '"+trueinput.getText().toString()+"'",null);

                    if(resultSet.getCount()!=0){
                       flag=1;
                    }

                    resultSet.close();
                    mydatabase.close();

                    if(flag==1){
                        Intent intent = new Intent();
                        intent.setClass(input.this, list.class);
                        Bundle bundle = new Bundle();

                        bundle.putString("測試者id", trueinput.getText().toString());//

                        intent.putExtras(bundle);
                        startActivity(intent);
                        input.this.finish();
                    }
                    else{
                        Toast.makeText(input.this, "裝置資料庫無相關資料 判定為新使用者 請選擇工作!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(input.this, task.class);
                        Bundle bundle = new Bundle();

                        bundle.putString("測試者id", trueinput.getText().toString());//

                        intent.putExtras(bundle);
                        startActivity(intent);
                        input.this.finish();
                    }
                }
                else{
                    DialogFragment newFragment = new FireMissilesDialogFragment2();
                    newFragment.show(getSupportFragmentManager(), "missiles");
                }
            }
        });
        BootstrapButton bb2=(BootstrapButton) findViewById(R.id.clearalldata);
        bb2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                mydatabase.execSQL("DROP TABLE IF EXISTS main");
                mydatabase.execSQL("DROP TABLE IF EXISTS adl");
                mydatabase.execSQL("DROP TABLE IF EXISTS iadl");
                mydatabase.execSQL("DROP TABLE IF EXISTS whoqol");
                mydatabase.execSQL("DROP TABLE IF EXISTS personaldata");
                mydatabase.execSQL("DROP TABLE IF EXISTS recorddata");
                mydatabase.execSQL("DROP TABLE IF EXISTS relation");
                mydatabase.execSQL("DROP TABLE IF EXISTS task");
                mydatabase.close();
                Toast.makeText(input.this, "所有資料都刪除!!!", Toast.LENGTH_LONG).show();
            }
        });

    }
    private void setupCustomStyle(int id,float t) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(t);

    }
    private void init(){
        BootstrapLabel i1= (BootstrapLabel) findViewById(R.id.input1);
        i1.setText(getResources().getString(getResources().getIdentifier("input"+"1", "string", getPackageName())));
    }
}
