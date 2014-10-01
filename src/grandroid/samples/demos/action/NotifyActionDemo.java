package grandroid.samples.demos.action;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import grandroid.action.NotifyAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
public class NotifyActionDemo extends SampleFace {

 
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
         
        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_notify);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {  maker.add(maker.createButton("Notify"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    new NotifyAction(NotifyActionDemo.this).setContent("Tips", "Smile make beautiful world!!").setActSound(true).useDefaultDialog(NotifyActionDemo.class, savedInstanceState).execute();

                }
            });maker.escape();}maker.escape();
        }

    }

}
