package com.example.user.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by user on 2017/10/17.
 */

public class FireMissilesDialogFragment3  extends DialogFragment {

    public interface listListener {
        public void deleteClick(DialogFragment dialog);
        public void updateClick(DialogFragment dialog);
        public void uploadClick(DialogFragment dialog);
        public void nothingClick(DialogFragment dialog);

    }
    listListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (listListener) activity;

        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] options = {"刪除","上傳","更新","什麼都不做"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("選擇動作")

        .setItems(options, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){//delete
                    mListener.deleteClick(FireMissilesDialogFragment3.this);
                }
                else if(which==1){//upload
                    mListener.uploadClick(FireMissilesDialogFragment3.this);
                }
                else if(which==2){//update
                    mListener.updateClick(FireMissilesDialogFragment3.this);
                }
                else if(which==3){//nothing
                    mListener.nothingClick(FireMissilesDialogFragment3.this);
                }
            }
        });
        return builder.create();
    }




}
