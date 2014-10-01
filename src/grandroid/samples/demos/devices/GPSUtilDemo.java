package grandroid.samples.demos.devices;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import grandroid.action.Action;
import grandroid.action.MapAction;
import grandroid.geo.GPSUtil;
import grandroid.samples.demos.SampleFace;

public class GPSUtilDemo extends SampleFace {

    protected String currText = "";
    protected TextView showText;
    protected Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Location loc = GPSUtil.getLastPosition(GPSUtilDemo.this);
        final Action act = new Action(btn);
        maker.addColLayout(false, maker.layFF()).setGravity(Gravity.CENTER);
        {
            maker.addColLayout(false, maker.layFW(1));
            {
                maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER);
                {
                    maker.add(maker.createStyledText("Last:").center().size(16).color(Color.WHITE).get(), maker.layFW(2));
                    maker.add(maker.createStyledText(displayLoc(loc.getLatitude(),loc.getLongitude())).size(16).color(Color.WHITE).get(), maker.layFW(1));
                    maker.escape();
                }
                maker.addRowLayout(false, maker.layFW(1));
                {
                    maker.add(maker.createStyledText("Current:").center().size(16).color(Color.WHITE).get(), maker.layFW(2));
                    showText = maker.add(maker.createStyledText(currText).size(16).color(Color.WHITE).get(), maker.layFW(1));
                    maker.escape();
                }

                maker.escape();
            }
            maker.addColLayout(false, maker.layWW(1));
            {
                maker.add(maker.createButton("Get current location"), maker.layFW()).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
//                        currText = locate(GPSUtilDemo.this, null, false) ;
//                       
                        showText.setText(displayLoc(loc.getLatitude(), loc.getLongitude()));
                    }
                });
//            showText = maker.add(maker.createStyledText("").color(Color.WHITE).center().get(), maker.layFF());

                maker.add(maker.createButton("Open google map"), maker.layFW()).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new MapAction(GPSUtilDemo.this).setLocation(loc.getLatitude(), loc.getLongitude(), 15).execute();
                    }
                });

//                maker.add(maker.createButton("Use GPS"), maker.layFW()).setOnClickListener(new View.OnClickListener() {
//
//                    public void onClick(View v) {
//                        GPSUtil.locate(GPSUtilDemo.this, act, true);
//                    }
//                });
                maker.escape();
            }
            maker.escape();
        }
    }
    protected String displayLoc(double lat , double lon){
        return String.valueOf(lat) + " , " + String.valueOf(lon);
    }
    
}
