package grandroid.samples.demos.activity;

import android.os.Bundle;
import grandroid.view.Face;
import grandroid.view.LayoutMaker;

public class FragmentDemo extends Face {

    protected LayoutMaker maker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker = new LayoutMaker(this);

    }

}
