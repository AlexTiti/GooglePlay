package com.example.administrator.googleplay.input;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/12
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class BroadReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null){
            Log.e("===",intent.getAction()+"=="+intent.getStringExtra("AAA"));
        }

    }
}
