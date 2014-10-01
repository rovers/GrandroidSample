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
import grandroid.net.Mon;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MonDemo extends SampleFace {

    protected JSONAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {

            displayComment(maker, this, R.string.comment_json_mon);
            displayLine(maker);

            maker.addColLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.addColLayout().setPadding(20, 20, 20, 20);
                {
                    maker.addColLayout().setGravity(Gravity.CENTER);
                    {
                        maker.getLastLayout().setBackgroundColor(Color.rgb(255, 231, 186));
                        maker.addRowLayout(false, maker.layFW());
                        {
                            maker.getLastLayout().setBackgroundColor(Color.rgb(255, 165, 79));
                            maker.add(maker.createStyledText("ID").center().color(Color.WHITE).get(), maker.layFW(4));
                            maker.add(maker.createStyledText("Name").center().color(Color.WHITE).get(), maker.layFW(3));
                            maker.add(maker.createStyledText("Age").center().color(Color.WHITE).get(), maker.layFW(4));
                            maker.add(maker.createStyledText("Team").center().color(Color.WHITE).get(), maker.layFW(3));
                            maker.escape();
                        }
                        adapter = new JSONAdapter(MonDemo.this, new JSONArray()) {
                            @Override
                            public View createRowView(int index, JSONObject item) {
                                LinearLayout layout = new LinearLayout(context);
                                final LayoutMaker m = new LayoutMaker(context, layout, false);
                                m.addRowLayout(false, m.layFW()).setPadding(0, 10, 0, 10);
                                {
                                    m.add(m.createStyledText("").tag("id").center().size(13).get(), m.layFW(4));
                                    m.add(m.createStyledText("").tag("name").center().size(13).get(), m.layFW(3));
                                    m.add(m.createStyledText("").tag("age").center().size(13).get(), m.layFW(4));
                                    m.add(m.createStyledText("").tag("team").center().size(13).get(), m.layFW(3));
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
                }
                maker.escape();
            }
            maker.escape();
            new AsyncAction<JSONArray>(
                    this) {
                        @Override
                        public void afterExecution(JSONArray result) {
                            adapter.setArray(result);
                            adapter.notifyDataSetChanged();
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
                    }.execute();
        }
    }
}
