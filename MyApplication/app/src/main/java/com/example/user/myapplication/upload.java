package com.example.user.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by user on 2017/10/24.
 */

public class upload extends AsyncTask<String, Integer, Void> {



    private Context context;


    public upload(Context context) {
        this.context = context;

    }




    @Override
    protected Void doInBackground(String...  arg0) {

        try{
            String username = "test";
            String password = "5jru6j04m4au4a83";
            //這裡補充傳遞資料
            String databasename=(String)arg0[0];//還沒改完
            String servername=(String)arg0[1];
            String tablename=(String)arg0[2];
            String insertcols=(String)arg0[3];//$_POST["insertcols"]="(FirstName, LastName, Age)"
            String insertvals=(String)arg0[4];//$_POST["insertvals"]="('admin', 'admin','adminstrator')"


            String link="http://203.64.84.32:8080/back-end/insert.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("databasename", "UTF-8") + "=" +
                    URLEncoder.encode(databasename, "UTF-8");
            data += "&" + URLEncoder.encode("servername", "UTF-8") + "=" +
                    URLEncoder.encode(servername, "UTF-8");
            data += "&" + URLEncoder.encode("tablename", "UTF-8") + "=" +
                    URLEncoder.encode(tablename, "UTF-8");
            data += "&" + URLEncoder.encode("insertcols", "UTF-8") + "=" +
                    URLEncoder.encode(insertcols, "UTF-8");
            data += "&" + URLEncoder.encode("insertvals", "UTF-8") + "=" +
                    URLEncoder.encode(insertvals, "UTF-8");
            //這裡補充傳遞資料//還沒改完

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
        }
        catch(Exception e){
        }
        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }


    @Override
    protected void onPostExecute(Void result) {
        //Task you want to do on UIThread after completing Network operation
        //onPostExecute is called after doInBackground finishes its task.
    }
}
