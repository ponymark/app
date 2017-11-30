package com.zhanya73.user.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class list extends AppCompatActivity  implements insertaction.insertactionListener ,FireMissilesDialogFragment3.listListener {
    private  ArrayList<String> TITLES =new ArrayList<String>();//受訪者id
    private  ArrayList<String> SUB_TITLES =new ArrayList<String>();//紀錄進度
    //前兩個個陣列到時候改成讀取測試者id相關的sdqlite資料得到(在myadapter改)
    NetWorkStateReceiver netWorkStateReceiver;//網路狀態


    private  String interviewerid="",taskname="";

    private static final int ICONS[] = {
        R.drawable.q111,
        R.drawable.q222,
        R.drawable.q333,
        };

    ListView list;
    String item;
    int positiontest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Bundle getbundle = getIntent().getExtras();

        list = (ListView) findViewById(R.id.list1);

        interviewerid=getbundle.getString("測試者id");






        SQLiteDatabase mydatabase2 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
        mydatabase2.execSQL("CREATE TABLE IF NOT EXISTS task(interviewerid VARCHAR,taskname VARCHAR);");
        Cursor resultSet2 = mydatabase2.rawQuery("Select * from task where interviewerid = '"+interviewerid+"'",null);

        if(resultSet2.getCount()!=0){
            resultSet2.moveToFirst();
            do {
                taskname=resultSet2.getString(1);
            } while (resultSet2.moveToNext());
        }

        resultSet2.close();
        mydatabase2.close();











        SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR,generateddate VARCHAR);");
        Cursor resultSet = mydatabase.rawQuery("Select * from main where interviewerid = '"+interviewerid+"'",null);

        if(resultSet.getCount()!=0){
            resultSet.moveToFirst();
            do {
                String dc1 = resultSet.getString(1)+" "+resultSet.getString(2);//這裡要改成名字
                String dc2 ;
                if(taskname.equals("基本資料")) {
                    dc2 = resultSet.getString(6);//和進度
                }
                else if(taskname.equals("問卷")) {
                    dc2 = resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5)+" ";//和進度
                }
                else if(taskname.equals("錄音")) {
                    dc2 = resultSet.getString(7);//和進度
                }
                else{
                    dc2 = "";//和進度
                }


                TITLES.add(dc1);
                SUB_TITLES.add(dc2);

            } while (resultSet.moveToNext());
        }
        resultSet.close();
        mydatabase.close();

        String[] simpleArray1 = new String[ TITLES.size() ];
        TITLES.toArray(simpleArray1);
        String[] simpleArray2 = new String[ SUB_TITLES.size() ];
        SUB_TITLES.toArray(simpleArray2);

        final myadapter adapter = new myadapter(simpleArray1,simpleArray2,ICONS,getbundle.getString("測試者id"),taskname);
        list.setAdapter(adapter);




        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View v,
                                           int position,
                                           long id) {
                return true;
            }
        });//長按


        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View v,
                                    int position,
                                    long id) {
                item = (String) list.getItemAtPosition(position).toString();
                positiontest=position;
                DialogFragment newFragment = new FireMissilesDialogFragment3();
                newFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");
            }
        });//短按

        FloatingActionButton mFab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                DialogFragment newFragment = new insertaction();
                newFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");
            }
        });//新增
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog,String name,String testid,String testname,String testsex,String testyear,String testmonth,String testday) {

        if(name.equals("insert")) {

        }


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog,String name ,String testid,String testname,String testsex,String testyear,String testmonth,String testday) {

        if(name.equals("insert")) {
            SQLiteDatabase mydatabase2 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
            mydatabase2.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR);");
            Cursor resultSet = mydatabase2.rawQuery("Select * from main where interviewerid = '"+interviewerid+"'",null);
            int te = resultSet.getCount()+1;
            resultSet.close();
            mydatabase2.close();


            SQLiteDatabase mydatabase3 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
            mydatabase3.execSQL("CREATE TABLE IF NOT EXISTS relation(interviewerid VARCHAR,testerid VARCHAR,testergroup VARCHAR, testername VARCHAR,testersex VARCHAR,testeryear VARCHAR,testermonth VARCHAR,testerday VARCHAR,generateddate VARCHAR);");

            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("interviewerid", interviewerid);
            contentValues2.put("testerid",  String.format("%03d", te));
            contentValues2.put("testergroup", testid);
            contentValues2.put("testername",testname );
            contentValues2.put("testersex", testsex);
            contentValues2.put("testeryear", testyear);
            contentValues2.put("testermonth", testmonth);
            contentValues2.put("testerday", testday);
            String date =(new SimpleDateFormat("yyyy/MM/dd")).format(new Date());
            contentValues2.put("generateddate", date);


            mydatabase3.insert("relation", null, contentValues2);
            mydatabase3.close();

            SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR,generateddate VARCHAR);");

            ContentValues contentValues = new ContentValues();
            contentValues.put("interviewerid", interviewerid);
            contentValues.put("testerid",  testid+String.format("%03d", te));
            contentValues.put("testname", testname);
            contentValues.put("adlprogress", "未開始");
            contentValues.put("iadlprogress", "未開始");
            contentValues.put("whoqolprogress", "未開始");
            contentValues.put("personaldataprogress", "未開始");
            contentValues.put("recordprogress", "未開始");
            contentValues.put("generateddate", date);
            mydatabase.insert("main", null, contentValues);
            //新增什麼資料? 可能是寫死在手機裡? 還是透過網路下載? 總之現在先隨機試試
            mydatabase.close();

            Intent intent = new Intent();
            intent.setClass(list.this, list.class);

            Bundle bundle = new Bundle();

            bundle.putString("測試者id",interviewerid);//

            intent.putExtras(bundle);
            startActivity(intent);
            list.this.finish();
        }


    }

