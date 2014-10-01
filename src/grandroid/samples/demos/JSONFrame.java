package grandroid.samples.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.GoAction;
import grandroid.adapter.ObjectAdapter;
import grandroid.samples.demos.json.ApacheMonDemo;
import grandroid.samples.demos.json.JSONConverterDemo;
import grandroid.samples.demos.json.MonDemo;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;

public class JSONFrame extends SampleFace {

    ObjectAdapter<String> adapter;
    protected String[] list = {"Converter", "Mon", "ApacheMon"};
    ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            array = new ArrayList<String>();
            for (int i = 0; i < list.length; i++) {
                array.add(list[i]);
            }
            adapter = new ObjectAdapter<String>(this, array) {

                @Override
                public View createRowView(int index, String item) {
                    LinearLayout layout_main = new LinearLayout(context);
                    LayoutMaker m = new LayoutMaker(context, layout_main, false);
                    m.addColLayout(false, m.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, Config.LIST_HEIGHT)).setGravity(Gravity.CENTER_VERTICAL);
                    {
                        m.add(m.createStyledText("").tag("item").color(Color.WHITE).padding(Config.LIST_LEFT_PADDING, 10, 0, 10).get());
                        displayListLine(m);
                    }
                    return layout_main;
                }

                @Override
                public void fillRowView(int index, View cellRenderer, String item) {
                    findView(cellRenderer, "item", TextView.class).setText(item);

                }

                @Override
                public void onClickItem(int index, View view, String item) {
                    switch (index) {
                        case 0:
                            new GoAction(JSONFrame.this, JSONConverterDemo.class, 1).execute();
                            break;
//                        case 1:
//                            new GoAction(JSONFrame.this, JSONUtilDemo.class, 1).execute();
//                            break;
                        case 1:
                            new GoAction(JSONFrame.this, MonDemo.class, 1).execute();
                            break;
                        case 2:
                            new GoAction(JSONFrame.this, ApacheMonDemo.class, 1).execute();
                            break;

                    }
                }

            };
            maker.addListView(adapter);
            maker.escape();
        }

    }

}
