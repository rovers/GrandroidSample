package grandroid.samples.demos.action;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import grandroid.action.ToastAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class ToastActionDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFW());
        {
            displayComment(maker, this, R.string.comment_action_toast);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Toast"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new ToastAction(ToastActionDemo.this).setMessage("Hello").execute();
                    }
                });
                maker.escape();
            }
            maker.escape();
        }

    }

}
