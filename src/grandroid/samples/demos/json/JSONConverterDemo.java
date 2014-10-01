package grandroid.samples.demos.json;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.AsyncAction;
import grandroid.adapter.JSONAdapter;
import grandroid.adapter.ObjectAdapter;
import grandroid.net.Mon;
import grandroid.view.LayoutMaker;
import grandroid.json.JSONConverter;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class JSONConverterDemo extends SampleFace {

    JSONAdapter jAdapter;
    ObjectAdapter objAdapter;
    Object obj;
    JSONObject jObj;
    ArrayList<UsersData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_json_converter);
            displayLine(maker);

            maker.addColLayout(false, maker.layFW(1));
            {
                maker.add(maker.createStyledText("JSON format").color(Color.WHITE).get(), maker.layFW());
                maker.addColLayout(false, maker.layAbsolute(0, 0, maker.getDisplayAgent().getWidth(), maker.getDisplayAgent().getHeight()/3));
                {
                    jAdapter = new JSONAdapter(this, new JSONArray()) {
                        @Override
                        public View createRowView(int index, JSONObject item) {
                            LinearLayout layout_main = new LinearLayout(context);
                            LayoutMaker m = new LayoutMaker(context, layout_main, false);
                            m.add(m.createStyledText("").tag("id").size(15).center().color(Color.RED).get(), m.layFW(3));
                            m.add(m.createStyledText("").tag("name").size(15).center().color(Color.WHITE).get(), m.layFW(2));
                            m.add(m.createStyledText("").tag("team").size(15).center().color(Color.WHITE).get(), m.layFW(2));
                            return layout_main;
                        }

                        @Override
                        public void fillRowView(int index, View cellRenderer, JSONObject item) throws JSONException {
                            findView(cellRenderer, "id", TextView.class).setText(item.getString("userId"));
                            findView(cellRenderer, "name", TextView.class).setText(item.getString("name"));
                            findView(cellRenderer, "team", TextView.class).setText(item.getString("team"));
                        }
                    };
                    maker.addListView(jAdapter, maker.layFF());
                    maker.escape();
                }
                maker.escape();
            }

            displayLine(maker);

            maker.addColLayout(false, maker.layFW(1));
            {
                maker.add(maker.createStyledText("Object format").color(Color.WHITE).get(), maker.layFW());
                maker.addColLayout(false, maker.layAbsolute(0, 0, maker.getDisplayAgent().getWidth(), maker.getDisplayAgent().getHeight()/3));
                {
                    objAdapter = new ObjectAdapter<UsersData>(this, list) {

                        @Override
                        public View createRowView(int index, UsersData item) {
                            LinearLayout layout_main = new LinearLayout(context);
                            LayoutMaker m = new LayoutMaker(context, layout_main, false);
                            m.add(m.createStyledText("").tag("id").size(15).center().color(Color.RED).get(), m.layFW(3));
                            m.add(m.createStyledText("").tag("name").size(15).center().color(Color.WHITE).get(), m.layFW(2));
                            m.add(m.createStyledText("").tag("team").size(15).center().color(Color.WHITE).get(), m.layFW(2));
                            return layout_main;
                        }

                        @Override
                        public void fillRowView(int index, View cellRenderer, UsersData item) {
                            findView(cellRenderer, "id", TextView.class).setText(item.getUserId());
                            findView(cellRenderer, "name", TextView.class).setText(item.getName());
                            findView(cellRenderer, "team", TextView.class).setText(item.getTeam());
                        }
                    };
                    maker.addListView(objAdapter, maker.layFF());
                    maker.escape();
                }
                maker.escape();
            }

            maker.escape();
        }

        new AsyncAction<JSONObject>(this) {

            @Override
            public void afterExecution(JSONObject result) {
                {
                    try {
                        jAdapter.setArray(result.getJSONArray("users"));
                        jAdapter.notifyDataSetChanged();
                        list = JSONConverter.toList(result.getJSONArray("users"), UsersData.class);
                        objAdapter.setList(list);
                        objAdapter.notifyDataSetChanged();
                    } catch (JSONException ex) {
                        Logger.getLogger(JSONConverterDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public boolean execute(Context context) {
                JSONObject obj = null;
                try {
                    obj = new Mon("http://www.json-generator.com/api/json/get/bUpnyUCUSW?indent=2").sendAndWrap();
                    setResult(obj);
                } catch (Exception exception) {
                }
                return true;
            }
        }.execute();

    }

}
