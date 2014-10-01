/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import grandroid.action.ToastAction;

/**
 *
 * @author Jack Huang
 */
public class MyBR extends BroadcastReceiver {

    int t, h;

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals("GRANDROID_SERVICE_T") || intent.getAction().equals("GRANDROID_SERVICE_H")) {
//            new ToastAction(context).setMessage("HS: "+String.valueOf(intent.getExtras().getInt("count_handler"))+" , "+ "TS: " + String.valueOf(intent.getExtras().getInt("count_timer")));
//        } else { }

        if (intent.getAction().equals("GRANDROID_SERVICE_T")) {
            new ToastAction(context).setMessage("TS: " + String.valueOf(intent.getExtras().getInt("count_timer"))).execute();
        }
        if (intent.getAction().equals("GRANDROID_SERVICE_H")) {
            new ToastAction(context).setMessage("HS: " + String.valueOf(intent.getExtras().getInt("count_handler"))).execute();
        }

    }
}
