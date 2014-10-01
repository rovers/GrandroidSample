
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class LayAbsoluteDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            maker.getLastLayout().setBackgroundColor(Color.WHITE);
            displayComment(maker, this, R.string.comment_layout_layabsoulte);
            maker.addColLayout(false, maker.layAbsolute(0, 0, 720, 300));
            {
                maker.add(maker.createButton("width=400"), maker.layAbsolute(0, 0, 400, 100));
                maker.add(maker.createButton("width=400"), maker.layAbsolute(320, 0, 400, 100));
                maker.add(maker.createButton("width=400"), maker.layAbsolute(160, 0, 400, 100));
                maker.escape();
            }
            maker.addRowLayout(false, maker.layAbsolute(20, 0, 680, 300));
            {
                maker.addImage(R.drawable.cat, maker.layAbsolute(0, 0, 75, 75)).setScaleType(ImageView.ScaleType.FIT_XY);
                maker.addImage(R.drawable.cat, maker.layAbsolute(10, 0, 150, 150)).setScaleType(ImageView.ScaleType.FIT_XY);
                maker.addImage(R.drawable.cat, maker.layAbsolute(10, 0, 225, 225)).setScaleType(ImageView.ScaleType.FIT_XY);
                maker.escape();
            }
            maker.addRowLayout(false, maker.layAbsolute(20, 0, 680, 300));
            {
                maker.addImage(R.drawable.cat, maker.layAbsolute(0, 0, 350, 350)).setScaleType(ImageView.ScaleType.FIT_XY);
                maker.addImage(R.drawable.cat, maker.layAbsolute(10, 0, 100, 200)).setScaleType(ImageView.ScaleType.FIT_XY);
                maker.addImage(R.drawable.cat, maker.layAbsolute(10, 0, 200, 1000)).setScaleType(ImageView.ScaleType.FIT_XY);
                maker.escape();
            }
            maker.escape();
        }
    }
}
