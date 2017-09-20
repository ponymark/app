package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

public class ADLANS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adlans);

        Bundle bundle = getIntent().getExtras();

        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.adlanswer);

        int cou=0;
        String temp="";
        temp=bundle.getString("選擇");
        String [] result=temp.split(" ");
        for(int y=0;y<result.length;y++){
            if((y==0||y==3||y==6)&&(result[y].equals("5")||result[y].equals("0")))
            {
                cou++;
            }
            else if((y==7||y==8)&&(result[y].equals("10")||result[y].equals("5")||result[y].equals("0")))
            {
                cou++;
            }
            else if(y==1&&result[y].equals("0"))
            {
                cou++;
            }
        }
        if(cou<3&&cou>0)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":輕度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        }
        else if(cou<5&&cou>2)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":中度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
        }
        else if(cou>4)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":重度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }
        else
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":無失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.INFO);
        }





        /*
        //寫檔
        SQLiteDatabase mydatabase = openOrCreateDatabase("data",MODE_PRIVATE,null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS adl(id VARCHAR,ans VARCHAR);");
        mydatabase.execSQL("INSERT INTO adl VALUES('"+
                ""+//流水號?
                "','"+
                temp+
                "');");
                */
















        /*
        if(bundle.getInt("分數")>=65)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":中度依賴");
            p1.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        }
        else if(bundle.getInt("分數")<=60&&bundle.getInt("分數")>=35)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":重度依賴");
            p1.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
        }
        else if(bundle.getInt("分數")<=30)
        {
            p1.setText(Integer.toString(bundle.getInt("分數"))+":極重度依賴");
            p1.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }
        */

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(ADLANS.this, MainActivity.class);
            startActivity(intent);
            ADLANS.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }

}
