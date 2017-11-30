package com.zhanya73.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

public class WHOQOLANS extends AppCompatActivity {
    String t1;
    String t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whoqolans);

        Bundle bundle = getIntent().getExtras();

        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.whoqolanswer);
        t1=bundle.getString("測試者id");
        t2=bundle.getString("受訪者id");

        int cou=0;
        String temp="";
        temp=bundle.getString("選擇");
        String [] temp2=temp.split(" ");
        String rs="";

        for(int i=0;i<temp2.length;i++){
            if(i==temp2.length-1){
                rs+="第"+Integer.toString(i+1)+"題\t:\t"+temp2[i];
            }
            else{
                rs+="第"+Integer.toString(i+1)+"題\t:\t"+temp2[i]+"\n";
            }
        }
        p1.setText(rs);
        p1.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);




    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(WHOQOLANS.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("測試者id",t1);
            bundle.putString("受訪者id",t2);
            intent.putExtras(bundle);
            startActivity(intent);
            WHOQOLANS.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }
}
