package grandroid.samples.demos.devices;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.phone.PhoneUtil;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class PhoneUtilDemo extends SampleFace {

    TextView netStatus, cameraStatus;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            loadStatus();
//        } catch (Exception e) {
//        }

        maker.addColLayout(false, maker.layFF());
        {
//            maker.add(maker.createStyledText(this.getString(R.string.PhoneUtil)).color(Color.GRAY).padding(20, 20, 20, 20).size(15).get(), maker.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, Config.CONTENT_HEIGHT));
            displayComment(maker, this, R.string.comment_devices_phone_util);
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Network Status: ").center().color(Color.WHITE).get(), maker.layFW(1));
                netStatus = maker.add(maker.createStyledText(convertAble(PhoneUtil.hasNetwork(this))).center().color(Color.WHITE).get(), maker.layFW(1));
                maker.escape();
            }
            maker.addLine(Color.GRAY, 2);
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Camera Status: ").center().color(Color.WHITE).get(), maker.layFW(1));
                cameraStatus = maker.add(maker.createStyledText(convertAble(PhoneUtil.hasCamera(this))).center().color(Color.WHITE).get(), maker.layFW(1));
                maker.escape();
            }
            maker.addLine(Color.GRAY, 2);
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Device ID: ").center().color(Color.WHITE).get(), maker.layFW(1));
                maker.add(maker.createStyledText(PhoneUtil.getDeviceID(this)).center().color(Color.WHITE).size(15).get(), maker.layFW(1));
                maker.escape();
            }
            maker.addLine(Color.GRAY, 2);
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("refresh"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        loadStatus();
                    }
                });
                maker.escape();
            }
            maker.escape();
        }
    }

    protected void loadStatus() {
        try {
            if (PhoneUtil.hasNetwork(this)) {
                netStatus.setText("enable");
            } else {
                netStatus.setText("disconnect");
            };
            if (PhoneUtil.hasCamera(this)) {
                cameraStatus.setText("enable");
            } else {
                cameraStatus.setText("disable");
            };
        } catch (Exception e) {
        }
    }
    
    protected String convertAble(boolean b){
        if (b) {
            return "enable";
        }
        return "disable";
    }
    
}
