package com.example.user.myapplication;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;

public class WHOQOL1 extends AppCompatActivity {
    int pastchose=0;
    int chose=-1;
    int counter=0;
    String record="",past="";
    String t1;
    String t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_whoqol1);
        for(int i=12 ; i<=13 ; i++)
        {
            String abc = "page"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }
        for(int i=25 ; i<=30 ; i++)
        {
            String abc = "bbutton_group_checked"+String.valueOf(i);
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            setupCustomStyle(resID,2.0f);
        }




        BootstrapButton bb=(BootstrapButton) findViewById(R.id.bbutton_group_checked25);
        bb.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=1;
                }
            }
        });
        BootstrapButton bb2=(BootstrapButton) findViewById(R.id.bbutton_group_checked26);
        bb2.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=2;
                }
            }
        });
        BootstrapButton bb3=(BootstrapButton) findViewById(R.id.bbutton_group_checked27);
        bb3.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=3;
                }
            }
        });
        BootstrapButton bb4=(BootstrapButton) findViewById(R.id.bbutton_group_checked28);
        bb4.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=4;
                }
            }
        });
        BootstrapButton bb5=(BootstrapButton) findViewById(R.id.bbutton_group_checked29);
        bb5.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=5;
                }
            }
        });
        BootstrapButton bb6=(BootstrapButton) findViewById(R.id.bbutton_group_checked30);
        bb6.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    chose=99;
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

        setTitle(getResources().getIdentifier("progress"+counter, "string", getPackageName()));

        BootstrapButton next=(BootstrapButton) findViewById(R.id.page13);
        next.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    if(chose!=-1){
                        Intent intent = new Intent();


                        if(counter!=28) {//應該是28
                            intent.setClass(WHOQOL1.this, WHOQOL1.class);
                        }
                        else  {
                            intent.setClass(WHOQOL1.this, WHOQOLANS.class);
                        }

                        Bundle bundle = new Bundle();
                        bundle.putInt("分數",pastchose+chose);
                        bundle.putInt("題號",counter);
                        bundle.putString("選擇",counter==1?Integer.toString(chose):record+" "+Integer.toString(chose));
                        bundle.putString("上一題","不需要");//
                        bundle.putString("測試者id",t1);
                        bundle.putString("受訪者id",t2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        WHOQOL1.this.finish();
                    }
                    else{
                        DialogFragment newFragment = new FireMissilesDialogFragment();
                        newFragment.show(getSupportFragmentManager(), "missiles");
                    }
                }

            }
        });


        BootstrapButton past=(BootstrapButton) findViewById(R.id.page12);
        past.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener(){
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent();

                    if(counter!=1) {
                        intent.setClass(WHOQOL1.this, WHOQOL1.class);
                    }

                    else  {
                        intent.setClass(WHOQOL1.this, Questionnaire.class);
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

                    String last=result.length==0?"99":result[result.length-1];//


                    //bundle.putInt("分數",counter==1?0:pastchose-Integer.parseInt(result[counter-2]));
                    bundle.putInt("題號",counter==1?0:counter-2);
                    bundle.putString("選擇",temp);
                    bundle.putString("上一題",last);//
                    bundle.putString("測試者id",t1);
                    bundle.putString("受訪者id",t2);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    WHOQOL1.this.finish();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();

            if(counter!=1) {
                intent.setClass(WHOQOL1.this, WHOQOL1.class);
            }

            else  {
                intent.setClass(WHOQOL1.this, Questionnaire.class);
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

            String last=result.length==0?"99":result[result.length-1];//


            //bundle.putInt("分數",counter==1?0:pastchose-Integer.parseInt(result[counter-2]));
            bundle.putInt("題號",counter==1?0:counter-2);
            bundle.putString("選擇",temp);
            bundle.putString("上一題",last);//
            intent.putExtras(bundle);
            startActivity(intent);
            WHOQOL1.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }

    private void setupCustomStyle(int id,float t) {
        BootstrapButton bt1=(BootstrapButton )findViewById(id);
        bt1.setBootstrapSize(t);

    }
    private  void init()
    {
        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.problem7);
        BootstrapButton c1= (BootstrapButton) findViewById(R.id.bbutton_group_checked25);
        BootstrapButton c2= (BootstrapButton) findViewById(R.id.bbutton_group_checked26);
        BootstrapButton c3= (BootstrapButton) findViewById(R.id.bbutton_group_checked27);
        BootstrapButton c4= (BootstrapButton) findViewById(R.id.bbutton_group_checked28);
        BootstrapButton c5= (BootstrapButton) findViewById(R.id.bbutton_group_checked29);
        BootstrapButton c6= (BootstrapButton) findViewById(R.id.bbutton_group_checked30);

        p1.setText(getResources().getString(getResources().getIdentifier("W"+counter, "string", getPackageName())));
        c1.setText(getResources().getString(getResources().getIdentifier("W"+counter+"c1", "string", getPackageName())));
        c2.setText(getResources().getString(getResources().getIdentifier("W"+counter+"c2", "string", getPackageName())));
        c3.setText(getResources().getString(getResources().getIdentifier("W"+counter+"c3", "string", getPackageName())));
        c4.setText(getResources().getString(getResources().getIdentifier("W"+counter+"c4", "string", getPackageName())));
        c5.setText(getResources().getString(getResources().getIdentifier("W"+counter+"c5", "string", getPackageName())));
        c6.setText(getResources().getString(getResources().getIdentifier("W"+counter+"c6", "string", getPackageName())));


        if(!past.equals("不需要")){
            if(past.equals("1")){
                c1.setSelected(true);
            }
            if(past.equals("2")){
                c2.setSelected(true);
            }
            if(past.equals("3")){
                c3.setSelected(true);
            }
            if(past.equals("4")){
                c4.setSelected(true);
            }
            if(past.equals("5")){
                c5.setSelected(true);
            }
            if(past.equals("99")){
                c6.setSelected(true);
            }
        }
    }
}
