package com.example.user.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 2017/10/13.
 */

public class insertaction extends DialogFragment {

    public interface insertactionListener {
        public void onDialogPositiveClick(DialogFragment dialog,String name,String testid,String testname,String testsex,String testyear);
        public void onDialogNegativeClick(DialogFragment dialog,String name,String testid,String testname,String testsex,String testyear);
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



    String group;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final TextView text1 = new TextView(getActivity());
        text1.setText("姓名:");
        text1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        final TextView text2 = new TextView(getActivity());
        text2.setText("id(種族):");
        text2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        final TextView text3 = new TextView(getActivity());
        text3.setText("id(數字):");
        text3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        final TextView text4 = new TextView(getActivity());
        text4.setText("性別:");
        text4.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        final TextView text5 = new TextView(getActivity());
        text5.setText("年次:");
        text5.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        final Spinner spinner = new Spinner(getActivity());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.D2));
        spinner.setAdapter(dataAdapter);

        final EditText input2 = new EditText(getActivity());
        input2.setInputType(InputType.TYPE_CLASS_TEXT);


        final EditText input3 = new EditText(getActivity());
        input3.setInputType(InputType.TYPE_CLASS_TEXT);


        final EditText input4 = new EditText(getActivity());
        input4.setInputType(InputType.TYPE_CLASS_NUMBER);


        Context context = getActivity();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(text1);
        layout.addView(input);

        layout.addView(text2);
        layout.addView(spinner);

        //layout.addView(text3);
        //layout.addView(input2);

        layout.addView(text4);
        layout.addView(input3);

        layout.addView(text5);
        layout.addView(input4);

        builder.setTitle("請輸入受訪者姓名與id!")
                .setView(layout)
        ;
        builder.setNegativeButton("新增活動", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(input.getText().toString().equals("")||input3.getText().toString().equals("")||input4.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "不得留空!\n請重填!", Toast.LENGTH_LONG).show();
                }
                else{
                    String temp = group.split("\\.")[0];
                    mListener.onDialogNegativeClick(insertaction.this,"insert",temp,input.getText().toString(),input3.getText().toString(),input4.getText().toString());
                }
            }
        });
        builder.setPositiveButton("放棄新增", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String temp = group.split("\\.")[0];
                mListener.onDialogPositiveClick(insertaction.this,"insert",temp,input.getText().toString(),input3.getText().toString(),input4.getText().toString());
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object obj = parent.getItemAtPosition(pos);
                group=obj.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        return builder.create();
    }
}
