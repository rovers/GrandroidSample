package grandroid.samples.demos.action;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import grandroid.action.URLAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class UrlActionDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_url);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Url"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new URLAction(UrlActionDemo.this, "http://www.google.com").execute();
                    }
                });
                maker.escape();
            }
            maker.escape();
        }
    }

}
