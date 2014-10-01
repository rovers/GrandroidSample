/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.samples.demos.SampleFace;
import grandroid.view.ViewDesigner;

/**
 *
 * @author Jack Huang
 */
public class LinearLayoutDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.setDesigner(new ViewDesigner() {
            @Override
            public TextView stylise(TextView tv) {
                super.stylise(tv);
                tv.setTextColor(Color.WHITE);
                return tv;
            }
        });

        maker.addColLayout(false, maker.layFF()).setPadding(10, 10, 10, 10);
        {
            maker.getLastLayout().setBackgroundColor(Color.rgb(205, 181, 205));
            maker.addTextView(" level 1");

            maker.addColLayout(false, maker.layFW()).setPadding(30, 10, 30, 10);
            {
                maker.getLastLayout().setBackgroundColor(Color.rgb(0, 139, 139));
                maker.addTextView(" level 2");

                maker.addColLayout(false, maker.layFW()).setPadding(10, 10, 10, 10);
                {
                    maker.getLastLayout().setBackgroundColor(Color.GRAY);
                    maker.addTextView(" level 3");
                    maker.add(maker.createStyledText("Layout can been put with horizon style").size(15).get(), maker.layAbsolute(30, 0, 620, LinearLayout.LayoutParams.WRAP_CONTENT));
                    maker.addColLayout().setPadding(80, 10, 80, 10);
                    {
                        maker.addRowLayout(false, maker.layFW());
                        {
                            maker.addColLayout(false, maker.layWF(1)).setPadding(40, 60, 40, 60);
                            {
                                maker.addTextView("A");
                                maker.getLastLayout().setBackgroundColor(Color.rgb(139, 105, 20));
                                maker.escape();
                            }
                            maker.addColLayout(false, maker.layWF(1)).setPadding(40, 60, 40, 60);
                            {
                                maker.addTextView("B");
                                maker.getLastLayout().setBackgroundColor(Color.rgb(238, 121, 66));
                                maker.escape();
                            }
                            maker.addColLayout(false, maker.layWF(1)).setPadding(40, 60, 40, 60);
                            {
                                maker.addTextView("C");
                                maker.getLastLayout().setBackgroundColor(Color.rgb(30, 144, 255));
                                maker.escape();
                            }
                            maker.escape();
                        }
                        maker.escape();
                    }
                    maker.escape();
                }
                maker.escape();
            }

            maker.addColLayout(false, maker.layFF(2)).setPadding(10, 10, 10, 10);
            {
                maker.getLastLayout().setBackgroundColor(Color.rgb(102, 205, 0));
                maker.addTextView(" level 2");
                maker.addColLayout().setPadding(30, 10, 30, 10);
                {
                    maker.add(maker.createStyledText("put by vertical style").size(15).get());

                    maker.addColLayout(false, maker.layFW()).setPadding(5, 5, 5, 5);
                    {
                        maker.getLastLayout().setBackgroundColor(Color.RED);
                        maker.addTextView(" ColLayout");
                        maker.addButton("col1");
                        maker.addButton("col2");
                        maker.escape();
                    }
                    maker.addRowLayout(false, maker.layFW()).setPadding(5, 5, 5, 5);
                    {
                        maker.getLastLayout().setBackgroundColor(Color.rgb(255, 222, 173));
                        maker.addTextView(" RowLayout");
                        maker.addButton("row1");
                        maker.addButton("row2");
                        maker.escape();
                    }
                    maker.addRowLayout(false, maker.layFF(1)).setPadding(5, 5, 5, 5);
                    {
                        maker.getLastLayout().setBackgroundColor(Color.rgb(175, 238, 238));
                        maker.addTextView(" vertical 3");
                        maker.escape();
                    }
//               
                    maker.escape();
                }
                maker.escape();
            }

            maker.escape();
        }
    }
}
