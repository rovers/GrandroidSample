package grandroid.samples.demos.json;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.AsyncAction;
import grandroid.adapter.JSONAdapter;
import grandroid.net.ApacheMon;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApacheMonDemo extends SampleFace {

    protected JSONAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            maker.addColLayout(false, maker.layAbsolute(20, 20, 680, 300));
            {
                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(20, 20, 680, LinearLayout.LayoutParams.MATCH_PARENT));
            {
                maker.getLastLayout().setBackgroundColor(Color.rgb(255, 231, 186));
                maker.addRowLayout(false, maker.layAbsolute(0, 0, 680, 100)).setGravity(Gravity.CENTER_VERTICAL);
                {
                    maker.getLastLayout().setBackgroundColor(Color.rgb(255, 165, 79));
                    maker.add(maker.createStyledText("ID").center().color(Color.WHITE).get(), maker.layAbsolute(10, 0, 70, LinearLayout.LayoutParams.WRAP_CONTENT));
                    maker.add(maker.createStyledText("Name").center().color(Color.WHITE).get(), maker.layFW(1));
                    maker.add(maker.createStyledText("Age").center().color(Color.WHITE).get(), maker.layAbsolute(10, 0, 80, LinearLayout.LayoutParams.WRAP_CONTENT));
                    maker.add(maker.createStyledText("Team").center().color(Color.WHITE).get(), maker.layFW(1));
                    maker.escape();
                }
                adapter = new JSONAdapter(ApacheMonDemo.this, new JSONArray()) {
                    @Override
                    public View createRowView(int index, JSONObject item) {
                        LinearLayout layout = new LinearLayout(context);
                        final LayoutMaker m = new LayoutMaker(context, layout, false);
                        m.addRowLayout(false, m.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, 70));
                        {
                            m.add(m.createStyledText("").tag("id").center().size(13).get(), m.layAbsolute(10, 0, 70, LinearLayout.LayoutParams.WRAP_CONTENT));
                            m.add(m.createStyledText("").tag("name").center().size(13).get(), m.layFW(1));
                            m.add(m.createStyledText("").tag("age").center().size(13).get(), m.layAbsolute(10, 0, 80, LinearLayout.LayoutParams.WRAP_CONTENT));
                            m.add(m.createStyledText("").tag("team").center().size(13).get(), m.layFW(1));
                            m.escape();
                        }
                        return layout;
                    }

                    @Override
                    public void fillRowView(int index, View cellRenderer, JSONObject item) throws JSONException {
                        findView(cellRenderer, "id", TextView.class).setText(item.getString("userId"));
                        findView(cellRenderer, "name", TextView.class).setText(item.getString("name"));
                        findView(cellRenderer, "age", TextView.class).setText(item.getString("age"));
                        findView(cellRenderer, "team", TextView.class).setText(item.getString("team"));
                    }
                };
                maker.addListView(adapter, maker.layFF());
                maker.escape();
            }
            maker.escape();
            new AsyncAction<JSONArray>(this) {
                @Override
                public void afterExecution(JSONArray result) {
                    adapter.setArray(result);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public boolean execute(Context context) {
                    JSONObject obj = null;
                    try {
                        obj = new ApacheMon("http://www.json-generator.com/api/json/get/bUpnyUCUSW?indent=2").sendAndWrap();
                        setResult(obj.getJSONArray("users"));

                    } catch (Exception exception) {
                    }
                    return true;
                }
            }.cancelable().execute();
        }
    }

}
