package grandroid.samples.demos.service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.ToastAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class ServiceDemo extends SampleFace {

    Intent hs, ts;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ts = new Intent(this, TimerServiceDemo.class);
        hs = new Intent(this, HandlerServiceDemo.class);

        maker.addColLayout(false, maker.layFF());
        {
            maker.add(maker.createStyledText("HandlerService").center().color(Color.WHITE).get());
            maker.add(maker.createStyledText(this.getString(R.string.comment_service_handler)).color(Color.GRAY).padding(20, 20, 20, 20).size(15).get(), maker.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, Config.CONTENT_HEIGHT));
            maker.addRowLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Start")).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (startService(hs) == null) {
                            new ToastAction(ServiceDemo.this).setMessage("Service start").execute();
                            startService(hs);
                        } else {
                            startService(hs);
                        }
                    }
                }
                );
                maker.add(maker.createButton("Stop")).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (stopService(hs)) {
                            stopService(hs);
                            new ToastAction(ServiceDemo.this).setMessage("HandlerService is stopped").execute();
                        } else {
                            new ToastAction(ServiceDemo.this).setMessage("HandlerService is already stopped").execute();
                        }

                    }
                });
                maker.escape();
            }
            maker.addLine(Color.GRAY);
            maker.add(maker.createStyledText("TimerService").center().color(Color.WHITE).get());
            maker.add(maker.createStyledText(this.getString(R.string.comment_service_timer)).color(Color.GRAY).padding(20, 20, 20, 20).size(15).get(), maker.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, Config.CONTENT_HEIGHT));
            maker.addRowLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Start")).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (startService(ts) == null) {
                            new ToastAction(ServiceDemo.this).setMessage("Service start").execute();
                            startService(ts);
                        } else {
                            startService(ts);
                        }
                    }
                }
                );
                maker.add(maker.createButton("Stop")).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (stopService(ts)) {
                            stopService(ts);
                            new ToastAction(ServiceDemo.this).setMessage("TimerService is stopped").execute();
                        } else {
                            new ToastAction(ServiceDemo.this).setMessage("TimerService is already stopped").execute();
                        }

                    }
                });
                maker.escape();
            }
            maker.addLine(Color.GRAY);
            maker.escape();
        }
    }
}
