package grandroid.samples.demos.layout;

import android.graphics.Color;
import android.os.Bundle;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class FrameLayoutDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        maker.setDesigner(new ViewDesigner() {
//            @Override
//            public TextView stylise(TextView tv) {
//                super.stylise(tv);
//                tv.setTextColor(Color.WHITE);
//                return tv;
//            }
//        });
        maker.addFrame(maker.layFF());
        {
            maker.addFrame(maker.layFrameAbsolute(0, 0, 720, maker.getDisplayAgent().getHeight()));
            {

                maker.getLastLayout().setBackgroundColor(Color.YELLOW);
                maker.escape();
            }
            maker.addFrame(maker.layFrameAbsolute(100, 100, 520, maker.getDisplayAgent().getHeight()/2));
            {

                maker.getLastLayout().setBackgroundColor(Color.GREEN);
                maker.escape();
            }
            maker.add(maker.createButton("Button 1"), maker.layFrameAbsolute(350, 80, 200, 80));
            maker.add(maker.createButton("Button 2"), maker.layFrameAbsolute(280, 100, 200, 80));
            maker.add(maker.createButton("Button 3"), maker.layFrameAbsolute(210, 120, 200, 80));
            maker.add(maker.createButton("Button 4"), maker.layFrameAbsolute(140, 140, 200, 80));
            maker.add(maker.createButton("Button 5"), maker.layFrameAbsolute(70, 160, 200, 80));
            maker.add(maker.createButton("Button 6"), maker.layFrameAbsolute(0, 180, 200, 80));

            maker.addFrame(maker.layFrameAbsolute(50, 600, 620, 400));
            {

                maker.getLastLayout().setBackgroundColor(Color.argb(200, 255, 255, 255));
                maker.add(maker.createStyledText(this.getString(R.string.comment_layout_framelayout)).get());
                maker.escape();
            }
            maker.escape();
        }

    }
}
