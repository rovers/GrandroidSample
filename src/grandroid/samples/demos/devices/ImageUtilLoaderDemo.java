package grandroid.samples.demos.devices;

import static android.R.id.input;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import grandroid.action.AsyncAction;
import static grandroid.image.ImageUtil.MAX_LENGTH;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtilLoaderDemo extends SampleFace {

    protected ImageView showView;
    protected Bitmap bmp, currBmp;
    protected URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_devices_imageutil_loader);
            maker.add(maker.createButton("Display"), maker.layFW()).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showView.setImageBitmap(currBmp);
                }
            });
            showView = maker.add(maker.createImage(ImageView.class, 0), maker.layFF());
            maker.escape();
            new AsyncAction<Bitmap>(ImageUtilLoaderDemo.this) {

                @Override
                public void afterExecution(Bitmap result) {
                    currBmp = result;
                    showView.setImageBitmap(currBmp);
//                    Log.e("cus", currBmp.toString());
                }

                @Override
                public boolean execute(Context context) {
                    try {
                        url = new URL("https://farm8.staticflickr.com/7124/7501177216_54ba40331a_k.jpg");
                        bmp = loadBitmap(url,false);
                        if (bmp != null) {
//                            Log.e("cus", "bmp is not null");
                        } else {
//                            Log.e("cus", "bmp is null");
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

    public static Bitmap sampling(URL url) throws Exception {
        Bitmap bitmap = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Accept", "*/*");
            
            while ((len = conn.getInputStream().read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
//          InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(is, null, o);

            Log.e("cus", o.outHeight + " and " + o.outWidth);
            //is.close();
            //Find the correct scale value. It should be the power of 2.

            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp <= MAX_LENGTH && height_tmp <= MAX_LENGTH) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            o.inJustDecodeBounds = false;
            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;

            is.close();
//            HttpURLConnection getConn = (HttpURLConnection) url.openConnection();
//            getConn.setConnectTimeout(30000);
//            getConn.setReadTimeout(30000);
//            getConn.setInstanceFollowRedirects(true);
//            getConn.setRequestProperty("Accept", "*/*");
//            InputStream getIs = getConn.getInputStream();
            InputStream getIs = new ByteArrayInputStream(baos.toByteArray());
            try {
                Log.e("cus", "try");
//                if (BitmapFactory.decodeStream(is, null, o2) != null) {

                bitmap = BitmapFactory.decodeStream(getIs, null, o2);

                Log.e("cus", o2.outHeight + "");

                if (bitmap == null) {
                    Log.e("cus", "no image");
                } else {
                    Log.e("cus", "have image");
                    Log.e("cus", bitmap.getHeight() + " and " + bitmap.getWidth());
                }

                return bitmap;

            } catch (Exception ex) {
                Log.e("cus", "catch");
                return loadBitmap(url, false);
            } finally {
                getIs.close();
                conn.disconnect();
//                getConn.disconnect();
            }
            //bitmap = BitmapFactory.decodeStream(is);

            //bitmap = BitmapFactory.decodeStream(instream);
        } catch (Exception e) {
            Log.e("grandroid", null, e);
            throw e;
        }

    }

    public static Bitmap loadBitmap(URL url) throws Exception {
        return loadBitmap(url, true);
    }

    public static Bitmap loadBitmap(URL url, boolean sampling) throws Exception {
        //
        if (sampling) {
            Log.e("cus", "enter true");
            return sampling(url);
            // it's okay        
        } else {
            Bitmap bitmap = null;
            Log.e("cus", "enter else");
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(20000);
                conn.setInstanceFollowRedirects(true);
                conn.setDoInput(true);
                conn.setDoOutput(false);
                conn.setRequestProperty("Accept", "*/*");
                conn.connect();
                InputStream is = conn.getInputStream();

                bitmap = BitmapFactory.decodeStream(is);
                Log.e("cus", "else ---" + bitmap.getHeight());
                conn.disconnect();
                //bitmap = BitmapFactory.decodeStream(instream);
            } catch (Exception e) {
                Log.e("grandroid", null, e);
                throw e;
            }
            return bitmap;
        }
    }

}
