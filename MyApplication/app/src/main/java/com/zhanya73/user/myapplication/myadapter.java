package com.zhanya73.user.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.beardedhen.androidbootstrap.BootstrapLabel;

import java.util.ArrayList;

/**
 * Created by user on 2017/9/24.
 */

public class myadapter extends BaseAdapter {
    private ArrayList<Integer> mList;
    private String[] mWords;
    private String[] mSubWords;
    private int[] mIcons;
    private  String minterviewer;
    String mtaskname;

    public myadapter(String[] words,String[] subwords,int[] icons,String interviewer,String taskname) {
        mWords = words;
        mSubWords = subwords;
        mIcons = icons;
        minterviewer=interviewer;
        mList = new ArrayList<>();
        mtaskname=taskname;
    }
    public void addItem(String title,String subtitle,Integer i){

        String rmwords[]= new String[mWords.length+1];
        for(int ii=0;ii<mWords.length;ii++){
            rmwords[ii]=new String(mWords[ii]);
        }
        rmwords[rmwords.length-1]=new String(title);
        mWords=rmwords;

        String rmsubwords[]= new String[mSubWords.length+1];
        for(int ii=0;ii<mSubWords.length;ii++){
            rmsubwords[ii]=new String(mSubWords[ii]);
        }
        rmsubwords[rmsubwords.length-1]=new String(subtitle);
        mSubWords=rmsubwords;



    }
    public void removeItem(int index){
        mList.remove(index);
    }
    @Override
    public int getCount() {
        return mSubWords.length;
    }
    @Override
    public Object getItem(int position) {
        return mWords[position];
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        }
            BootstrapLabel title = (BootstrapLabel) convertView.findViewById(R.id.titleb);
            BootstrapLabel subTitle = (BootstrapLabel) convertView.findViewById(R.id.sub_titleb);

            String text = (String) getItem(position);
            String subText = mSubWords[position];
            title.setText(text);
            subTitle.setText(subText);


            BootstrapLabel icon = (BootstrapLabel) convertView.findViewById(R.id.imgb);

            //由interviewer決定資料類型
            if(mtaskname.equals("基本資料")){
                //基本
                icon.setText("基本");
            }
            else if(mtaskname.equals("問卷")){
                //問卷
                icon.setText("問卷");
            }
            else if(mtaskname.equals("錄音")){
                //錄音
                icon.setText("錄音");
            }
        return convertView;
    }


}
