package com.example.user.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class list extends AppCompatActivity  implements action.actionListener {
    private static final String[] TITLES = {
        "t1", "t2", "t3","t4", "t5", "t6" ,"t7"
};

private static final String[] SUB_TITLES = {
        "未完成", "未上傳", "未開始","未完成", "未上傳", "未開始", "未開始"
        };

private static final int ICONS[] = {
        R.drawable.q111,
        R.drawable.q222,
        R.drawable.q333,
        };
//前兩個個陣列到時候改成讀取測試者id相關的sdqlite資料得到(在myadapter改)
ListView list;
    String item;
    int positiontest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Bundle getbundle = getIntent().getExtras();

        list = (ListView) findViewById(R.id.list1);
        myadapter adapter = new myadapter(TITLES,SUB_TITLES,ICONS,getbundle.getString("測試者id"));
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View v,
                                           int position,
                                           long id) {

                return true;
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View v,
                                    int position,
                                    long id) {
                item = (String) list.getItemAtPosition(position).toString();
                positiontest=position;
                DialogFragment newFragment = new action();
                newFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");
            }
        });

        FloatingActionButton mFab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(list.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
                //跳至新增活動
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {



    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Intent intent = new Intent();
        intent.setClass(list.this, MainActivity.class);

        Bundle bundle = new Bundle();

        bundle.putString("測試者id",item );//
        bundle.putString("受訪者id",item);//

        intent.putExtras(bundle);
        startActivity(intent);
        list.this.finish();
    }
}
