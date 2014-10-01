package grandroid.samples.demos.database;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.ToastAction;
import grandroid.adapter.FaceDataAdapter;
import grandroid.database.FaceData;
import grandroid.database.GenericHelper;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import grandroid.view.StyledText;

public class FaceDataDemo extends SampleFace {

    protected FaceData faceData;
    protected FaceDataAdapter adapter;
    protected GenericHelper<GroupData> helper;
    protected Cursor cursor;
    protected String[][] array = {{"Lisa", "B"}, {"Jack", "A"}, {"Bob", "C"}};
    protected TextView showView, showValueOne, showValueTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        faceData = new FaceData(this, "FaceDataDemoBase");
        helper = new GenericHelper<GroupData>(faceData, GroupData.class);

        maker.disableKeyboardFocus();
        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_database_facedata, LinearLayout.LayoutParams.WRAP_CONTENT);
            maker.addRowLayout(false, maker.layFW());
            {
                maker.addColLayout(false, maker.layFW(2));
                {

                    maker.add(maker.createButton("Execute"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            showView.setText("Insert into Table values (value1,value2,value3,...valueN)");
                            execEntrance();
                        }
                    });

                    maker.add(maker.createButton("Query"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            showView.setText("Select * from Table where col_name = 'value1'");
                            queryEntrance();
                        }

                    });
                    maker.add(maker.createButton("Clear"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            showView.setText("Delete from table_name");
                            truncateData();
                        }
                    });

                    maker.escape();
                }
                maker.addColLayout(false, maker.layFF(1));
                {
                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER);
                    {
                        maker.add(maker.createStyledText("Value1: ").color(Color.WHITE).center().get(), maker.layFW(1));
                        showValueOne = maker.add(maker.createStyledEdit("").bgc(Color.WHITE).hint("random").maxLine(1).format(StyledText.Format.English).get(), maker.layFW(1));
                        maker.escape();
                    }
                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER);
                    {
                        maker.add(maker.createStyledText("Value2: ").color(Color.WHITE).center().get(), maker.layFW(1));
                        showValueTwo = maker.add(maker.createStyledEdit("").bgc(Color.WHITE).hint("random").maxLine(1).format(StyledText.Format.English).get(), maker.layFW(1));
                        maker.escape();
                    }
                    maker.escape();
                }

                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(0, 0, maker.getDisplayAgent().getWidth(), 50));
            {
                showView = maker.add(maker.createStyledText("").size(14).center().color(Color.GRAY).get(), maker.layFF());
                maker.escape();
            }

            maker.addColLayout(false, maker.layFW(1));
            {

                adapter = new FaceDataAdapter<GroupData>(this, helper) {

                    @Override
                    public View createRowView(int index, GroupData item) {
                        LinearLayout layout_main = new LinearLayout(context);
                        LayoutMaker m = new LayoutMaker(context, layout_main, false);
                        m.add(m.createStyledText("").tag("ID").center().color(Color.WHITE).get(), m.layFW(1));
                        m.add(m.createStyledText("").tag("name").center().color(Color.WHITE).get(), m.layFW(1));
                        m.add(m.createStyledText("").tag("team").center().color(Color.WHITE).get(), m.layFW(1));
                        return layout_main;
                    }

                    @Override
                    public void fillRowView(int index, View cellRenderer, GroupData item) {
                        findView(cellRenderer, "ID", TextView.class).setText(item.get_id().toString());
                        findView(cellRenderer, "name", TextView.class).setText(item.getName());
                        findView(cellRenderer, "team", TextView.class).setText(item.getTeam());
                    }
                };
                maker.addListView(adapter, maker.layFF());
                maker.escape();
            }
            maker.escape();
        }

    }

    public void execData(String one, String two) {
        adapter.requery("");
        faceData.exec("Insert Into GroupData(name,team)Values('" + one + "','" + two + "')");
        adapter.refresh();
        new ToastAction(FaceDataDemo.this).setMessage("Random execute success").execute();

    }

    public void truncateData() {
        adapter.requery("");
        faceData.truncate("GroupData");
        adapter.refresh();
        new ToastAction(FaceDataDemo.this).setMessage("Clear success").execute();
    }

    public void queryData(String s) {
        adapter.requery("where name = '" + s + "'");
        adapter.refresh();
        new ToastAction(FaceDataDemo.this).setMessage("Select success").execute();
    }

    public void execEntrance() {
        if (showValueOne.getText().length() > 0 || showValueTwo.getText().length() > 0) {
            execData(showValueOne.getText().toString(), showValueTwo.getText().toString());
        } else {
            int i = (int) (Math.random() * 3);
            String one = array[i][0];
            String two = array[i][1];
            execData(one, two);
        }
    }

    public void queryEntrance() {
        if (adapter.isEmpty()) {
            new ToastAction(FaceDataDemo.this).setMessage("Nothing cannot been selected in result").execute();
        } else {
            if (showValueOne.getText().length() > 0) {
                queryData(showValueOne.getText().toString());
            }else{
            new ToastAction(FaceDataDemo.this).setMessage("query failed").execute();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); //To change body of generated methods, choose Tools | Templates.
        helper.drop();
        Log.e("cus", "onDestroy");
    }
}
