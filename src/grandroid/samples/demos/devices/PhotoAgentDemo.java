package grandroid.samples.demos.devices;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import grandroid.image.PhotoAgent;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class PhotoAgentDemo extends SampleFace {

    ImageView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final PhotoAgent currBmp = new PhotoAgent(BitmapFactory.decodeResource(getResources(), R.drawable.cat_yellow));

        maker.addColLayout(false, maker.layFF()).setGravity(Gravity.CENTER);
        {
//            maker.add(maker.createStyledText(this.getString(R.string.PhotoAgent)).color(Color.GRAY).padding(20, 20, 20, 20).size(Config.TEXT_SIZE).get(), maker.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, Config.CONTENT_HEIGHT));
            displayComment(maker, this, R.string.comment_devices_photo_agent);
            maker.addRowLayout(false, maker.layWW(1)).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Rotate"), maker.layFW()).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        currBmp.rotate(90);
                        showView.setImageBitmap(currBmp.getBitmap());
                    }
                });
                maker.add(maker.createButton("Square"), maker.layFW()).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        currBmp.square(500);
                        showView.setImageBitmap(currBmp.getBitmap());
                    }
                });
                maker.add(maker.createButton("Border Radius"), maker.layFW()).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        currBmp.round(500, 100);
                        showView.setImageBitmap(currBmp.getBitmap());
                    }
                });
                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(0, 0, maker.getDisplayAgent().getWidth(), (maker.getDisplayAgent().getHeight() / 2))).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.addColLayout(false, maker.layFF()).setGravity(Gravity.CENTER);
                {
                    showView = maker.addImage(currBmp.getBitmap(), maker.layWW(1));
                    maker.escape();
                }
                maker.escape();
            }
            maker.escape();
        }
    }

}
