package grandroid.samples.demos.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.adapter.ObjectAdapter;
import grandroid.adapter.RowAdapter;
import grandroid.adapter.UniversalAdapter;
import grandroid.adapter.ViewPagerAdapter;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;

public class ViewPagerAdapterDemo extends SampleFace {

    protected ViewPagerAdapter adapter;
    protected UniversalAdapter uniAdapter;
    protected ObjectAdapter objAdapter;
    protected LayoutInflater li;
    protected ArrayList viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewList = new ArrayList();
        viewList.add("1");
        viewList.add("2");
        viewList.add("3");

        objAdapter = new ObjectAdapter<String>(this, viewList) {

            @Override
            public void fillRowView(int index, View cellRenderer, String item) throws Exception {
                   findView(cellRenderer, "fruit", TextView.class).setText(item);
            }

            @Override
            public View createRowView(int index, String item) {
                LinearLayout layout = new LinearLayout(ViewPagerAdapterDemo.this);
                final LayoutMaker m = new LayoutMaker(ViewPagerAdapterDemo.this, layout, false);
                m.addColLayout(false, maker.layFW());
                {
                    m.add(m.createStyledText("").tag("fruit").color(Color.rgb(255, 193, 37)).get(), m.layFW());
                    m.escape();
                }
                return layout;
            }

        };

        maker.addColLayout(false, maker.layFF());
        {
            adapter = new ViewPagerAdapter(objAdapter){};
//            maker.addListView(adapter);
            maker.escape();
        }
    }

}
