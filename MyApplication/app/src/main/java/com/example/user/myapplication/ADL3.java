package com.example.user.myapplication;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class ADL3 extends AppCompatActivity {
    int pastchose=0;
    int chose=-1;
    int counter=0;
    String record="",past="";
    String t1;
    String t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adl3);

        for(int i=4 ; i<=5 ; i++)
        {
            String abc = "page"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }
        for(int i=6 ; i<=9 ; i++)
        {
            String abc = "bbutton_group_checked"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }

        BootstrapButton bb=(BootstrapButton) findViewById(R.id.bbutton_group_checked6);
        bb.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=15;
                }
            }
        });
        BootstrapButton bb2=(BootstrapButton) findViewById(R.id.bbutton_group_checked7);
        bb2.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=10;
                }
            }
        });
        BootstrapButton bb3=(BootstrapButton) findViewById(R.id.bbutton_group_checked8);
        bb3.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=5;
                }
            }
        });
        BootstrapButton bb4=(BootstrapButton) findViewById(R.id.bbutton_group_checked9);
        bb4.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=0;
                }
            }
        });



        Bundle bundle = getIntent().getExtras();
        counter=bundle.getInt("題號")+1;
        pastchose=bundle.getInt("分數");
        record=bundle.getString("選擇");
        past=bundle.getString("上一題");//
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");
        init();

        BootstrapButton next=(BootstrapButton) findViewById(R.id.page5);
        next.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    if(chose!=-1){
                        Intent intent = new Intent();


                        if(counter==9) {
                            intent.setClass(ADL3.this, ADL.class);
                        }
                        else if(counter==8) {
                            intent.setClass(ADL3.this, ADL3.class);
                        }



                        Bundle bundle = new Bundle();
                        bundle.putInt("分數",pastchose+chose);
                        bundle.putInt("題號",counter);
                        bundle.putString("選擇",record+" "+Integer.toString(chose));
                        bundle.putString("上一題","不需要");//
                        bundle.putString("測試者id",t1);
                        bundle.putString("受訪者id",t2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        ADL3.this.finish();
                    }
                    else{
                        DialogFragment newFragment = new FireMissilesDialogFragment();
                        newFragment.show(getSupportFragmentManager(), "missiles");
                    }
            }

            }});
        BootstrapButton past=(BootstrapButton) findViewById(R.id.page4);
        past.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent();

                    if(counter==8) {
                        intent.setClass(ADL3.this, ADL.class);
                    }
                    else if(counter==9) {
                        intent.setClass(ADL3.this, ADL3.class);
                    }

                    Bundle bundle = new Bundle();

                    String [] result=record.split(" ");
                    String temp="";
                    for(int y=0;y<result.length-1;y++){
                        if(y!=result.length-2)
                        {temp+=result[y]+" ";}
                        else
                        {temp+=result[y];}
                    }

                    String last=result.length==0?"0":result[result.length-1];//

                    bundle.putInt("分數",counter==1?0:pastchose-Integer.parseInt(result[counter-2]));
                    bundle.putInt("題號",counter==1?0:counter-2);
                    bundle.putString("選擇",temp);
                    bundle.putString("上一題",last);//
                    bundle.putString("測試者id",t1);
                    bundle.putString("受訪者id",t2);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    ADL3.this.finish();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();

            if(counter==8) {
                intent.setClass(ADL3.this, ADL.class);
            }
            else if(counter==9) {
                intent.setClass(ADL3.this, ADL3.class);
            }

            Bundle bundle = new Bundle();

            String [] result=record.split(" ");
            String temp="";
            for(int y=0;y<result.length-1;y++){
                if(y!=result.length-2)
                {temp+=result[y]+" ";}
                else
                {temp+=result[y];}
            }

            String last=result.length==0?"0":result[result.length-1];//

            bundle.putInt("分數",counter==1?0:pastchose-Integer.parseInt(result[counter-2]));
            bundle.putInt("題號",counter==1?0:counter-2);
            bundle.putString("選擇",temp);
            bundle.putString("上一題",last);//
            bundle.putString("測試者id",t1);
            bundle.putString("受訪者id",t2);
            intent.putExtras(bundle);
            startActivity(intent);
            ADL3.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setupCustomStyle(int id,float t) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(t);

    }
    private  void init()
    {
        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.problem3);
        BootstrapButton c1= (BootstrapButton) findViewById(R.id.bbutton_group_checked6);
        BootstrapButton c2= (BootstrapButton) findViewById(R.id.bbutton_group_checked7);
        BootstrapButton c3= (BootstrapButton) findViewById(R.id.bbutton_group_checked8);
        BootstrapButton c4= (BootstrapButton) findViewById(R.id.bbutton_group_checked9);

        if(counter==8){
            p1.setText(R.string.p8);
            c1.setText(R.string.p8c1);
            c2.setText(R.string.p8c2);
            c3.setText(R.string.p8c3);
            c4.setText(R.string.p8c4);
        }
        if(counter==9){
            p1.setText(R.string.p9);
            c1.setText(R.string.p9c1);
            c2.setText(R.string.p9c2);
            c3.setText(R.string.p9c3);
            c4.setText(R.string.p9c4);
        }

        if(!past.equals("不需要")){
            if(past.equals("15")){
                c1.setSelected(true);
            }
            if(past.equals("10")){
                c2.setSelected(true);
            }
            if(past.equals("5")){
                c3.setSelected(true);
            }
            if(past.equals("0")){
                c4.setSelected(true);
            }
        }
    }
}
