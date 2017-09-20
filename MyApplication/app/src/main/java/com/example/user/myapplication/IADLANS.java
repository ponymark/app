package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

public class IADLANS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iadlans);

        Bundle bundle = getIntent().getExtras();

        BootstrapLabel p1= (BootstrapLabel) findViewById(R.id.iadlanswer);

        int cou=0;
        String temp="";
        temp=bundle.getString("選擇");
        String [] result=temp.split(" ");
        for(int y=0;y<result.length;y++){
            if((y==0||y==1||y==3||y==5||y==6)&&(result[y].equals("1")||result[y].equals("0")))
            {
                if(y==0||y==1||y==3)
                {
                    cou++;
                }
            }
            else if((y==2||y==4||y==7)&&result[y].equals("0"))
            {
                if(y==2||y==4)
                {
                    cou++;
                }
            }
        }
        if(cou>=3)
        {
            p1.setText("評估結果:輕度失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        }
        else
        {
            p1.setText("評估結果:無失能");
            p1.setBootstrapBrand(DefaultBootstrapBrand.INFO);
        }

        /*
        //寫檔
        SQLiteDatabase mydatabase = openOrCreateDatabase("data",MODE_PRIVATE,null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS iadl(id VARCHAR,ans VARCHAR);");
        mydatabase.execSQL("INSERT INTO iadl VALUES('"+
                ""+//流水號?
                "','"+
                temp+
                "');");
                */
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(IADLANS.this, MainActivity.class);
            startActivity(intent);
            IADLANS.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }

}