//可能用不到刪除
    @Override
    public void deleteClick(DialogFragment dialog){

        SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR);");
        String delete="delete from main where interviewerid ='"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'";
        mydatabase.execSQL(delete);

        int cg=0;
        Cursor cursor = mydatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+"adl"+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                cg=1;
            }
        }
        if(cg==1) {
            delete="delete from adl where interviewerid ='"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'";
            mydatabase.execSQL(delete);
        }
        else{
        }

        cg=0;
        cursor = mydatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+"iadl"+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                cg=1;
            }
        }
        if(cg==1) {
            delete="delete from iadl where interviewerid ='"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'";
            mydatabase.execSQL(delete);
         }
        else{
         }

        cg=0;
        cursor = mydatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+"whoqol"+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                cg=1;
            }
        }
        if(cg==1) {
            delete="delete from whoqol where interviewerid ='"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'";
            mydatabase.execSQL(delete);
        }
        else{
        }

        cg=0;
        cursor = mydatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+"personaldata"+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                cg=1;
            }
        }
        if(cg==1) {
            delete="delete from personaldata where interviewerid ='"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'";
            mydatabase.execSQL(delete);
        }
        else{
        }

        cg=0;
        cursor = mydatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+"recorddata"+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                cg=1;
            }
        }
        if(cg==1) {
            delete="delete from recorddata where interviewerid ='"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'";
            mydatabase.execSQL(delete);
        }
        else{
        }
        cursor.close();
        mydatabase.close();

