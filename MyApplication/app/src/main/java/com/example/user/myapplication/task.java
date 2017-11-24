package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class task extends AppCompatActivity {
    private  String interviewerid="",option="-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Bundle bundle = getIntent().getExtras();
        interviewerid=bundle.getString("測試者id");

        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.select);
        BootstrapButton c1= (BootstrapButton) findViewById(R.id.questionnaire);
        BootstrapButton c2= (BootstrapButton) findViewById(R.id.personaldata);

        p1.setText("請選擇工作:");
        c1.setText("問卷");
        c2.setText("基本資料");

        int resID = getResources().getIdentifier("questionnaire", "id", getPackageName());
        setupCustomStyle(resID,2.0f);
        int resID2 = getResources().getIdentifier("personaldata", "id", getPackageName());
        setupCustomStyle(resID2,2.0f);
        int resID3 = getResources().getIdentifier("past", "id", getPackageName());
        setupCustomStyle(resID3,2.0f);
        int resID4 = getResources().getIdentifier("future", "id", getPackageName());
        setupCustomStyle(resID4,2.0f);

        BootstrapButton bb=(BootstrapButton) findViewById(R.id.questionnaire);
        bb.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    option="問卷";
                }
            }
        });
        BootstrapButton bb2=(BootstrapButton) findViewById(R.id.personaldata);
        bb2.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    option="基本資料";
                }
            }
        });

        BootstrapButton past=(BootstrapButton) findViewById(R.id.past);
        past.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                            Intent intent = new Intent();
                            intent.setClass(task.this, input.class);
                            startActivity(intent);
                            task.this.finish();
                }}
        });
        BootstrapButton future=(BootstrapButton) findViewById(R.id.future);
        future.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {

                    if(option.equals("-1"))
                    {

                        DialogFragment newFragment = new FireMissilesDialogFragment();
                        newFragment.show(getSupportFragmentManager(), "missiles");

                    }
                    else{
                        SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS task(interviewerid VARCHAR,taskname VARCHAR);");

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("interviewerid", interviewerid);
                        contentValues.put("taskname",option );
                        mydatabase.insert("task", null, contentValues);
                        mydatabase.close();

                        Intent intent = new Intent();
                        intent.setClass(task.this, list.class);
                        Bundle bundle = new Bundle();

                        bundle.putString("測試者id", interviewerid);//

                        intent.putExtras(bundle);
                        startActivity(intent);
                        task.this.finish();
                    }


                }}
        });

    }
    private void setupCustomStyle(int id,float t) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(t);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(task.this, input.class);
            startActivity(intent);
            task.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }
}
