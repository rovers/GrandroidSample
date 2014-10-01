package grandroid.samples.demos.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import grandroid.action.GoAction;
import grandroid.action.ToastAction;
import grandroid.adapter.ObjectAdapter;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.Face;
import grandroid.view.LayoutMaker;
import grandroid.view.ViewDesigner;
import java.util.ArrayList;

public class ViewDesignerDemo extends SampleFace {

    protected int setTextColor, setBgColor, receiveTextColor, receiveBgColor, selectTextColor, selectBgColor;
    protected boolean flag;
    protected ObjectAdapter<String> objectAdapter;
    protected ArrayList<String> list;
    protected RadioGroup rg;
    protected Spinner sp;
    protected String[] radioList = {"white", "black"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intiColor();
        initList();

//        LayoutMaker maker = new LayoutMaker(this);
//        maker.setDrawableDesignWidth(this, 720);
        maker.setDesigner(new ViewDesigner() {

            @Override
            public TextView stylise(TextView tv) {
                super.stylise(tv);

                tv.setTextColor(setTextColor);
                return tv;
            }

            @Override
            public Button stylise(Button btn) {
                super.stylise(btn);
                btn.setTextColor(setTextColor);
                btn.setBackgroundColor(Color.DKGRAY);
                return btn;
            }

            @Override
            public LinearLayout stylise(LinearLayout layout) {
                layout.setBackgroundColor(setBgColor);
                return layout;
            }

            @Override
            public RadioButton stylise(RadioButton rb) {
                rb.setTextColor(Color.GRAY);

                return rb;
            }

        });

        maker.addColLayout(false, maker.layFF());
        {

            displayComment(maker, this, R.string.comment_view_view_designer);
            maker.addRowLayout(false, maker.layFW()).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("TextView Color").get(), maker.layFW(1));
                objectAdapter = new ObjectAdapter<String>(this, list) {

                    @Override
                    public View createRowView(int index, String item) {
                        LinearLayout layout = new LinearLayout(context);
                        final LayoutMaker m = new LayoutMaker(context, layout, false);
                        m.add(m.createStyledText("").tag("select").center().get(), m.layFF());
                        return layout;
                    }

                    @Override
                    public void fillRowView(int index, View cellRenderer, String item) {
                        findView(cellRenderer, "select", TextView.class).setText(item);
                    }
                };
                sp = maker.addSpinner(objectAdapter, maker.layFW(1));
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        changeTextColor(id);

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
//                        switch (setTextColor) {
//                            case Color.GREEN:
//                               
//                            case Color.BLUE:
//                                parent.setSelection(1);
//                            case Color.RED:
//                                parent.setSelection(2);
//                        }
                    }
                });

                maker.escape();
            }
            maker.addRowLayout(false, maker.layFW()).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Background Color").get(), maker.layFW(1));
                maker.addColLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER);
                {
                    rg = maker.createRadioGroup(radioList);
//                    rg.check(1);
                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            changeBackgroundColor(checkedId);
//                            intentColor();
                        }
                    });
                    maker.add(rg);
                    maker.escape();
                }
                maker.escape();
            }
            maker.addColLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("change"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        intentColor();
                    }

                });
                maker.escape();
            }
            maker.add(maker.createTextView("Hello"));
            maker.add(maker.createTextView(this.getText(R.string.comment_view_view_designer)));
            maker.escape();
        }
    }

    private void changeTextColor(long l) {
        if (l == 1) {
            selectTextColor = Color.BLUE;
        } else if (1 == 2) {
            selectTextColor = Color.RED;
        } else {
            selectTextColor = Color.GREEN;
        }
    }

    private void changeBackgroundColor(int i) {
        if (i == 0) {
            selectBgColor = Color.WHITE;
        } else {
            selectBgColor = Color.BLACK;
        }
    }

    private void initList() {
        list = new ArrayList<String>();
        list.add("GREEN");
        list.add("BLUE");
        list.add("RED");
    }

    private void intiColor() {
        try {
            Intent i = getIntent();
            if (i == null) {
                flag = true;
            } else {
                Intent intent = this.getIntent();
                Bundle bundle = intent.getExtras();
                receiveTextColor = bundle.getInt("intent_textColor");
                receiveBgColor = bundle.getInt("intent_bgColor");
//                Log.e("cus", receiveBgColor+" 1");
                flag = false;
            }
        } catch (Exception e) {
            flag = true;
        }
        try {
            if (flag == false) {
                if (receiveTextColor != 0) {
                    setTextColor = receiveTextColor;
                    Log.e("cus", "text not 0");
                }
                if (receiveBgColor != 0) {
                    setBgColor = receiveBgColor;
                    Log.e("cus", "bg not 0");
                }
//                Log.e("cus", setBgColor+"2");
            } else if (flag) {
                setTextColor = Color.WHITE;
                setBgColor = Color.BLACK;
                Log.e("cus", "tx" + setTextColor + "bg" + setBgColor);
            }
        } catch (Exception e) {
        }
    }

    private void intentColor() {
        finish();
        Bundle bundle = new Bundle();
        bundle.putInt("intent_textColor", selectTextColor);
        bundle.putInt("intent_bgColor", selectBgColor);
        new GoAction(ViewDesignerDemo.this, ViewDesignerDemo.class).setBundle(bundle).cancelAnimation().execute();
//        new ToastAction(ViewDesignerDemo.this).setMessage("change").execute();
    }

//    private void intentBgColor() {
//        finish();
//        Bundle bundle = new Bundle();
//        bundle.putInt("intent_bgColor", selectBgColor);
//        new GoAction(ViewDesignerDemo.this, ViewDesignerDemo.class).setBundle(bundle).cancelAnimation().execute();
//        new ToastAction(ViewDesignerDemo.this).setMessage("change").execute();
//    }
}
