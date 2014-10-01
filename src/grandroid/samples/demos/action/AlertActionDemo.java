package grandroid.samples.demos.action;

import grandroid.samples.demos.ActionFrame;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import grandroid.action.Action;
import grandroid.action.AlertAction;
import grandroid.action.GoAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class AlertActionDemo extends SampleFace {

    protected Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_alert);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Alert"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new AlertAction(AlertActionDemo.this).setData("System", "Keep edit or exit", new Action("Don't leave"), new GoAction(AlertActionDemo.this, "Leave", ActionFrame.class)).execute();
                    }
                });
                maker.escape();
            }
            maker.escape();
        }

    }

}
