/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.devices;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import grandroid.action.Action;
import grandroid.action.ToastAction;
import grandroid.dialog.DialogMask;
import grandroid.dialog.GDialog;
import grandroid.phone.PhoneAgent;
import grandroid.samples.demos.Config;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import grandroid.view.StyledText;

/**
 *
 * @author Jack Huang
 */
public class PhoneAgentDemo extends SampleFace {

    TextView numberText, contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFF());
        {

            displayComment(maker, this, R.string.comment_devices_phone_agent);
            maker.addLine(Color.GRAY);
            maker.addColLayout(false, maker.layAbsolute(20, 20, 680, 100)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Call Number").center().color(Color.WHITE).get(), maker.layFW(1));
                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(20, 20, 680, 70));
            {
                numberText = maker.add(maker.createStyledEdit("").bgc(Color.WHITE).format(StyledText.Format.Number).get(), maker.layFW(1));
                maker.escape();
            }
            maker.addRowLayout(false, maker.layAbsolute(150, 20, 420, 100)).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Call"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (numberText.getText().length() > 0) {
                            PhoneAgent phone = new PhoneAgent();
                            phone.dial(PhoneAgentDemo.this, numberText.getText().toString());
                        } else {
                            new ToastAction(PhoneAgentDemo.this).setMessage("Please enter phone number").execute();
                        }
                    }
                });
                maker.escape();
            }
            maker.addLine(Color.GRAY);
            maker.addColLayout(false, maker.layAbsolute(20, 20, 680, 100)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Content: ").center().color(Color.WHITE).get(), maker.layFW(1));

                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(20, 20, 680, 200));
            {
                contentText = maker.add(maker.createStyledEdit("").bgc(Color.WHITE).get(), maker.layFF(1));
                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(150, 20, 420, 100)).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Send message"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (contentText.getText().length() > 0 && numberText.getText().length() > 0) {
                            new DialogMask(PhoneAgentDemo.this) {
                                @Override
                                public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                                    builder.setTitle("Warning");
                                    maker.add(maker.createStyledText("this demo can send actual message and product a lot of charge").color(Color.WHITE).get());
                                    builder.setPositiveButton(new Action("confirm") {
                                        @Override
                                        public boolean execute() {
                                            PhoneAgent phone = new PhoneAgent();
                                            phone.sendSMS(PhoneAgentDemo.this, numberText.getText().toString(), contentText.getText().toString());
                                            return true;
                                        }
                                    });
                                    builder.setNegativeButton(new Action("cancel") {
                                        @Override
                                        public boolean execute() {
                                            dialog.dismiss();
                                            return true;
                                        }
                                    });
                                    return false;
                                }
                            }.show(GDialog.DialogStyle.Android);

                        } else if (contentText.getText().length() > 0) {
                            new ToastAction(PhoneAgentDemo.this).setMessage("Please enter phone number").execute();
                        } else if (numberText.getText().length() > 0) {
                            new ToastAction(PhoneAgentDemo.this).setMessage("Please enter content").execute();
                        } else {
                            new ToastAction(PhoneAgentDemo.this).setMessage("Please enter something").execute();
                        }
                    }
                });
                maker.escape();
            }
            maker.escape();
        }
    }

}
