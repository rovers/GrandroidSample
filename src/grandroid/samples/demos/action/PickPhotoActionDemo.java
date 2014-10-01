package grandroid.samples.demos.action;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import grandroid.action.PickPhotoAction;
import grandroid.image.PhotoAgent;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class PickPhotoActionDemo extends SampleFace {

    ImageView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         
         
        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_action_pickphoto);
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                 maker.add(maker.createButton("Pick Photo"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        new PickPhotoAction(PickPhotoActionDemo.this) {

                            @Override
                            public void execute(Context context, PhotoAgent photoAgent) {
                                onPickPhoto(photoAgent);
                            }
                        }.execute();

                    }
                });
                maker.escape();
            }
            //--

//   protected void onPhotoGet(PhotoAgent pa) {
//       File dirDM = new File(getActivity().getExternalFilesDir(null), "photo");
//       Config.logd("圖片Path：" + dirDM.getPath());
//       dirDM.mkdirs();
//       final String fileName = String.valueOf(System.currentTimeMillis()) + ".png";
//       File file = new File(dirDM, fileName);
//       Config.logd("File Path：" + file.getPath());
//       pa.save(file, Bitmap.CompressFormat.PNG); //fixSize(780, 1024)
//       final String storedPath;
//       try {
//           storedPath = pa.getStoredFile().getCanonicalPath();
//           if (storedPath != null) {
//               if (ivLayout != null) {
//                   ivLayout.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//                   ivLayout.setImageBitmap(pa.getBitmap());
//               }
//           }
//       } catch (IOException ex) {
//           Config.loge(ex.toString());
//       }
//
//   }
            //--
            maker.addColLayout(false, maker.layFF());
            {
                showView = maker.add(maker.createImage(ImageView.class, 0), maker.layFF());
                maker.escape();
            }
            maker.escape();
        }
    }

    protected void onPickPhoto(PhotoAgent pa) {
//        pa.save(pa.file("pickTmp"), Bitmap.CompressFormat.JPEG);
        showView.setImageBitmap(pa.getBitmap());
    }
}
