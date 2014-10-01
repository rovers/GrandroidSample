package grandroid.samples.demos.action;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import grandroid.action.StoreAction;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import static grandroid.samples.demos.devices.ImageUtilLoaderDemo.loadBitmap;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreActionDemo extends SampleFace {

    String line = "http://ppt.cc/Bzlx@.jpg", fb = "https://www.facebook.com/images/fb_icon_325x325.png", youtube = "http://static.techspot.com/images2/downloads/topdownload/youtube.jpg";
    URL urlF, urlL, urlY;
    Bitmap bmpF, bmpL, bmpY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_store);
            maker.addColLayout(false, maker.layAbsolute(maker.getDisplayAgent().getWidth() / 6, 0, ((maker.getDisplayAgent().getWidth()) / 3)*2, LinearLayout.LayoutParams.WRAP_CONTENT)).setGravity(Gravity.CENTER);
            {
                maker.addRowLayout(false, maker.layFW(1));
                {
//                    try {
//                        urlF = new URL(fb);
//                        bmpF = loadBitmap(urlF, false);
//                    } catch (Exception ex) {
//                        Logger.getLogger(StoreActionDemo.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    maker.addImage(bmpF, maker.layFW(1));
                    maker.add(maker.createButton("Facebook"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            new StoreAction(StoreActionDemo.this, "com.facebook.katana").execute();
                        }
                    });
                    maker.escape();

                    

                }
                maker.addRowLayout(false, maker.layFW(1));
                {
//                    try {
//                        urlL = new URL(line);
//                        bmpL = loadBitmap(urlL, false);
//                    } catch (Exception ex) {
//                        Logger.getLogger(StoreActionDemo.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    maker.addImage(bmpL, maker.layFW(1));
                    maker.add(maker.createButton("Line"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            new StoreAction(StoreActionDemo.this, "jp.naver.line.android").execute();
                        }
                    });
                    maker.escape();
                }
                maker.addRowLayout(false, maker.layFW(1));
                {
//                    try {
//                        urlY = new URL(youtube);
//                        bmpY = loadBitmap(urlY, false);
//                    } catch (Exception ex) {
//                        Logger.getLogger(StoreActionDemo.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    maker.addImage(bmpY, maker.layFW(1));
                    maker.add(maker.createButton("Youtube"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            new StoreAction(StoreActionDemo.this, "com.google.android.youtube").execute();
                        }
                    });
                    maker.escape();
                }
                maker.escape();
            }
            maker.escape();
        }

    }

}