//需要加上刪除adl iadl whoql personaldata recorddata的部分


        Intent intent = new Intent();
        intent.setClass(list.this, list.class);

        Bundle bundle = new Bundle();

        bundle.putString("測試者id",interviewerid);//

        intent.putExtras(bundle);
        startActivity(intent);
        list.this.finish();
    }
    @Override
    public void updateClick(DialogFragment dialog){
        Intent intent = new Intent();
        intent.setClass(list.this, MainActivity.class);

        Bundle bundle = new Bundle();

        bundle.putString("測試者id",interviewerid);//
        bundle.putString("受訪者id",item.split(" ")[0]);//

        intent.putExtras(bundle);
        startActivity(intent);
        list.this.finish();
    }
    @Override
    public void uploadClick(DialogFragment dialog){

        int cc=0;
        //ArrayList<String> TITLES2 =new ArrayList<String>();
        //ArrayList<String> SUB_TITLES2 =new ArrayList<String>();
        SQLiteDatabase mydatabase = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS main(interviewerid VARCHAR,testerid VARCHAR,testname VARCHAR,adlprogress VARCHAR,iadlprogress VARCHAR,whoqolprogress VARCHAR,personaldataprogress VARCHAR,recordprogress VARCHAR,generateddate VARCHAR);");
        Cursor resultSet = mydatabase.rawQuery("Select * from main where interviewerid = '"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'",null);
        String dc2="";

        if(resultSet.getCount()!=0){
            resultSet.moveToFirst();
            do {
                String dc1 = resultSet.getString(1)+" "+resultSet.getString(2);//這裡要改成名字

                if(taskname.equals("基本資料")) {
                    dc2 = resultSet.getString(6);//和進度
                }
                else if(taskname.equals("問卷")) {
                    dc2 = resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5)+" ";//和進度
                }
                else if(taskname.equals("錄音")) {
                    dc2 = resultSet.getString(7);//和進度
                }
                else{
                    dc2 = "";//和進度
                }

                if(dc2.equals("未開始")||dc2.equals("未開始 未開始 未開始 ")) {//未開始 未完成 之類
                    cc++;
                }

                //TITLES2.add(dc1);
                //SUB_TITLES2.add(dc2);

            } while (resultSet.moveToNext());
        }
        resultSet.close();
        mydatabase.close();

        if(cc!=0){
            Toast.makeText(list.this, "你不能上傳 因為進度未完成", Toast.LENGTH_LONG).show();
        }
        else if(netWorkStateReceiver.vv==0){
            Toast.makeText(list.this, "你不能上傳 因為沒有網路", Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(list.this, "你可以上傳 因為有網路", Toast.LENGTH_LONG).show();

            //....
            //去sqlite抓出可以上傳的部分打包整理//還沒改完//先寫這個
            //....
            String dc3="";
            ArrayList<String> dc4=new ArrayList<String>();
            ArrayList<String> dc42=new ArrayList<String>();
            ArrayList<String> dc43=new ArrayList<String>();
            String [] rr= new String[]{"test","test","test","test"};



            if(taskname.equals("基本資料")) {

            }
            else if(taskname.equals("問卷")) {
                if(dc2.split(" ")[0].equals("adl完成")){
                    SQLiteDatabase mydatabase2 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                    Cursor resultSet2 = mydatabase2.rawQuery("Select * from adl where interviewerid = '"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'",null);

                    if(resultSet2.getCount()!=0){
                        resultSet2.moveToFirst();
                        do {
                            int cou=0;
                            while(cou<14) {
                                dc4.add(resultSet2.getString(cou));
                                cou++;
                            }
                        } while (resultSet2.moveToNext());
                    }
                    resultSet2.close();
                    mydatabase2.close();

                    String [] hh=new String[ dc4.size() ];
                    dc4.toArray(hh);

                    String temp;


                    if(item.split(" ")[0].substring(0,1).equals("9")){
                        temp="("+item.split(" ")[0].substring(2)
                                +","+"'"+item.split(" ")[0].substring(0,2)+"'";
                    }
                    else{
                        temp="("+item.split(" ")[0].substring(1)
                                +","+"'"+item.split(" ")[0].substring(0,1)+"'";
                    }


                            for(int gh=3;gh<=12;gh++)
                            {
                                temp+=","+hh[gh];
                            }

                    temp+=",'"+interviewerid+"','"+hh[13]+"')";

                    rr[0]=p1("app","203.64.84.32","w2017_a","(id,groupid,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,interviewerid,generateddate)",temp);
                    //p1(String databasename, String servername,String tablename,String insertcols,String insertvals)
                    //String insertcols="";//"(FirstName, LastName, Age)"
                    //String insertvals="";//"('admin', 'admin','adminstrator')"

                }
                if(dc2.split(" ")[1].equals("iadl完成")){
                    SQLiteDatabase mydatabase2 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                    Cursor resultSet2 = mydatabase2.rawQuery("Select * from iadl where interviewerid = '"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'",null);

                    if(resultSet2.getCount()!=0){
                        resultSet2.moveToFirst();
                        do {
                            int cou=0;
                            while(cou<12) {
                                dc42.add(resultSet2.getString(cou));
                                cou++;
                            }
                        } while (resultSet2.moveToNext());
                    }
                    resultSet2.close();
                    mydatabase2.close();

                    String [] hh=new String[ dc42.size() ];
                    dc42.toArray(hh);

                    String temp;


                    if(item.split(" ")[0].substring(0,1).equals("9")){
                        temp="("+item.split(" ")[0].substring(2)
                                +","+"'"+item.split(" ")[0].substring(0,2)+"'";
                    }
                    else{
                        temp="("+item.split(" ")[0].substring(1)
                                +","+"'"+item.split(" ")[0].substring(0,1)+"'";
                    }


                    for(int gh=3;gh<=10;gh++)
                    {
                        temp+=","+hh[gh];
                    }

                    temp+=",'"+interviewerid+"','"+hh[11]+"')";

                    rr[1]=p1("app","203.64.84.32","w2017_b","(id,groupid,B1,B2,B3,B4,B5,B6,B7,B8,interviewerid,generateddate)",temp);
                }
                if(dc2.split(" ")[2].equals("whoqol完成")){
                    SQLiteDatabase mydatabase2 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);
                    Cursor resultSet2 = mydatabase2.rawQuery("Select * from whoqol where interviewerid = '"+interviewerid+"' and testerid='"+item.split(" ")[0]+"'",null);

                    if(resultSet2.getCount()!=0){
                        resultSet2.moveToFirst();
                        do {
                            int cou=0;
                            while(cou<32) {
                                dc43.add(resultSet2.getString(cou));
                                cou++;
                            }
                        } while (resultSet2.moveToNext());
                    }
                    resultSet2.close();
                    mydatabase2.close();

                    String [] hh=new String[ dc43.size() ];
                    dc43.toArray(hh);

                    String temp;


                    if(item.split(" ")[0].substring(0,1).equals("9")){
                        temp="("+item.split(" ")[0].substring(2)
                                +","+"'"+item.split(" ")[0].substring(0,2)+"'";
                    }
                    else{
                        temp="("+item.split(" ")[0].substring(1)
                                +","+"'"+item.split(" ")[0].substring(0,1)+"'";
                    }


                    for(int gh=3;gh<=30;gh++)
                    {
                        temp+=","+hh[gh];
                    }

                    temp+=",'"+interviewerid+"','"+hh[31]+"')";

                    rr[2]=p1("app","203.64.84.32","w2017_c","(id,groupid,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,interviewerid,generateddate)",temp);
                }
            }
            else if(taskname.equals("錄音")) {

            }



            ArrayList<String> dc5=new ArrayList<String>();
            SQLiteDatabase mydatabase3 = openOrCreateDatabase("myactivity",MODE_PRIVATE,null);


            Cursor resultSet3;

            if(item.split(" ")[0].substring(0,1).equals("9")){
                resultSet3 = mydatabase3.rawQuery("Select * from relation where interviewerid = '"+interviewerid+"' and testerid='"+item.split(" ")[0].substring(2)+"' and testergroup='"+item.split(" ")[0].substring(0,2)+"'",null);
            }
            else{
                resultSet3 = mydatabase3.rawQuery("Select * from relation where interviewerid = '"+interviewerid+"' and testerid='"+item.split(" ")[0].substring(1)+"' and testergroup='"+item.split(" ")[0].substring(0,1)+"'",null);
            }

            if(resultSet3.getCount()!=0){
                resultSet3.moveToFirst();
                do {
                    int cou=0;
                    while(cou<8) {
                        dc5.add(resultSet3.getString(cou));
                        cou++;
                    }
                } while (resultSet3.moveToNext());
            }
            resultSet3.close();
            mydatabase3.close();

            String [] hh2=new String[ dc5.size() ];
            dc5.toArray(hh2);

            String temp2="(";

            for(int gh=0;gh<7;gh++)
            {
                if(gh!=6) {
                    if(gh==1){

                        temp2 += hh2[gh] + ",";
                    }
                    else{
                        temp2 += "'"+hh2[gh]+"'" + ",";
                    }
                }
                else{
                    temp2 += "'"+hh2[gh]+"'";
                }
            }

            temp2+=")";

            rr[3]=p1("app","203.64.84.32","relation","(interviewerid,testerid,testergroup,testername,testersex,testeryear,generateddate)",temp2);

            /*
            Intent intent = new Intent();
            intent.setClass(list.this, list.class);

            Bundle bundle = new Bundle();

            bundle.putString("測試者id",interviewerid);//

            intent.putExtras(bundle);
            startActivity(intent);
            list.this.finish();
*/
            String bh="";

            for(int y=0;y<4;y++){
                if(rr[y].equals("test")){

                }
                else if(rr[y].equals("Values have been inserted successfully")){
                    if(taskname.equals("問卷")){
                        if(y==0){
                            bh+="ADL成功上傳\n";
                        }
                        else if(y==1){
                            bh+="IADL成功上傳\n";
                        }
                        else if(y==2){
                            bh+="WHOQOL成功上傳\n";
                        }
                        else if(y==3){
                            bh+="關係資料成功上傳\n";
                        }
                    }
                    if(taskname.equals("基本資料")){
                        if(y==0){
                            bh+="基本資料成功上傳\n";
                        }
                        else if(y==1){
                            bh+="關係資料成功上傳\n";
                        }
                    }
                }
                else {
                    if(taskname.equals("問卷")){
                        if(y==0){
                            bh+="ADL失敗上傳\n";
                        }
                        else if(y==1){
                            bh+="IADL失敗上傳\n";
                        }
                        else if(y==2){
                            bh+="WHOQOL失敗上傳\n";
                        }
                        else if(y==3){
                            bh+="關係資料失敗上傳\n";
                        }
                    }
                    if(taskname.equals("基本資料")){
                        if(y==0){
                            bh+="基本資料失敗上傳\n";
                        }
                        else if(y==1){
                            bh+="關係資料失敗上傳\n";
                        }
                    }
                }
            }
            Toast.makeText(this,bh+"上傳動作結束", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void nothingClick(DialogFragment dialog){

    }


    //註冊廣播 監聽網路狀態
    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver(this);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        super.onResume();
    }
    //撤銷廣播
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        super.onPause();
    }


public String p1(String databasename, String servername,String tablename,String insertcols,String insertvals){
   try {
       upload temp = new upload(this);
       String tt = temp.execute(databasename, servername, tablename, insertcols, insertvals).get();
       return  tt;
   }
   catch (Exception e){
        return "error";
   }
    //Toast.makeText(list.this, result, Toast.LENGTH_LONG).show();
}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(list.this, list.class);

            Bundle bundle = new Bundle();

            bundle.putString("測試者id", interviewerid);//

            intent.putExtras(bundle);

            startActivity(intent);
            list.this.finish();
        }


        return super.onKeyDown(keyCode, event);
    }

}
