package com.example.user.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.security.PublicKey;

import static com.example.user.myapplication.R.layout.activity_list;

/**
 * Created by user on 2017/9/25.
 */

public class action extends DialogFragment {

    public interface actionListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    actionListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (actionListener) activity;

        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("test!")
                .setTitle("test");
        builder.setNegativeButton("更新", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            mListener.onDialogNegativeClick(action.this);
        }
    });
        builder.setPositiveButton("放棄", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            mListener.onDialogPositiveClick(action.this);
        }
    });
        return builder.create();
    }

}


/*
*
*
*
* */