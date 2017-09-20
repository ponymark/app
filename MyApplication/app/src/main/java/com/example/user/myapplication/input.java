package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class input extends AppCompatActivity {

    int inputcounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        String abc = "input"+String.valueOf(3);
        int resID = getResources().getIdentifier(abc, "id", getPackageName());
        setupCustomStyle(resID,2.0f);

        Bundle bundle = getIntent().getExtras();
        inputcounter=(bundle==null)?0:bundle.getInt("輸入順序");

        init();

        BootstrapButton bb=(BootstrapButton) findViewById(R.id.input3);
        bb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent();
                    if(inputcounter==0) {
                        intent.setClass(input.this, input.class);
                    }
                    else {
                        intent.setClass(input.this, MainActivity.class);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("輸入順序",inputcounter+1);

                    BootstrapEditText trueinput=(BootstrapEditText) findViewById(R.id.input2);


                    if(inputcounter==0) {
                        bundle.putString("測試者id",trueinput.getText().toString() );//
                    }
                    else {

                        Bundle getbundle = getIntent().getExtras();
                        bundle.putString("測試者id",getbundle.getString("測試者id") );//
                        bundle.putString("受訪者id",trueinput.getText().toString() );//
                    }

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

        if(inputcounter==0){
            i1.setText(getResources().getString(getResources().getIdentifier("input" + "1", "string", getPackageName())));
        }
        else{
            i1.setText(getResources().getString(getResources().getIdentifier("input"+"2", "string", getPackageName())));
        }
    }
}
