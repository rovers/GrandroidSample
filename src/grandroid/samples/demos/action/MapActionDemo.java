package grandroid.samples.demos.action;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import grandroid.action.MapAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class MapActionDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_map);
            maker.addColLayout(false, maker.layAbsolute(maker.getDisplayAgent().getWidth()/3, 0, maker.getDisplayAgent().getWidth()/3, LinearLayout.LayoutParams.WRAP_CONTENT)).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Taipei"), maker.layFW()).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //add action and set your location
                        new MapAction(MapActionDemo.this).setLocation(25.0854062, 121.5615012, 12).execute();
                    }
                });
                maker.add(maker.createButton("Tokyo"), maker.layFW()).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new MapAction(MapActionDemo.this).setLocation(35.673343, 139.710388, 12).execute();
                    }
                });
                maker.add(maker.createButton("New York"), maker.layFW()).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new MapAction(MapActionDemo.this).setLocation(40.7056308, -73.9780035, 12).execute();
                    }
                });
                maker.escape();
            }
            maker.escape();
        }

    }

}
