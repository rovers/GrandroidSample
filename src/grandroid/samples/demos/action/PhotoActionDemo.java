package grandroid.samples.demos.action;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import grandroid.action.PhotoAction;
import grandroid.image.PhotoAgent;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class PhotoActionDemo extends SampleFace {

    protected ImageView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_photo);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Photo"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new PhotoAction(PhotoActionDemo.this) {

                            @Override
                            protected void execute(Context context, PhotoAgent photoAgent) {
//                            photoAgent.save(photoAgent.dir("/data1").file("tmp2"),Bitmap.CompressFormat.PNG);
//                            photoAgent.save(photoAgent.dir("/data1").file("photoTmp"), Bitmap.CompressFormat.JPEG);
//                            PhotoAgent pa = photoAgent;

                                photoAgent.fixSize(600, 600);
                                showView.setImageBitmap(photoAgent.getBitmap());
                            }
                        }.execute(PhotoActionDemo.this);
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
