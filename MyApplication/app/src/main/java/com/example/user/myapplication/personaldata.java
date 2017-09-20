package com.example.user.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapDropDown;
import com.beardedhen.androidbootstrap.BootstrapEditText;

public class personaldata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldata);
        final BootstrapDropDown spinner1 = (BootstrapDropDown) findViewById(R.id.ttt2);
        spinner1.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner1.setText(spinner1.getDropdownData()[id]);
            }

        });

        final BootstrapDropDown spinner2 = (BootstrapDropDown) findViewById(R.id.ttt4);
        spinner2.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner2.setText(spinner2.getDropdownData()[id]);
                if(id==spinner2.getDropdownData().length-1){
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.JJJ2);
                    otherwork.setEnabled(true);
                }
                else{
                    BootstrapEditText otherwork = (BootstrapEditText) findViewById(R.id.JJJ2);
                    otherwork.setEnabled(false);
                    otherwork.setText("");
                }
            }

        });

        final BootstrapDropDown spinner3 = (BootstrapDropDown) findViewById(R.id.yyy2);
        spinner3.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener(){
            @Override
            public void onItemClick(ViewGroup parent,View v,int id){
                spinner3.setText(spinner3.getDropdownData()[id]);
            }

        });
    }
}
