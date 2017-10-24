package com.example.user.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2017/10/13.
 */

public class insertaction extends DialogFragment {

    public interface insertactionListener {
        public void onDialogPositiveClick(DialogFragment dialog,String name,String testid,String testname);
        public void onDialogNegativeClick(DialogFragment dialog,String name,String testid,String testname);
    }
    insertactionListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (insertaction.insertactionListener) activity;

        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

         final EditText input = new EditText(getActivity());
         input.setInputType(InputType.TYPE_CLASS_TEXT);

         final EditText input2 = new EditText(getActivity());
         input2.setInputType(InputType.TYPE_CLASS_TEXT);

        final TextView text1 = new TextView(getActivity());
        text1.setText("姓名:");
        text1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        final TextView text2 = new TextView(getActivity());
        text2.setText("id:");
        text2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        Context context = getActivity();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(text1);
        layout.addView(input2);

        layout.addView(text2);
        layout.addView(input);


        builder.setTitle("請輸入受訪者姓名與id!")
                .setView(layout)
        ;
        builder.setNegativeButton("新增活動", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(input.getText().toString().equals("")||input2.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "不得留空!\n請重填!", Toast.LENGTH_LONG).show();
                }
                else{
                    mListener.onDialogNegativeClick(insertaction.this,"insert",input.getText().toString(),input2.getText().toString());
                }
            }
        });
        builder.setPositiveButton("放棄新增", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogPositiveClick(insertaction.this,"insert",input.getText().toString(),input2.getText().toString());
            }
        });
        return builder.create();
    }
}
