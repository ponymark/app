package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class Questionnaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
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
                intent.putExtras(bundle);
                startActivity(intent);
                Questionnaire.this.finish();
            }
        });


    }
    private void setupCustomStyle(int id) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(3.0f);
    }
}
