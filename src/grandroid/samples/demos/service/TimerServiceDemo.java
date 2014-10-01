package grandroid.samples.demos.service;

import android.content.Intent;
import grandroid.service.TimerService;

public class TimerServiceDemo extends TimerService {

    int i;

    @Override
    protected long getServiceInterval() {
        return 5000;
    }

    @Override
    protected boolean execute() {
        i++;
//        Log.e("cus", "TimerService on start, " + i);

        sendMessageReceiver(i);
        return false;
    }

    protected void sendMessageReceiver(int i) {
        Intent intent = new Intent().setAction("GRANDROID_SERVICE_T");
        intent.putExtra("count_timer", i);
        sendBroadcast(intent);
    }

}
