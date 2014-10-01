package grandroid.samples.demos.adapter;

import grandroid.adapter.SimpleRowAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.adapter.ObjectAdapter;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;

public class ObjectAdapterDemo extends SampleFace {

    protected String[] list = {"Banana", "Grape", "Pineapple", "Peach", "Watermelon", "Apple", "Bilberry", "Orange"};
//    protected int[] list2 = {R.drawable.ic_launcher,R.drawable.cat,R.drawable.ic_launcher};
//    protected String[] list = {"Banana", "Grape", "Pineapple"};
    ArrayList<String> arrayList;
    ObjectAdapter adapter, spinAdapter;
    SimpleRowAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayList = new ArrayList<String>();
        for (int i = 0; i < list.length; i++) {
            arrayList.add(list[i]);
        }

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_adapter_object);
            displayLine(maker);
            maker.addRowLayout(false, maker.layFW(1));
            {
                maker.addColLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
                {
                    maker.addColLayout().setPadding(20, 20, 20, 20);
                    {
                        maker.addColLayout(false, maker.layFW(1));
                        {
                            maker.getLastLayout().setBackgroundColor(Color.WHITE);
                            maker.addColLayout(false, maker.layFW(1));
                            {
                                maker.getLastLayout().setBackgroundColor(Color.GRAY);
                                maker.add(maker.createStyledText("Fruits").color(Color.WHITE).size(30).get(), maker.layFW());
                                maker.escape();
                            }
                            maker.addColLayout(false, maker.layFW(1));
                            {
                                adapter = new ObjectAdapter<String>(this, arrayList) {
                                    @Override
                                    public View createRowView(int index, String item) {
                                        LinearLayout layout_main = new LinearLayout(context);
                                        LayoutMaker m = new LayoutMaker(context, layout_main, false);

                                        m.add(m.createStyledText("").tag("fruit").color(Color.rgb(255, 193, 37)).get(), m.layFW());

                                        return layout_main;
                                    }

                                    @Override
                                    public void fillRowView(int index, View cellRenderer, String item) {
                                        findView(cellRenderer, "fruit", TextView.class).setText(item);
                                        Log.e("list", item);
                                    }
                                };
                                maker.addListView(adapter, maker.layFW());
                                maker.escape();
                            }
                            maker.escape();
                        }
                        maker.escape();
                    }
                    maker.escape();
                }

                spinAdapter = new ObjectAdapter<String>(this, arrayList) {

                    @Override
                    public View createRowView(int index, String item) {
                        LinearLayout layout_main = new LinearLayout(context);
                        LayoutMaker m = new LayoutMaker(context, layout_main, false);
                        m.add(m.createStyledText("").tag("select").center().get(), m.layFW());
                        return layout_main;
                    }

                    @Override
                    public void fillRowView(int index, View cellRenderer, String item) {
                        findView(cellRenderer, "select", TextView.class).setText(item);
                    }
                };
                maker.addSpinner(spinAdapter, maker.layFW(1));

                
                maker.escape();
            }
            maker.escape();
        }
    }

}
