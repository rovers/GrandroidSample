package grandroid.samples.demos.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;

public class SideMenuDemo extends SampleFace {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn = maker.createButton("Menu");
        LayoutMaker m = maker.insertSideMenu(btn);

        m.addColLayout(false, m.layFF());
        {
            m.getLastLayout().setBackgroundColor(Color.rgb(175, 238, 238));
            m.addButton("Apple");
            m.addButton("Banana");
            m.addButton("Cola");
            m.escape();
        }

        maker.addColLayout(false, maker.layFF());
        {

            maker.getLastLayout().setBackgroundColor(Color.WHITE);
            maker.addColLayout(false, maker.layAbsolute(0, 0, 720, 150));
            {
                maker.getLastLayout().setBackgroundColor(Color.rgb(175, 238, 238));
                btn = maker.add(btn, maker.layAbsolute(0, Gravity.LEFT, 150, 100));

            }

            maker.escape();
        }

        maker.escape();
    }
}
