package com.example.user.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by user on 2017/7/27.
 */

public class FireMissilesDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("請至少選擇一個選項!")
                .setTitle("警告");

// Add the buttons
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        //builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
        //    public void onClick(DialogInterface dialog, int id) {
        //        // User cancelled the dialog
        //    }
        //});


        // 3. Get the AlertDialog from create()
        return builder.create();
    }
}
