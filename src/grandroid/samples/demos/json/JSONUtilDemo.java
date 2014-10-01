package grandroid.samples.demos.json;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.AsyncAction;
import grandroid.adapter.JSONAdapter;
import grandroid.adapter.ObjectAdapter;
import grandroid.json.JSONUtil;
import grandroid.net.Mon;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtilDemo extends SampleFace {

    protected JSONAdapter JSONAdapter;
    protected ObjectAdapter objectAdapter;
    protected ArrayList<String> list;
    protected String filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<String>();
        list.add("userId");
        list.add("name");
        list.add("age");
        list.add("team");

        maker.addColLayout(false, maker.layFF());
        {
            maker.addColLayout(false, maker.layAbsolute(0, 0, 720, 220));
            {
                maker.getLastLayout().setBackgroundColor(Color.GRAY);
                maker.addRowLayout(false, maker.layAbsolute(0, 5, 720, 100)).setGravity(Gravity.CENTER_VERTICAL);
                {
                    maker.add(maker.createStyledText("Sort").center().get(), maker.layFW(1));
                    objectAdapter = new ObjectAdapter<String>(this, list) {
                        @Override
                        public View createRowView(int index, String item) {
                            LinearLayout layout_main = new LinearLayout(context);
                            final LayoutMaker m = new LayoutMaker(context, layout_main, false);

                            m.addRowLayout(false, m.layFF());
                            {
                                m.add(m.createStyledText("").tag("select").center().get(), m.layFF());
                                m.escape();
                            }
                            return layout_main;
                        }

                        @Override
                        public void fillRowView(int index, View cellRenderer, String item) {
                            findView(cellRenderer, "select", TextView.class).setText(item);
                        }
                    };
                    maker.addSpinner(objectAdapter, maker.layFW(1)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            filter = parent.getSelectedItem().toString();
                            sortData();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    maker.escape();
                }

                maker.addLine(Color.LTGRAY);
                maker.addRowLayout(false, maker.layAbsolute(0, 10, 720, LinearLayout.LayoutParams.MATCH_PARENT)).setGravity(Gravity.CENTER_VERTICAL);
                {
                    maker.add(maker.createStyledText("remove item").center().get(), maker.layFW(1));
                    maker.add(maker.createButton("Delete"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            removeData();
                        }
                    });
                    maker.escape();
                }

                maker.escape();
            }
            maker.addRowLayout(false, maker.layAbsolute(0, 0, 720, 60)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Id").size(15).color(Color.RED).get(), maker.layAbsolute(20, 0, 50, 60));
                maker.add(maker.createStyledText("name").size(15).color(Color.WHITE).get(), maker.layAbsolute(100, 0, 100, 60));
                maker.add(maker.createStyledText("age").size(15).color(Color.WHITE).get(), maker.layAbsolute(120, 0, 50, 60));
                maker.add(maker.createStyledText("team").size(15).color(Color.WHITE).get(), maker.layAbsolute(130, 0, 280, 60));
                maker.escape();
            }
            maker.addLine(Color.WHITE);
            maker.addColLayout(false, maker.layFF());
            {
                JSONAdapter = new JSONAdapter(JSONUtilDemo.this, new JSONArray()) {
                    @Override
                    public View createRowView(int index, JSONObject item) {
                        LinearLayout layout = new LinearLayout(context);
                        final LayoutMaker m = new LayoutMaker(context, layout, false);

                        m.add(m.createStyledText("").tag("userId").size(13).color(Color.RED).get(), m.layAbsolute(20, 0, 50, 70));
                        m.add(m.createStyledText("").tag("name").center().size(13).color(Color.WHITE).get(), m.layAbsolute(0, 0, 280, 70));
                        m.add(m.createStyledText("").tag("age").size(13).color(Color.WHITE).get(), m.layAbsolute(0, 0, 50, 70));
                        m.add(m.createStyledText("").tag("team").size(13).center().color(Color.WHITE).get(), m.layAbsolute(0, 0, 280, 70));

                        return layout;
                    }

                    @Override
                    public void fillRowView(int index, View cellRenderer, JSONObject item) throws JSONException {
                        findView(cellRenderer, "userId", TextView.class).setText(String.valueOf(item.getInt("userId")));
                        findView(cellRenderer, "name", TextView.class).setText(item.getString("name"));
                        findView(cellRenderer, "age", TextView.class).setText(item.getString("age"));
                        findView(cellRenderer, "team", TextView.class).setText(item.getString("team"));
                    }
                };
                maker.addListView(JSONAdapter, maker.layFF());
                maker.escape();
                new AsyncAction<JSONArray>(
                        this) {
                            @Override
                            public void afterExecution(JSONArray result) {
                                JSONAdapter.setArray(result);
                                JSONAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public boolean execute(Context context) {
                                JSONObject obj = null;
                                try {
                                    obj = new Mon("http://www.json-generator.com/api/json/get/bUpnyUCUSW?indent=2").sendAndWrap();
                                    setResult(obj.getJSONArray("users"));
                                } catch (Exception exception) {

                                }
                                return true;
                            }
                        }
                        .execute();
            }
            maker.escape();
        }
    }

    private void sortData() {
//        if (filter != "userId") 
//            JSONAdapter.setArray(JSONUtil.sort(JSONAdapter.getArray(), filter));
            JSONAdapter.notifyDataSetChanged();
//        } else {
//            JSONArray arr = JSONAdapter.getArray();
////            
//        }
    }

    private void removeData() {
        try {
            JSONAdapter.setArray(JSONUtil.removeArrayItem(JSONAdapter.getArray(), 0));
            JSONAdapter.notifyDataSetChanged();
        } catch (Exception e) {
//            new ToastAction(this, "no more item");
        }

    }

}
