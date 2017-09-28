package com.example.user.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 2017/9/24.
 */

public class myadapter extends BaseAdapter {

    private String[] mWords;
    private String[] mSubWords;
    private int[] mIcons;
    private  String minterviewer;

    public myadapter(String[] words,String[] subwords,int[] icons,String interviewer) {
        mWords = words;
        mSubWords = subwords;
        //這裡要改成根據interviewerid找到相關sqldata 然後把sqldata中的進度與受訪者id丟給兩個陣列mWords與mSubWords
        //現在只是外面丟字串資料進來這裡做測試
        mIcons = icons;
        minterviewer=interviewer;
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


        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView subTitle = (TextView) convertView.findViewById(R.id.sub_title);
        String text = (String) getItem(position);
        String subText = mSubWords[position];
        title.setText(text);
        subTitle.setText(subText);


        ImageView icon = (ImageView) convertView.findViewById(R.id.img);

        int resId;
        if(text.equals("t1")||text.equals("t6")){
            resId=mIcons[0];//基本
            icon.setImageResource(resId);
        }
        else if(text.equals("t2")||text.equals("t5")){
            resId=mIcons[1];//問卷
            icon.setImageResource(resId);
        }
        else if(text.equals("t3")||text.equals("t4")||text.equals("t7")){
            resId=mIcons[2];//錄音
            icon.setImageResource(resId);
        }

//這裡要改成依照text決定icon 也就是ifelse 若資料是問卷 則給予問卷資料的圖片resid

        return convertView;
    }


}
