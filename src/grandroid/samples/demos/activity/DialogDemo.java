package grandroid.samples.demos.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import grandroid.action.Action;
import grandroid.dialog.DialogMask;
import grandroid.dialog.GDialog;
import grandroid.dialog.InputDialogMask;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import java.util.Calendar;

public class DialogDemo extends SampleFace {

    protected TextView dateButton;
    protected String Year, Mon, Day;
    protected DatePicker dp;
    protected DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dateButton = maker.createButton("extension");
//        dateButton.setText("Date Dialog");
        maker.addColLayout(false, maker.layFF());
        {
            
//            displayComment(maker, this, menuID);
            maker.add(maker.createButton("Android Theme"), maker.layAbsolute(160, 20, 400, 100)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showAndroidThemeDialog();
                }
            });
            maker.add(maker.createButton("Grandroid Theme"), maker.layAbsolute(160, 20, 400, 100)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showGrandroidThemeDialog();
                }
            });
            maker.add(maker.createButton("Custom Theme"), maker.layAbsolute(160, 20, 400, 100)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showCustomDialog();
                }
            });
            maker.add(maker.createButton("Input Dialog"), maker.layAbsolute(160, 20, 400, 100)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showInputDialog();
                }
            });
            maker.add(dateButton, maker.layAbsolute(160, 20, 400, 100)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showDateDialog();
                }
            });
            maker.escape();
        }
    }

    public void showGrandroidThemeDialog() {
        new DialogMask(this) {

            @Override
            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                builder.setTitle("GDialog");
                maker.addColLayout(false, maker.layAbsolute(30, 30, 400, 100));
                {
                    maker.add(maker.createStyledText("Exit?").get());
                    maker.escape();
                }
                builder.setPositiveButton(new Action("confirm") {
                    @Override
                    public boolean execute() {
                        dialog.dismiss();
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
        }.cancelable().show(GDialog.DialogStyle.Grandroid);
    }

    public void showAndroidThemeDialog() {
        new DialogMask(this) {

            @Override
            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                builder.setTitle("Dialog");
                maker.addColLayout(false, maker.layAbsolute(30, 30, 400, 100));
                {
                    maker.add(maker.createStyledText("Exit?").color(Color.WHITE).get());
                    maker.escape();
                }
                builder.setPositiveButton(new Action("confirm") {
                    @Override
                    public boolean execute() {
                        dialog.dismiss();
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
        }.cancelable().show(GDialog.DialogStyle.Android);
    }

    public void showCustomDialog() {
        new DialogMask(this) {

            @Override
            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                maker.addColLayout(false, maker.layAbsolute(0, 0, 400, 300));
                {
                    maker.getLastLayout().setBackgroundColor(Color.WHITE);
                    maker.addColLayout(false, maker.layAbsolute(0, 0, 400, 80));
                    {
                        maker.getLastLayout().setBackgroundColor(Color.GRAY);
                        maker.add(maker.createStyledText("It is a dialog demo").padding(20, 0, 0, 0).color(Color.WHITE).get());
                        maker.escape();
                    }
                    maker.addColLayout(false, maker.layAbsolute(0, 0, 400, 120));
                    {
                        maker.add(maker.createStyledText("Exit this activity?").padding(30, 0, 0, 0).get());
                        maker.escape();
                    }
                    maker.addRowLayout(false, maker.layAbsolute(0, 0, 400, 100));
                    {
                        maker.add(maker.createButton("confirm"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        maker.add(maker.createButton("cancel"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        maker.escape();
                    }
                    maker.escape();
                }
                return false;
            }
        }.cancelable().show(GDialog.DialogStyle.Custom);
    }

    private void showInputDialog() {
        new InputDialogMask(this, "System", "", "", null) {

            @Override
            public boolean executeAction(String inputText) {

                return true;
            }
        }.show(GDialog.DialogStyle.Android);
    }

    private void showDateDialog() {
        new DialogMask(DialogDemo.this) {
            @Override
            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                Calendar TodayDate = Calendar.getInstance();
                final int sYear = TodayDate.get(Calendar.YEAR);
                final int sMon = TodayDate.get(Calendar.MONTH);
                final int sDay = TodayDate.get(Calendar.DAY_OF_MONTH);
                Year = String.valueOf(sYear);
                Mon = String.valueOf(sMon + 1);
                Day = String.valueOf(sDay);
                maker.addColLayout(false, maker.layWW(1));
                {
                    maker.getLastLayout().setBackgroundColor(Color.WHITE);
                    maker.add(maker.createStyledText("Dat").color(Color.WHITE).bgc(Color.BLACK).get(), maker.layFW(1));
                    dp = new DatePicker(DialogDemo.this);
                    maker.add(dp, maker.layFW(1));
                    //initial datepicker
                    dp.init(sYear, sMon, sDay, new DatePicker.OnDateChangedListener() {
                        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                            Log.e("cus", String.valueOf(monthOfYear));
                            Year = String.valueOf(year);
                            //TodayDate.get(Calendar.MONTH) index(0~11)
                            Mon = String.valueOf(monthOfYear + 1);
                            Day = String.valueOf(dayOfMonth);
                        }
                    });
                    maker.addRowLayout(false, maker.layFW(1));
                    {
                        maker.add(maker.createButton("confirm"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dateButton.setText(Year + "-" + Mon + "-" + Day);
                                dialog.dismiss();
                            }
                        });
                        maker.add(maker.createButton("cancel"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                        maker.escape();
                    }
                    maker.escape();
                }
                return false;
            }
        }.cancelable().show(GDialog.DialogStyle.Custom);
    }

}
