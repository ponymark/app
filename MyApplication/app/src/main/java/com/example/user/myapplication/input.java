package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        init();

        BootstrapButton bb=(BootstrapButton) findViewById(R.id.input3);
        bb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(input.this, list.class);
                Bundle bundle = new Bundle();

                BootstrapEditText trueinput=(BootstrapEditText) findViewById(R.id.input2);

                bundle.putString("測試者id",trueinput.getText().toString() );//

                intent.putExtras(bundle);
                startActivity(intent);
                input.this.finish();

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
