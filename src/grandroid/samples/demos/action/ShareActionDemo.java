package grandroid.samples.demos.action;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import grandroid.action.ShareAction;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class ShareActionDemo extends SampleFace {

    protected ImageView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_share);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Share to"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {
                    
                    public void onClick(View v) {
                        //add and set the title/content
                        new ShareAction(ShareActionDemo.this).setTitle("share").setContent("I have a good news with you").execute();
                    }
                });
                maker.escape();
            }
            maker.escape();
        }

    }

}
