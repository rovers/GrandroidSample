package grandroid.samples.demos.view;

import android.graphics.Color;
import android.os.Bundle;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;

public class CreateViewDemo extends SampleFace {

    protected String[] radioList = {"radio1", "radio2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout();
        {
            maker.getLastLayout().setBackgroundColor(Color.WHITE);

            maker.add(maker.createTextView("you can create the image or button,some object in Layout."));

            maker.addImage(R.drawable.cat_yellow, maker.layWW(0));

            maker.add(maker.createButton("Button"));

            maker.addLine(Color.GRAY);
            maker.addRowLayout(false, maker.layFW());
            {
                maker.addTextView("check box");
                maker.addCheckBox(true);
                maker.addCheckBox(true);
                maker.escape();
            }
            maker.addRowLayout(false, maker.layFW());
            {
                maker.addTextView("radio group");
                maker.add(maker.createRadioGroup(radioList));
                maker.escape();
            }
            maker.escape();
        }
    }
}
