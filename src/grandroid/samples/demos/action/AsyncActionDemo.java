package grandroid.samples.demos.action;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.GoAction;
import grandroid.adapter.ObjectAdapter;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;

public class AsyncActionDemo extends SampleFace {

    ObjectAdapter<String> adapter;
    protected String[] list = {"Default", "Custom"};
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
                    m.addColLayout(false, m.layAbsolute(0, 30, LinearLayout.LayoutParams.MATCH_PARENT, 80));
                    {
                        m.add(m.createStyledText("").tag("item").color(Color.WHITE).get(), m.layAbsolute(30, 0));
                        m.addColLayout(false, m.layAbsolute(30, 5, 580, 1));
                        {
                            m.addLine(Color.GRAY);
                            m.escape();
                        }
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
                            new GoAction(AsyncActionDemo.this, AsyncActionDefaultDemo.class, 1).execute();
                            break;
                        case 1:
                            new GoAction(AsyncActionDemo.this, AsyncActionCustomDemo.class, 1).execute();
                            break;

                    }
                }

            };
            maker.addListView(adapter);
            maker.escape();
        }

    }
}
