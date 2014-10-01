package grandroid.samples.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.GoAction;
import grandroid.adapter.ObjectAdapter;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;
import grandroid.samples.demos.action.AlertActionDemo;
import grandroid.samples.demos.action.AsyncActionDemo;
import grandroid.samples.demos.action.DialActionDemo;
import grandroid.samples.demos.action.GoActionDemo;
import grandroid.samples.demos.action.MapActionDemo;
import grandroid.samples.demos.action.NavigationActionDemo;
import grandroid.samples.demos.action.NotifyActionDemo;
import grandroid.samples.demos.action.PhotoActionDemo;
import grandroid.samples.demos.action.PickPhotoActionDemo;
import grandroid.samples.demos.action.ShareActionDemo;
import grandroid.samples.demos.action.StoreActionDemo;
import grandroid.samples.demos.action.TrimPhotoActionDemo;
import grandroid.samples.demos.action.UrlActionDemo;

public class ActionFrame extends SampleFace {

    ObjectAdapter<String> adapter;
    protected String[] list = {"Go and Back", "Dial", "Notification", "Alert", "URL", "Map", "Store", "Navigation", "Pick Photo", "Photo", "Async", "Trim", "Share"};
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
                            new GoAction(ActionFrame.this, GoActionDemo.class, 1).execute();
                            break;
                        case 1:
                            new GoAction(ActionFrame.this, DialActionDemo.class, 1).execute();
                            break;
                        case 2:
                            new GoAction(ActionFrame.this, NotifyActionDemo.class, 1).execute();
                            break;
                        case 3:
                            new GoAction(ActionFrame.this, AlertActionDemo.class, 1).execute();
                            break;
                        case 4:
                            new GoAction(ActionFrame.this, UrlActionDemo.class, 1).execute();
                            break;
                        case 5:
                            new GoAction(ActionFrame.this, MapActionDemo.class, 1).execute();
                            break;
                        case 6:
                            new GoAction(ActionFrame.this, StoreActionDemo.class, 1).execute();
                            break;
                        case 7:
                            new GoAction(ActionFrame.this, NavigationActionDemo.class, 1).execute();
                            break;
                        case 8:
                            new GoAction(ActionFrame.this, PickPhotoActionDemo.class, 1).execute();
                            break;
                        case 9:
                            new GoAction(ActionFrame.this, PhotoActionDemo.class, 1).execute();
                            break;
                        case 10:
                            new GoAction(ActionFrame.this, AsyncActionDemo.class, 1).execute();
                            break;
                        case 11:
                            new GoAction(ActionFrame.this, TrimPhotoActionDemo.class, 1).execute();
                            break;
                        case 12:
                            new GoAction(ActionFrame.this, ShareActionDemo.class, 1).execute();
                            break;
                    }
                }

            };
            maker.addListView(adapter);
            maker.escape();
        }

    }

}
