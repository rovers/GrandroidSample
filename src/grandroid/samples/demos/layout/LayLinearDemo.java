package grandroid.samples.demos.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;

public class LayLinearDemo extends SampleFace {

    View view;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        final LayoutMaker m = new LayoutMaker(this, layout, false);

        maker.addColLayout(false, maker.layFF());
        {

            text = maker.add(maker.createStyledText(this.getText(R.string.comment_layout_laylinear)).padding(20, 20, 20, 20).size(15).color(Color.GRAY).get(), maker.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, Config.CONTENT_HEIGHT));

            maker.addRowLayout(false, maker.layAbsolute(0, 0, 720, LinearLayout.LayoutParams.WRAP_CONTENT));
            {
                maker.add(maker.createButton("layFF"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //set the layFF params
                        view.setLayoutParams(m.layFF());
                        view.setBackgroundColor(Color.RED);
                        text.setText(R.string.comment_layout_laylinear_ff);
                    }
                });
                maker.add(maker.createButton("layFW"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //set the layFW params
                        view.setLayoutParams(m.layFW());
                        view.setBackgroundColor(Color.LTGRAY);
                        text.setText(R.string.comment_layout_laylinear_fw);
                    }
                });
                maker.add(maker.createButton("layWF"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //set the layWF params
                        view.setLayoutParams(m.layWF(1));
                        view.setBackgroundColor(Color.GREEN);
                        text.setText(R.string.comment_layout_laylinear_wf);
                    }
                });
                maker.add(maker.createButton("layWW"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        //set the layWW params
                        view.setLayoutParams(m.layWW(0));
                        view.setBackgroundColor(Color.WHITE);
                        text.setText(R.string.comment_layout_laylinear_ww);
                    }
                });
                maker.escape();
            }
            maker.addColLayout(false, maker.layFF());
            {

                view = maker.addColLayout(false, maker.layFF());
                {
                    maker.getLastLayout().setBackgroundColor(Color.RED);
//                    text = maker.addTextView(this.getText(R.string.layFF));
                    maker.add(maker.createImage(ImageView.class, R.drawable.cat));
                    maker.add(maker.createButton("button"), maker.layFF(1));
                    maker.add(maker.createButton("button"), maker.layFF(1));
                    maker.escape();
                }
                maker.escape();
            }
            maker.escape();
        }
    }
}
