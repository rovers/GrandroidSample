package grandroid.samples.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.GoAction;
import grandroid.adapter.ObjectAdapter;
import grandroid.samples.demos.devices.GPSUtilDemo;
import grandroid.samples.demos.devices.ImageUtilDemo;
import grandroid.samples.demos.devices.PhoneAgentDemo;
import grandroid.samples.demos.devices.PhoneUtilDemo;
import grandroid.samples.demos.devices.PhotoAgentDemo;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;
public class DevicesFrame extends SampleFace {

    ObjectAdapter<String> adapter;
    protected String[] list = {"PhoneUtil", "ImageUtil", "PhotoAgent", "GPSUtil"};
    ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
         
        maker.addColLayout(false, maker.layFW());
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
                            new GoAction(DevicesFrame.this, PhoneUtilDemo.class, 1).execute();
                            break;
//                        case 1:
//                            new GoAction(DevicesFrame.this, PhoneAgentDemo.class, 1).execute();
//                            break;
                        case 1:
                            new GoAction(DevicesFrame.this, ImageUtilDemo.class, 1).execute();
                            break;

                        case 2:
                            new GoAction(DevicesFrame.this, PhotoAgentDemo.class, 1).execute();
                            break;
                        case 3:
                            new GoAction(DevicesFrame.this, GPSUtilDemo.class, 1).execute();
                            break;

                    }
                }

            };
            maker.addListView(adapter);
            maker.escape();
        }

    }

}
