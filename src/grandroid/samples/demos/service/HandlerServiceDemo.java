/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.service;

import android.content.Intent;
import grandroid.service.HandlerService;

public class HandlerServiceDemo extends HandlerService {

    int i;

    @Override
    protected long getServiceInterval() {
        return 2000;
    }

    @Override
    protected boolean execute() {
        i++;
//        Log.e("cus", "HandlerService is onStart, "+ i );
        sendMessageReceiver(i);
        return true;
    }

    protected void sendMessageReceiver(int i) {
        Intent intent = new Intent().setAction("GRANDROID_SERVICE_H");
        intent.putExtra("count_handler", i);
        sendBroadcast(intent);
    }

}
