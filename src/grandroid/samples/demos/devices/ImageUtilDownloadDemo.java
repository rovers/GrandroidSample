package grandroid.samples.demos.devices;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import grandroid.action.AsyncAction;
import grandroid.action.GoAction;
import grandroid.action.ToastAction;
import grandroid.image.ImageUtil;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageUtilDownloadDemo extends SampleFace {

    protected ImageView showView;
    protected Bitmap bmp, currBmp;
    protected URL url;
    protected TextView et;
    protected String fetchUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_devices_imageutil_download);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.addRowLayout(false, maker.layFW()).setPadding(10, 20, 10, 20);
                {
                    maker.add(maker.createStyledText("Your link").color(Color.WHITE).get(), maker.layFW(3));
                    et = maker.add(maker.createStyledEdit("").bgc(Color.LTGRAY).nowrap().right().maxLine(1).get(), maker.layFW(1));
                    maker.escape();
                }

                maker.add(maker.createButton("Download"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (et.getText().toString().length() < 1) {
                            new ToastAction(ImageUtilDownloadDemo.this).setMessage("unable link").execute();
                        } else {
                            new GoAction(ImageUtilDownloadDemo.this, ImageUtilDownloadDemo.class).addBundleObject("custom_url", et.getText().toString()).cancelAnimation().execute();
                        }
                    }
                });

                maker.escape();
            }

            showView = maker.add(maker.createImage(ImageView.class, 0), maker.layFF());
            maker.escape();
            new AsyncAction<Bitmap>(ImageUtilDownloadDemo.this) {

                @Override
                public void afterExecution(Bitmap result) {
                    currBmp = result;
                    showView.setImageBitmap(currBmp);
                }

                @Override
                public boolean execute(Context context) {
                    try {
                        initUrl();
                        bmp = ImageUtil.downloadAndLoad(url, "/data1", "downloadtmp.png");
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
            }.execute();

        }
    }

    private void initUrl() {
        try {
            try {
                Intent i = this.getIntent();
                Bundle b = i.getExtras();
                fetchUrl = b.getString("custom_url");
                url = new URL(fetchUrl);
            } catch (Exception e) {
                new ToastAction(ImageUtilDownloadDemo.this).setMessage("download failed").execute();
            }

        } catch (Exception e) {
            try {
                url = new URL("https://farm3.staticflickr.com/2828/10053622446_02b55d5953_b.jpg");
            } catch (MalformedURLException ex) {
                Logger.getLogger(ImageUtilDownloadDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
