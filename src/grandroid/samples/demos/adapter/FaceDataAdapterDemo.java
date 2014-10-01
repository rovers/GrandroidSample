package grandroid.samples.demos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import grandroid.action.AsyncAction;
import grandroid.action.ToastAction;
import grandroid.adapter.FaceDataAdapter;
import grandroid.database.GenericHelper;
import grandroid.database.RawFaceData;
import grandroid.dialog.DialogMask;
import grandroid.dialog.GDialog;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;

public class FaceDataAdapterDemo extends SampleFace {

    //
    protected FaceDataAdapter<Book> adapter;
    protected RawFaceData rfd;
    protected GenericHelper<Book> helper;

    protected String[] randomAuthor = {"Dallin Linsey", "Alexis Gayman", "Zachary Davison", "Dakota Bracher", "Terrence Kady", "Corey Havey", "Samir Parris", "Gerald Canner", "Terrell Boord", "Ignacio Hawe"};
    protected String[] randomName = {"Spy Without Flaws", "Friend Without A Goal", "Creators Of Time", "Pilots Of Fortune", "Rebels And Horses", "Creators And Agents", "Fate Without Duty", "Foundation Of Sorrow", "Life At The Forest", "Growing In The Ashes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // new object
        rfd = new RawFaceData(this, "book-grandroid", true);
        helper = new GenericHelper<Book>(rfd, Book.class);

        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_adapter_facedata);
            displayLine(maker);

//                    maker.add(maker.createButton("Display"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
//
//                        public void onClick(View v) {
//
//                            Book bookBase = new Book();
//                            bookBase.setBookName("Time for money");
//                            bookBase.setBookAuthor("John");
//                            helper.insert(bookBase);
//                            adapter.refresh();
//                        }
//                    });
            maker.addColLayout().setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.addColLayout().setGravity(Gravity.CENTER);
                {
                    maker.add(maker.createButton("Clear"), maker.layWW(0)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            helper = new GenericHelper<Book>(rfd, Book.class);
                            helper.truncate();
                            adapter.refresh();
                        }
                    });
                    maker.escape();
                }
                maker.addColLayout().setGravity(Gravity.CENTER);
                {
                    maker.addColLayout(false, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 100) * 98, maker.getDisplayAgent().getHeight() / 2));
                    {

                        maker.getLastLayout().setBackgroundColor(Color.rgb(193, 255, 193));
                        maker.addRowLayout(false, maker.layFW());
                        {
                            maker.getLastLayout().setBackgroundColor(Color.rgb(46, 139, 87));

                            maker.add(maker.createStyledText("ID").center().color(Color.WHITE).size(18).get(), maker.layFW(3));
                            maker.add(maker.createStyledText("Book").center().color(Color.WHITE).size(18).get(), maker.layFW(2));
                            maker.add(maker.createStyledText("Author").center().color(Color.WHITE).size(18).get(), maker.layFW(2));
                            maker.escape();
                        }
                        //add adapter
                        adapter = new FaceDataAdapter<Book>(this, helper) {

                            //createRowView is the view which add list item on
                            @Override
                            public View createRowView(int index, Book item) {
                                LinearLayout layout_main = new LinearLayout(context);
                                final LayoutMaker m = new LayoutMaker(context, layout_main, false);

                                m.addRowLayout(false, m.layFW());
                                {
                                    m.add(m.createStyledText("").tag("bookID").center().color(Color.rgb(110, 139, 61)).size(14).get(), m.layFW(3));
                                    m.add(m.createStyledText("").tag("bookName").center().size(14).get(), m.layFW(2));
                                    m.add(m.createStyledText("").tag("bookAuthor").center().size(14).get(), m.layFW(2));
                                    m.escape();
                                }
                                m.addLine(Color.GRAY, 1);

                                return layout_main;
                            }

                            //fillRowView 
                            @Override
                            public void fillRowView(int index, View cellRenderer, Book item) {
                                findView(cellRenderer, "bookID", TextView.class).setText(item.get_id().toString());
                                findView(cellRenderer, "bookName", TextView.class).setText(item.getBookName());
                                findView(cellRenderer, "bookAuthor", TextView.class).setText(item.getBookAuthor());
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
            new AsyncAction<Void>(this) {

                @Override
                public void afterExecution(Void result) {
                    adapter.refresh();
                    new ToastAction(FaceDataAdapterDemo.this).setMessage("Loading DataBase success").execute();
                }

                @Override
                public boolean execute(Context context) {
                    try {
                        initData();
                    } catch (Exception e) {
//                        Log.e("cus", e.toString());
                    }
                    return true;
                }

                @Override
                public DialogMask createLoadingDialog(String message) {
                    return new DialogMask(FaceDataAdapterDemo.this) {
                        @Override
                        public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                            maker.add(new ProgressBar(context));
                            maker.add(maker.createStyledText("Database Loading").center().color(Color.WHITE).get());
                            return false;
                        }
                    };
                }
            }.customizeLoadingBox().execute();
        }
    }

    protected void initData() {
        if (helper.select().isEmpty()) {
            helper.drop();
            helper = new GenericHelper<Book>(rfd, Book.class);
//            rfd.isEditing();
            for (int i = 0; i < 100; i++) {
                Book bookBase = new Book(randomName[(int) (Math.random() * (randomName.length))], randomAuthor[(int) (Math.random() * (randomAuthor.length))]);
//                Log.e("cus", bookBase.getBookName() + " " + bookBase.getBookAuthor());
                helper.insert(bookBase);
            }
//            rfd.endEdit();
        }
    }
}
