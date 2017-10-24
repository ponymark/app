package com.example.user.myapplication;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class ADL2 extends AppCompatActivity {
    int pastchose=0;
    int chose=-1;
    int counter =0;
    String record="",past="";
    String t1;
    String t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adl2);
        for(int i=2 ; i<=3 ; i++)
        {
            String abc = "page"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }
        for(int i=4 ; i<=5 ; i++)
        {
            String abc = "bbutton_group_checked"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }

        BootstrapButton bb=(BootstrapButton) findViewById(R.id.bbutton_group_checked4);
        bb.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=5;
                }
            }
        });
        BootstrapButton bb2=(BootstrapButton) findViewById(R.id.bbutton_group_checked5);
        bb2.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
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

        BootstrapButton next=(BootstrapButton) findViewById(R.id.page3);
        next.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    if(chose!=-1){
                        Intent intent = new Intent();


                        if(counter==2) {
                            intent.setClass(ADL2.this, ADL2.class);
                        }
                        else if(counter==3) {
                            intent.setClass(ADL2.this, ADL.class);
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
                        ADL2.this.finish();
                    }
                    else{
                        DialogFragment newFragment = new FireMissilesDialogFragment();
                        newFragment.show(getSupportFragmentManager(), "missiles");
                    }

            }


            }});

        BootstrapButton past=(BootstrapButton) findViewById(R.id.page2);
        past.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent();

                    if(counter==3) {
                        intent.setClass(ADL2.this, ADL2.class);
                    }
                    else if(counter==2) {
                        intent.setClass(ADL2.this, ADL.class);
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
                    ADL2.this.finish();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();

            if(counter==3) {
                intent.setClass(ADL2.this, ADL2.class);
            }
            else if(counter==2) {
                intent.setClass(ADL2.this, ADL.class);
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
            ADL2.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setupCustomStyle(int id,float t) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(t);

    }
    private  void init()
    {
        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.problem2);
        BootstrapButton c1= (BootstrapButton) findViewById(R.id.bbutton_group_checked4);
        BootstrapButton c2= (BootstrapButton) findViewById(R.id.bbutton_group_checked5);

        if(counter==2){
            p1.setText(R.string.p2);
            c1.setText(R.string.p2c1);
            c2.setText(R.string.p2c2);
        }
        if(counter==3){
            p1.setText(R.string.p3);
            c1.setText(R.string.p3c1);
            c2.setText(R.string.p3c2);
        }

        if(!past.equals("不需要")){
            if(past.equals("5")){
                c1.setSelected(true);
            }
            if(past.equals("0")){
                c2.setSelected(true);
            }
        }
    }
}
