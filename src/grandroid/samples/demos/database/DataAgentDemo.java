package grandroid.samples.demos.database;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import grandroid.data.DataAgent;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.StyledText;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataAgentDemo extends SampleFace {

    TextView nameView, phoneView, saveTimeView;
    String name, phone, saveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DataAgent da = new DataAgent(DataAgentDemo.this);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_database_data_agent);
            displayLine(maker);
            maker.addRowLayout(false, maker.layFW(2)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Name").color(Color.WHITE).center().get(), maker.layFW(1));
                nameView = maker.add(maker.createStyledEdit(da.getPreference("name")).center().bgc(Color.WHITE).get(), maker.layFW(1));
                maker.escape();
            }
            maker.addRowLayout(false, maker.layFW(2)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Phone Number").color(Color.WHITE).center().get(), maker.layFW(1));
                phoneView = maker.add(maker.createStyledEdit(da.getPreference("phone")).center().allowChars("0123456789").format(StyledText.Format.Number).bgc(Color.WHITE).get(), maker.layFW(1));
                maker.escape();
            }
            displayLine(maker);
            maker.addColLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Save Time").color(Color.WHITE).center().get(), maker.layFW(1));
                saveTimeView = maker.add(maker.createStyledText(da.getPreference("time")).color(Color.WHITE).center().size(15).bgc(Color.GRAY).get(), maker.layFW(1));
                maker.escape();
            }
            displayLine(maker);
            maker.addRowLayout(false, maker.layFW(2)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createButton("confirm"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
                        saveTime = sdf.format(c.getTime());
//                        Log.e("cus", saveTime);
                        da.getEditor().putString("name", nameView.getText().toString()).putString("phone", phoneView.getText().toString()).putString("time", saveTime).commit();
//                        Log.e("cus", da.getPreference("name"));
//                        Log.e("cus", da.getPreference("phone"));
//                        Log.e("cus", da.getPreference("time"));
                    }

                });
                maker.add(maker.createButton("default"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        defaultEvent(da);
                    }

                });
                maker.escape();
            }
            maker.escape();
        }
    }

    protected void defaultEvent(DataAgent da) {
        nameView.setText("");
        phoneView.setText("");
        saveTimeView.setText("");
        da.getEditor().clear().commit();
//        Log.e("cus", "123"+da.getPreference("name"));
    }
}
