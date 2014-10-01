package grandroid.samples.demos.layout;

import android.graphics.Color;
import android.os.Bundle;
import grandroid.samples.demos.SampleFace;

public class LayFrameDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addFrame(maker.layFF());
        {
            maker.addFrame(maker.layFrameAbsolute(0, 0, 720, 200));
            {
                maker.getLastLayout().setBackgroundColor(Color.WHITE);
                maker.addTextView("LayFrame");
                maker.escape();
            }
            maker.addFrame(maker.layFrameAbsolute(0, 200, 720, 200));
            {
                maker.getLastLayout().setBackgroundColor(Color.YELLOW);
                maker.addTextView("LayFrame2");
                maker.escape();
            }
            maker.addFrame(maker.layFrameAbsolute(0, 400, 360, 200));
            {
                maker.getLastLayout().setBackgroundColor(Color.WHITE);
                maker.addTextView("LayFrame3");
                maker.escape();
            }
            maker.addFrame(maker.layFrameAbsolute(360, 400, 360, 200));
            {
                maker.getLastLayout().setBackgroundColor(Color.GREEN);
                maker.addTextView("LayFrame4");
                maker.escape();
            }
            maker.addFrame(maker.layFrameAbsolute(180, 690, 360, 360));
            {
                maker.getLastLayout().setBackgroundColor(Color.YELLOW);
                maker.addTextView("LayFrame5");
                maker.escape();
            }
            maker.addFrame(maker.layFrameAbsolute(0, 1100, 720, 100));
            {
                maker.getLastLayout().setBackgroundColor(Color.WHITE);
                maker.addTextView("LayFrame6");
                maker.escape();
            }
            maker.escape();
        }
    }

}
