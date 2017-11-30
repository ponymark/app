package com.zhanya73.user.myapplication;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class IADL extends AppCompatActivity {
    //int pastchose=0;
    int chose=-1;
    int counter=0;
    String record="",past="";
    String t1;
    String t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iadl);
        for(int i=6 ; i<=7 ; i++)
        {
            String abc = "page"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }
        for(int i=10 ; i<=13 ; i++)
        {
            String abc = "bbutton_group_checked"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }




        BootstrapButton bb=(BootstrapButton) findViewById(R.id.bbutton_group_checked10);
        bb.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=3;
                }
            }
        });
        BootstrapButton bb2=(BootstrapButton) findViewById(R.id.bbutton_group_checked11);
        bb2.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=2;
                }
            }
        });
        BootstrapButton bb3=(BootstrapButton) findViewById(R.id.bbutton_group_checked12);
        bb3.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=1;
                }
            }
        });
        BootstrapButton bb4=(BootstrapButton) findViewById(R.id.bbutton_group_checked13);
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
        //pastchose=bundle.getInt("分數");
        record=bundle.getString("選擇");
        past=bundle.getString("上一題");//
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");
        init();
        setTitle(getResources().getIdentifier("iadlprogress"+counter, "string", getPackageName()));



        BootstrapButton next=(BootstrapButton) findViewById(R.id.page7);
        next.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    if(chose!=-1){
                        Intent intent = new Intent();

                        if(counter==5) {
                            intent.setClass(IADL.this, IADL2.class);
                        }
                        else if(counter==8) {
                            intent.setClass(IADL.this, IADLANS.class);
                        }

                        Bundle bundle = new Bundle();
                        //bundle.putInt("分數",pastchose+chose);
                        bundle.putInt("題號",counter);
                        bundle.putString("選擇",counter==1?Integer.toString(chose):record+" "+Integer.toString(chose));
                        bundle.putString("上一題","不需要");//
                        bundle.putString("測試者id",t1);
                        bundle.putString("受訪者id",t2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        IADL.this.finish();
                    }
                    else{
                        DialogFragment newFragment = new FireMissilesDialogFragment();
                        newFragment.show(getSupportFragmentManager(), "missiles");
                    }
                }

            }
        });


        BootstrapButton past=(BootstrapButton) findViewById(R.id.page6);
        past.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent();

                    if(counter==5) {
                        intent.setClass(IADL.this, IADL3.class);
                    }
                    else if(counter==8) {
                        intent.setClass(IADL.this, IADL2.class);
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


                    //bundle.putInt("分數",counter==1?0:pastchose-Integer.parseInt(result[counter-2]));
                    bundle.putInt("題號",counter==1?0:counter-2);
                    bundle.putString("選擇",temp);
                    bundle.putString("上一題",last);//
                    bundle.putString("測試者id",t1);
                    bundle.putString("受訪者id",t2);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    IADL.this.finish();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();

            if(counter==5) {
                intent.setClass(IADL.this, IADL3.class);
            }
            else if(counter==8) {
                intent.setClass(IADL.this, IADL2.class);
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


            //bundle.putInt("分數",counter==1?0:pastchose-Integer.parseInt(result[counter-2]));
            bundle.putInt("題號",counter==1?0:counter-2);
            bundle.putString("選擇",temp);
            bundle.putString("上一題",last);//
            bundle.putString("測試者id",t1);
            bundle.putString("受訪者id",t2);
            intent.putExtras(bundle);
            startActivity(intent);
            IADL.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setupCustomStyle(int id,float t) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(t);

    }
    private  void init()
    {
        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.problem4);
        BootstrapButton c1= (BootstrapButton) findViewById(R.id.bbutton_group_checked10);
        BootstrapButton c2= (BootstrapButton) findViewById(R.id.bbutton_group_checked11);
        BootstrapButton c3= (BootstrapButton) findViewById(R.id.bbutton_group_checked12);
        BootstrapButton c4= (BootstrapButton) findViewById(R.id.bbutton_group_checked13);

        if(counter==5){
            p1.setText(R.string.I5);
            c1.setText(R.string.I5c1);
            c2.setText(R.string.I5c2);
            c3.setText(R.string.I5c3);
            c4.setText(R.string.I5c4);
        }
        if(counter==8){
            p1.setText(R.string.I8);
            c1.setText(R.string.I8c1);
            c2.setText(R.string.I8c2);
            c3.setText(R.string.I8c3);
            c4.setText(R.string.I8c4);
        }

        if(!past.equals("不需要")){
            if(past.equals("3")){
                c1.setSelected(true);
            }
            if(past.equals("2")){
                c2.setSelected(true);
            }
            if(past.equals("1")){
                c3.setSelected(true);
            }
            if(past.equals("0")){
                c4.setSelected(true);
            }
        }
    }
}
