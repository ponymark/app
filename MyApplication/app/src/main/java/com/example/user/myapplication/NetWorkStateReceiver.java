package com.example.user.myapplication;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

/**
 * Created by user on 2017/7/13.
 */

public class NetWorkStateReceiver extends BroadcastReceiver {
    public Activity activity;

    public NetWorkStateReceiver(Activity _activity){
        this.activity = _activity;
    }
    //廣播接收? 這裡不太懂
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        BootstrapButton bt1=(BootstrapButton )this.activity.findViewById(R.id.button4);
        if(connMgr.getActiveNetworkInfo()==null){//沒有連線 null
            bt1.setText("不行上傳");
            bt1.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }
        else{
            bt1.setText(connMgr.getActiveNetworkInfo().isAvailable()?"可以上傳":"不行上傳");//true 有且允許可用連線 false 有但是不允許不可用連線
            bt1.setBootstrapBrand(connMgr.getActiveNetworkInfo().isAvailable()?DefaultBootstrapBrand.WARNING:DefaultBootstrapBrand.DANGER);
        }
    }
}
