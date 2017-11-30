package com.zhanya73.user.myapplication;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class IADL2 extends AppCompatActivity {
    int chose=-1;
    int counter=0;
    String record="",past="";
    String t1;
    String t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iadl2);

        for(int i=8 ; i<=9 ; i++)
        {
            String abc = "page"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }
        for(int i=14 ; i<=18 ; i++)
        {
            String abc = "bbutton_group_checked"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }




        BootstrapButton bb=(BootstrapButton) findViewById(R.id.bbutton_group_checked14);
        bb.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=4;
                }
            }
        });
        BootstrapButton bb2=(BootstrapButton) findViewById(R.id.bbutton_group_checked15);
        bb2.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=3;
                }
            }
        });
        BootstrapButton bb3=(BootstrapButton) findViewById(R.id.bbutton_group_checked16);
        bb3.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=2;
                }
            }
        });
        BootstrapButton bb4=(BootstrapButton) findViewById(R.id.bbutton_group_checked17);
        bb4.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=1;
                }
            }
        });
        BootstrapButton bb5=(BootstrapButton) findViewById(R.id.bbutton_group_checked18);
        bb5.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=0;
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        counter=bundle.getInt("題號")+1;
        record=bundle.getString("選擇");
        past=bundle.getString("上一題");//
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");
        init();
        setTitle(getResources().getIdentifier("iadlprogress"+counter, "string", getPackageName()));



        BootstrapButton next=(BootstrapButton) findViewById(R.id.page9);
        next.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    if(chose!=-1){
                        Intent intent = new Intent();

                        if(counter==1||counter==3) {
                            intent.setClass(IADL2.this, IADL3.class);
                        }
                        else if(counter==6) {
                            intent.setClass(IADL2.this, IADL2.class);
                        }
                        else if(counter==7)
                        {
                            intent.setClass(IADL2.this, IADL.class);
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
                        IADL2.this.finish();
                    }
                    else{
                        DialogFragment newFragment = new FireMissilesDialogFragment();
                        newFragment.show(getSupportFragmentManager(), "missiles");
                    }
                }

            }
        });


        BootstrapButton past=(BootstrapButton) findViewById(R.id.page8);
        past.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent();

                    if(counter==1) {
                        intent.setClass(IADL2.this,  Questionnaire.class);
                    }
                    else if(counter==3) {
                        intent.setClass(IADL2.this, IADL3.class);
                    }
                    else if(counter==6)
                    {
                        intent.setClass(IADL2.this, IADL.class);
                    }
                    else if(counter==7)
                    {
                        intent.setClass(IADL2.this, IADL2.class);
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
                    IADL2.this.finish();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();

            if(counter==1) {
                intent.setClass(IADL2.this,  Questionnaire.class);
            }
            else if(counter==3) {
                intent.setClass(IADL2.this, IADL3.class);
            }
            else if(counter==6)
            {
                intent.setClass(IADL2.this, IADL.class);
            }
            else if(counter==7)
            {
                intent.setClass(IADL2.this, IADL2.class);
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
            IADL2.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setupCustomStyle(int id,float t) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(t);

    }
    private  void init()
    {
        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.problem5);
        BootstrapButton c1= (BootstrapButton) findViewById(R.id.bbutton_group_checked14);
        BootstrapButton c2= (BootstrapButton) findViewById(R.id.bbutton_group_checked15);
        BootstrapButton c3= (BootstrapButton) findViewById(R.id.bbutton_group_checked16);
        BootstrapButton c4= (BootstrapButton) findViewById(R.id.bbutton_group_checked17);
        BootstrapButton c5= (BootstrapButton) findViewById(R.id.bbutton_group_checked18);

        if(counter==1){
            p1.setText(R.string.I1);
            c1.setText(R.string.I1c1);
            c2.setText(R.string.I1c2);
            c3.setText(R.string.I1c3);
            c4.setText(R.string.I1c4);
            c5.setText(R.string.I1c5);
        }
        if(counter==3){
            p1.setText(R.string.I3);
            c1.setText(R.string.I3c1);
            c2.setText(R.string.I3c2);
            c3.setText(R.string.I3c3);
            c4.setText(R.string.I3c4);
            c5.setText(R.string.I3c5);
        }
        if(counter==6){
            p1.setText(R.string.I6);
            c1.setText(R.string.I6c1);
            c2.setText(R.string.I6c2);
            c3.setText(R.string.I6c3);
            c4.setText(R.string.I6c4);
            c5.setText(R.string.I6c5);
        }
        if(counter==7){
            p1.setText(R.string.I7);
            c1.setText(R.string.I7c1);
            c2.setText(R.string.I7c2);
            c3.setText(R.string.I7c3);
            c4.setText(R.string.I7c4);
            c5.setText(R.string.I7c5);
        }

        if(!past.equals("不需要")){
            if(past.equals("4")){
                c1.setSelected(true);
            }
            if(past.equals("3")){
                c2.setSelected(true);
            }
            if(past.equals("2")){
                c3.setSelected(true);
            }
            if(past.equals("1")){
                c4.setSelected(true);
            }
            if(past.equals("0")){
                c5.setSelected(true);
            }
        }
    }

}
