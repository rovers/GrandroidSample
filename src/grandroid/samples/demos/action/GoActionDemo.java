package grandroid.samples.demos.action;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import grandroid.action.Action;
import grandroid.action.GoAction;
import grandroid.action.ToastAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class GoActionDemo extends SampleFace {

    protected String content = "GoAction is the method which can call other class, ...";
    protected String toast;
    protected Action actionOne, actionTwo;
    protected View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       
        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_go);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                //make a button
                maker.add(maker.createButton("GoAction"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //if you want to execute GoAction, you need to add the execute() end the sentence
                        new GoAction(GoActionDemo.this, BackActionDemo.class).execute();
                    }
                });
                maker.add(maker.createButton("GoAction"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
//                    if you want to execute GoAction, you need to add the execute() end the sentence
//                    new GoAction(GoActionDemo.this.execute();
                        new GoAction(GoActionDemo.this,"msg", BackActionDemo.class).execute();
                    }
                });
//                new Action() {
//                    @Override
//                    public boolean execute() {
//                        new ToastAction(GoActionDemo.this).setMessage("Toast").execute();
//                        return true;
//                    }
//                }.setActionName(toast);
                maker.add(maker.createButton("GoAction"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //if you want to execute GoAction, you need to add the execute() end the sentence
                        new GoAction(GoActionDemo.this, BackActionDemo.class).execute();
                    }
                });
                maker.escape();
            }
            maker.escape();
        }

    }

}
