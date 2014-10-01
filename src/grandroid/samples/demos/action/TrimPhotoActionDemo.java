package grandroid.samples.demos.action;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import grandroid.action.TrimPhotoAction;
import grandroid.image.PhotoAgent;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import java.io.File;

public class TrimPhotoActionDemo extends SampleFace {

    File file = new File("/sdcard/data1/downloadtmp.png");
    protected ImageView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.e("cus", ""+file.exists());

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_trim);
            maker.addColLayout(false, maker.layAbsolute(10, 10, 700, 150));
            {
                maker.add(maker.createButton("Trim")).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new TrimPhotoAction(TrimPhotoActionDemo.this, file, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) {

                            @Override
                            public void execute(Context context, PhotoAgent photoAgent) {

                                showView.setImageBitmap(photoAgent.getBitmap());

                            }
                        }.execute();
                    }
                });
                maker.escape();
            }
            maker.addColLayout(false, maker.layFF());
            {
                showView = maker.add(maker.createImage(ImageView.class, 0), maker.layFF());
                maker.escape();
            }
            maker.escape();
        }

    }

}
