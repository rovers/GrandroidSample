package grandroid.samples.demos.devices;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import grandroid.dialog.CommandPickModel;
import grandroid.image.ImageUtil;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class ImageUtilSaveDemo extends SampleFace {

    String[] choseWay = {"Save Photo", "Cancel"};
    ImageView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF()).setGravity(Gravity.CENTER_VERTICAL);
        {
            displayComment(maker, this, R.string.comment_devices_imageutil_save);
            maker.add(maker.createStyledText("Long-Press on Picture").center().color(Color.WHITE).get(),maker.layFW());
            showView = maker.addImage(R.drawable.fall, maker.layFF());
            showView.setOnLongClickListener(new View.OnLongClickListener() {

                public boolean onLongClick(View v) {

                    pickObject(new CommandPickModel("", choseWay) {
                        @Override
                        public void onCommand(int i) {
                            switch (i) {
                                case 0:
                                    savePhoto();
                                    break;
                                case 1:
                                    break;
                            }
                        }

                    });
                    return false;
                }
            });

            maker.escape();
        }
    }

    @Override
    public void pickObject(CommandPickModel cpm) {
        new AlertDialog.Builder(ImageUtilSaveDemo.this).setItems(cpm.getStringArray(), cpm).setTitle(cpm.getTitle()).show();
    }

    public void savePhoto() {
        showView.setDrawingCacheEnabled(true);
        ImageUtil.saveBitmap(showView.getDrawingCache(), "/data1");
        showView.setDrawingCacheEnabled(false);
    }
}
