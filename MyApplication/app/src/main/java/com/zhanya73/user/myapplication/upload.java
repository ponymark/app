package com.zhanya73.user.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by user on 2017/10/24.
 */

public class upload extends AsyncTask<String, Integer, String> {



    private Context context;
    private ProgressDialog progressBar;

    public upload(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        //執行前 設定可以在這邊設定
        super.onPreExecute();

        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Loading...");
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.show();
        //初始化進度條並設定樣式及顯示的資訊。
    }

    @Override
    protected String doInBackground(String...  arg0) {
        int progress = 0;
        StringBuilder sb = new StringBuilder();
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
            publishProgress(progress+=33 );

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));


            String line = null;
            publishProgress(progress+=33);
            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            reader.close();
            publishProgress(progress+=33);
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
        publishProgress(100);
        //最後達到100%
        return sb.toString();
    }


    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        super.onProgressUpdate(progress[0]);
    }


    @Override
    protected void onPostExecute(String result) {
        //Task you want to do on UIThread after completing Network operation
        //onPostExecute is called after doInBackground finishes its task.
        super.onPostExecute(result);
        progressBar.dismiss();
        //當完成的時候，把進度條消失

        if(result.equals("Values have been inserted successfully"))
        {
            //Toast.makeText(context, "上傳成功!", Toast.LENGTH_LONG).show();
        }
        else
        {
            //Toast.makeText(context, "上傳失敗!", Toast.LENGTH_LONG).show();
        }


    }
}
