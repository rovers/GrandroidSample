package grandroid.samples.demos.action;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import grandroid.action.AsyncAction;
import grandroid.action.GoAction;
import grandroid.image.ImageUtil;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import java.net.URL;

public class AsyncActionDefaultDemo extends SampleFace {

    Bitmap bmp, currBmp;
    ImageView showView;
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_async_default);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {maker.add(maker.createButton("Display"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        showView.setImageBitmap(currBmp);
                    }
                });
                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(10, 10, 700, 600));
            {
                showView = maker.add(maker.createImage(ImageView.class, 0), maker.layFF());
                maker.escape();
            }
            maker.escape();
            new AsyncAction<Bitmap>(AsyncActionDefaultDemo.this) {

                @Override
                public void afterExecution(Bitmap result) {
                    currBmp = result;
                    Log.e("cus", currBmp.toString());
                }

                @Override
                public boolean execute(Context context) {
                    try {
                        url = new URL("https://farm3.staticflickr.com/2597/4196875259_79d5dfb51f_o.jpg");
                        //bmp = ImageUtil.loadBitmap(url, true);
                        bmp = ImageUtil.downloadAndLoad(url, "/data1", "asyncTmp.png");
                        if (bmp != null) {
                            Log.e("cus", "bmp is not null");
                        } else {
                            Log.e("cus", "bmp is null");
                        }
                        setResult(bmp);
                    } catch (Exception exception) {
                        Log.e("cus", exception.toString());
                    }
                    return true;
                }
            }.cancelable().execute();

        }

    }
}
