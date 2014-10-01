package grandroid.samples.demos.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.StyledText;

public class CreateStyleDemo extends SampleFace {

    TextView text;
    String allow = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {
            maker.addColLayout(false, maker.layAbsolute(20, 10, 680, 200));
            {
                maker.add(maker.createStyledText(this.getText(R.string.comment_view_edit)).size(15).bold().color(Color.GRAY).get());

                maker.escape();
            }
            maker.addLine(Color.GRAY);
            maker.addColLayout(false, maker.layAbsolute(20, 10, 680, 150));
            {
                maker.add(maker.createStyledText("Only enter English").color(Color.WHITE).get());
                maker.add(maker.createStyledEdit("").format(StyledText.Format.English).allowChars(allow).color(Color.BLUE).bgc(Color.WHITE).get());

                maker.escape();
            }
            maker.addLine(Color.GRAY);
            maker.addColLayout(false, maker.layAbsolute(20, 10, 680, 150));
            {
                maker.add(maker.createStyledText("Something set").color(Color.WHITE).get());
                maker.add(maker.createStyledEdit("").hint("set hint").bold().bgc(Color.WHITE).get());
                maker.escape();
            }
            maker.addLine(Color.GRAY);
            maker.addColLayout(false, maker.layAbsolute(20, 10, 680, 250));
            {
                maker.add(maker.createStyledText("Other font").color(Color.WHITE).get());
                maker.add(maker.createStyledEdit("font-style: SERIF , font-size: 30").size(30).font(Typeface.SERIF).bgc(Color.WHITE).get());
                maker.escape();
            }
            maker.addLine(Color.GRAY);
            maker.addColLayout(false, maker.layAbsolute(20, 10, 680, 150));
            {
                maker.add(maker.createStyledText("Cant edit").color(Color.WHITE).get());
                maker.add(maker.createStyledEdit("It has been locked").font(Typeface.SERIF).lock(true).bgc(Color.GRAY).get());
                maker.escape();
            }
            maker.escape();

        }
    }
}
