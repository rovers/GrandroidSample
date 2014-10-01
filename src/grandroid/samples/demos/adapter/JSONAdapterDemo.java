package grandroid.samples.demos.adapter;

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

public class JSONAdapterDemo extends SampleFace {

    protected JSONAdapter adapter;
    protected JSONObject object = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_adapter_json);
            displayLine(maker);

            //list
//            maker.addColLayout(false, maker.layAbsolute(20, 30, 680, LinearLayout.LayoutParams.MATCH_PARENT));
            maker.addColLayout().setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.addColLayout().setGravity(Gravity.CENTER);
                {
                    maker.addColLayout(false, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 100) * 98, maker.getDisplayAgent().getHeight() / 3 * 2));
                    {

                        maker.getLastLayout().setBackgroundColor(Color.rgb(224, 255, 255));
                        maker.addRowLayout(false, maker.layFW());
                        {
                            maker.getLastLayout().setBackgroundColor(Color.rgb(30, 144, 255));
                            maker.add(maker.createStyledText("ID").center().color(Color.WHITE).size(13).get(), maker.layFW(5));
                            maker.add(maker.createStyledText("Name").center().color(Color.WHITE).size(13).get(), maker.layFW(4));
                            maker.add(maker.createStyledText("Age").center().color(Color.WHITE).size(13).get(), maker.layFW(5));
                            maker.add(maker.createStyledText("Gender").center().color(Color.WHITE).size(13).get(), maker.layFW(5));
                            maker.add(maker.createStyledText("Team").center().color(Color.WHITE).size(13).get(), maker.layFW(4));
                            maker.escape();
                        }
                        adapter = new JSONAdapter(JSONAdapterDemo.this, new JSONArray()) {

                            @Override
                            public View createRowView(int index, JSONObject item) {
                                LinearLayout layout = new LinearLayout(context);
                                final LayoutMaker m = new LayoutMaker(context, layout, false);
                                m.addRowLayout(false, m.layFW());
                                {
                                    m.add(m.createStyledText("").tag("id").center().size(14).color(Color.rgb(102, 139, 139)).get(), m.layFW(5));
                                    m.add(m.createStyledText("").tag("name").center().size(11).get(), m.layFW(4));
                                    m.add(m.createStyledText("").tag("age").center().size(13).get(), m.layFW(5));
                                    m.add(m.createStyledText("").tag("gender").center().size(13).get(), m.layFW(5));
                                    m.add(m.createStyledText("").tag("team").center().size(12).get(), m.layFW(4));
                                    m.escape();
                                }
                                return layout;
                            }

                            @Override
                            public void fillRowView(int index, View cellRenderer, JSONObject item) throws JSONException {
                                findView(cellRenderer, "id", TextView.class).setText(item.getString("userId"));
                                findView(cellRenderer, "name", TextView.class).setText(item.getString("name"));
                                findView(cellRenderer, "age", TextView.class).setText(item.getString("age"));
                                findView(cellRenderer, "gender", TextView.class).setText(item.getString("gender"));
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
                        obj = new Mon("http://www.json-generator.com/api/json/get/bUpnyUCUSW?indent=2").sendAndWrap();
                        setResult(obj.getJSONArray("users"));
                    } catch (Exception exception) {
                    }

                    return true;
                }
            }.cancelable().execute();
        }
    }
}
