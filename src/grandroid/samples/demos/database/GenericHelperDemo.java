package grandroid.samples.demos.database;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import grandroid.action.ToastAction;
import grandroid.adapter.FaceDataAdapter;
import grandroid.adapter.ObjectAdapter;
import grandroid.database.FaceData;
import grandroid.database.GenericHelper;
import grandroid.dialog.DialogMask;
import grandroid.dialog.GDialog;
import grandroid.samples.demos.R;
import grandroid.samples.demos.SampleFace;
import grandroid.view.LayoutMaker;
import grandroid.view.StyledText;
import java.util.ArrayList;
import java.util.List;

//                  
public class GenericHelperDemo extends SampleFace {

//    protected RawFaceData rfd;
    protected FaceData faceData;
    protected FaceDataAdapter<Book> listAdapter;
    protected GenericHelper<Book> helper;
    protected ArrayList<String> list, bookList, authorList, idList;
    protected String[][] bookArray = {{"Weather in Asia", "Alex"}, {"May Day", "Mary"}, {"Coffee bean", "Cryis"}, {"Life up", "Peter"}, {"Apple and Build", "Oxford"}, {"The tree", "John"}};
//    protected EditText editBookName, editBookAuthor;
    protected TextView editTextBook, editTextAuthor, editTextID, eTI, eTB, eTA;
    protected ObjectAdapter bookAdapter, authorAdapter, idAdapter;
    protected String filter, allow = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    protected static String selectTextBook, selectTextAuthor, selectTextId;
    protected Spinner spinBook, spinAuthor, spinId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        rfd = new RawFaceData(this, "GenericHelperDemoBase", true);
        faceData = new FaceData(this, "BookDataBase");
        helper = new GenericHelper<Book>(faceData, Book.class);

        list = new ArrayList<String>();
        for (int i = 0; i < bookArray.length; i++) {
            list.add(bookArray[i][1]);
        }
        
        initArrayList(bookList);
        initArrayList(authorList);
        initArrayList(idList);
        
//        bookList = new ArrayList<String>();
//        bookList.add("-select-");
//        authorList = new ArrayList<String>();
//        authorList.add("-select-");
//        idList = new ArrayList<String>();
//        idList.add("-select-");
        
//        initObjectAdapter(bookAdapter, bookList);
//        initObjectAdapter(authorAdapter, authorList);
//        initObjectAdapter(idAdapter, idList);
        
        bookAdapter = new ObjectAdapter<String>(this, bookList) {
            @Override
            public void fillRowView(int index, View cellRenderer, String item) throws Exception {
                findView(cellRenderer, "book", TextView.class).setText(item);
            }

            @Override
            public View createRowView(int index, String item) {
                LinearLayout layout_main = new LinearLayout(context);
                final LayoutMaker m = new LayoutMaker(context, layout_main, false);
                m.addRowLayout(false, m.layFF());
                {
                    m.add(m.createStyledText("").tag("book").center().get(), m.layFW());
                }
                return layout_main;
            }
        };

        authorAdapter = new ObjectAdapter<String>(this, authorList) {
            @Override
            public void fillRowView(int index, View cellRenderer, String item) throws Exception {
                findView(cellRenderer, "author", TextView.class).setText(item);
            }

            @Override
            public View createRowView(int index, String item) {
                LinearLayout layout_main = new LinearLayout(context);
                final LayoutMaker m = new LayoutMaker(context, layout_main, false);
                m.addRowLayout(false, m.layFF());
                {
                    m.add(m.createStyledText("").tag("author").center().get(), m.layFW());
                }
                return layout_main;
            }
        };

        idAdapter = new ObjectAdapter<String>(this, idList) {
            @Override
            public void fillRowView(int index, View cellRenderer, String item) throws Exception {
                findView(cellRenderer, "id", TextView.class).setText(item);
            }

            @Override
            public View createRowView(int index, String item) {
                LinearLayout layout_main = new LinearLayout(context);
                final LayoutMaker m = new LayoutMaker(context, layout_main, false);
                m.addRowLayout(false, m.layFF());
                {
                    m.add(m.createStyledText("").tag("id").center().get(), m.layFW());
                }
                return layout_main;
            }
        };
        maker.disableKeyboardFocus();
        maker.addColLayout(false, maker.layFF());
        {
            displayComment(maker, this, R.string.comment_database_generichelper);

            maker.addRowLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Insert"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        new DialogMask(GenericHelperDemo.this) {
                            @Override
                            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                                maker.addColLayout(false, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 3) * 2, maker.getDisplayAgent().getHeight() / 2)).setPadding(20, 20, 20, 20);
                                {
                                    maker.getLastLayout().setBackgroundColor(Color.BLACK);
                                    editTextBook = displayBookLayout(maker, true);
                                    editTextAuthor = displayAuthorLayout(maker, true);
                                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
                                    {
                                        maker.add(maker.createButton("insert"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                boolean b = insertData(editTextBook.getText().toString(), editTextAuthor.getText().toString());
                                                if (b) {
                                                    dialog.dismiss();
                                                }
                                            }
                                        });
                                        maker.add(maker.createButton("random"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                randomData();
                                            }
                                        });
                                        maker.escape();
                                    }
                                    maker.escape();
                                }
                                return true;
                            }
                        }.cancelable().show(GDialog.DialogStyle.Custom);
                    }
                });
                maker.add(maker.createButton("Delete"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        new DialogMask(GenericHelperDemo.this) {
                            @Override
                            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                                maker.addColLayout(false, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 3) * 2, maker.getDisplayAgent().getHeight() / 2)).setPadding(20, 20, 20, 20);
                                {
                                    maker.getLastLayout().setBackgroundColor(Color.BLACK);
                                    editTextID = displayIDLayout(maker, true);
                                    displayBookLayout(maker, false);
                                    displayAuthorLayout(maker, false);

                                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
                                    {
                                        maker.add(maker.createButton("Delete"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                boolean b = deleteEntrance(editTextID, selectTextBook, selectTextAuthor);
                                                if (b) {
                                                    dialog.dismiss();
                                                }
                                            }
                                        });
                                        maker.escape();
                                    }
                                    maker.escape();
                                }
                                return true;
                            }
                        }.cancelable().show(GDialog.DialogStyle.Custom);
                    }
                });
                maker.escape();
            }

            maker.addRowLayout(false, maker.layFW()).setGravity(Gravity.CENTER);
            {
                maker.add(maker.createButton("Update"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        new DialogMask(GenericHelperDemo.this) {
                            @Override
                            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                                maker.addColLayout(false, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 3) * 2, maker.getDisplayAgent().getHeight() / 2)).setPadding(20, 20, 20, 20);
                                {
                                    maker.getLastLayout().setBackgroundColor(Color.BLACK);
                                    displayIDLayout(maker, false);
                                    editTextBook = displayBookLayout(maker, true);
                                    editTextAuthor = displayAuthorLayout(maker, true);

                                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
                                    {
                                        maker.add(maker.createButton("update"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                boolean b = updateData(selectTextId, editTextBook, editTextAuthor);
                                                if (b) {
                                                    dialog.dismiss();
                                                }
                                            }
                                        });
                                        maker.escape();
                                    }
                                    maker.escape();
                                }
                                return true;
                            }
                        }.cancelable().show(GDialog.DialogStyle.Custom);
                    }
                });
                maker.add(maker.createButton("Select"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        new DialogMask(GenericHelperDemo.this) {
                            @Override
                            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                                maker.addColLayout(false, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 3) * 2, maker.getDisplayAgent().getHeight() / 2)).setPadding(20, 20, 20, 20);
                                {
                                    maker.getLastLayout().setBackgroundColor(Color.BLACK);
                                    displayIDLayout(maker, false);
                                    displayBookLayout(maker, false);
                                    displayAuthorLayout(maker, false);
                                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
                                    {
                                        maker.add(maker.createButton("select"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                boolean b = selectEntrance(selectTextId, selectTextBook, selectTextAuthor);
                                                if (b) {
                                                    dialog.dismiss();
                                                }
                                            }
                                        });
                                        maker.add(maker.createButton("select all"), maker.layWW(1)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                helper.select();
                                                listAdapter.refresh();
                                                dialog.dismiss();

                                            }
                                        });
                                        maker.escape();
                                    }
                                }
                                return true;
                            }
                        }.cancelable().show(GDialog.DialogStyle.Custom);
                    }
                });
                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(0, 10, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            {
                maker.getLastLayout().setBackgroundColor(Color.WHITE);
                maker.addRowLayout(false, maker.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                {
                    maker.add(maker.createStyledText("ID").color(Color.GRAY).center().get(), maker.layFW(2));
                    maker.add(maker.createStyledText("Book").color(Color.GRAY).center().get(), maker.layFW(1));
                    maker.add(maker.createStyledText("Author").color(Color.GRAY).center().get(), maker.layFW(2));
                    maker.escape();
                }
                maker.addColLayout(false, maker.layAbsolute(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                {
                    listAdapter = new FaceDataAdapter<Book>(this, helper) {
                        @Override
                        public View createRowView(int index, Book item) {
                            LinearLayout layout_main = new LinearLayout(context);
                            final LayoutMaker m = new LayoutMaker(context, layout_main, false);

                            m.addRowLayout(false, m.layFF());
                            {
                                m.add(m.createStyledText("").tag("ID").center().get(), m.layFW(2));
                                m.add(m.createStyledText("").tag("bookName").center().get(), m.layFW(1));
                                m.add(m.createStyledText("").tag("bookAuthor").center().get(), m.layFW(2));
                            }
                            return layout_main;
                        }

                        @Override
                        public void fillRowView(int index, View cellRenderer, Book item) {
                            findView(cellRenderer, "ID", TextView.class).setText(item.get_id().toString());
                            findView(cellRenderer, "bookName", TextView.class).setText(item.getBookName());
                            findView(cellRenderer, "bookAuthor", TextView.class).setText(item.getBookAuthor());
                        }
                    };
                    maker.addListView(listAdapter, maker.layFF());
                    maker.escape();
                }
                maker.escape();
            }

            maker.escape();
        }
    }

    protected TextView displayBookLayout(LayoutMaker maker, boolean b) {
        if (b) {

            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Book").center().color(Color.WHITE).get(), maker.layFW(1));
                eTB = maker.add(maker.createStyledEdit("").allowChars(allow).format(StyledText.Format.English).bgc(Color.WHITE).maxLine(1).get(), maker.layFW(1));
                maker.escape();
            }
            return eTB;
        } else {
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Book").center().color(Color.WHITE).get(), maker.layFW(1));
                spinBook = maker.addSpinner(bookAdapter, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 5) * 2, 80));
                spinBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectTextBook = parent.getSelectedItem().toString();
//                        Log.e("cus", "select " + selectTextBook);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                maker.escape();
            }
//            eTB.setText(filter);
//            return eTB;

            return null;
        }
    }

    protected TextView displayAuthorLayout(LayoutMaker maker, boolean b) {
        if (b) {
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Author").center().color(Color.WHITE).get(), maker.layFW(1));
                eTA = maker.add(maker.createStyledEdit("").allowChars(allow).format(StyledText.Format.English).bgc(Color.WHITE).maxLine(1).get(), maker.layFW(1));
                maker.escape();
            }
            return eTA;
        } else {

            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("Author").center().color(Color.WHITE).get(), maker.layFW(1));
                spinAuthor = maker.addSpinner(authorAdapter, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 5) * 2, 80));
                spinAuthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectTextAuthor = parent.getSelectedItem().toString();
                        Log.e("cus", "select " + selectTextAuthor);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                maker.escape();
            }
            return null;
        }

    }

    protected TextView displayIDLayout(LayoutMaker maker, boolean b) {
        if (b) {
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("ID").center().color(Color.WHITE).get(), maker.layFW(2));
                eTI = maker.add(maker.createStyledEdit("").allowChars("0123456789").format(StyledText.Format.Number).bgc(Color.WHITE).maxLine(1).get(), maker.layFW(1));
//            eTI.addTextChangedListener(new TextWatcher() {
//
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
////                    if (eTI.getText().length() > 0) {
//////                        ((Spinner) spinBook).getSelectedView().setEnabled(false);
//////                        spinBook.setEnabled(false);
//////                        ((Spinner) spinAuthor).getSelectedView().setEnabled(false);
//////                        spinAuthor.setEnabled(false);
////
////                    }
//                }
//
//                public void afterTextChanged(Editable s) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            });
                maker.escape();
                return eTI;
            }
        } else {
            maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER_VERTICAL);
            {
                maker.add(maker.createStyledText("ID").center().color(Color.WHITE).get(), maker.layFW(1));
                spinId = maker.addSpinner(idAdapter, maker.layAbsolute(0, 0, (maker.getDisplayAgent().getWidth() / 5) * 2, 80));
                spinId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectTextId = parent.getSelectedItem().toString();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                maker.escape();
            }
            return null;
        }
    }

    public boolean insertData(String b, String a) {
        if (b.length() > 0 && a.length() > 0) {
            Book book = new Book(b, a);
            helper.insert(book);
            listAdapter.refresh();

            refreshAll();
            new ToastAction(GenericHelperDemo.this).setMessage("success").execute();
            return true;
        } else if (b.length() > 0 && a.length() == 0 || b.length() == 0 && a.length() > 0) {
            new ToastAction(GenericHelperDemo.this).setMessage("Please enter something").execute();
            return false;
        } else {
            new ToastAction(GenericHelperDemo.this).setMessage("Please enter something").execute();
            return false;
        }
    }

    private void randomData() {
        int i = (int) (Math.random() * 6);
        String name = bookArray[i][0];
        String author = bookArray[i][1];
        Book book = new Book(name, author);
        helper.insert(book);
        listAdapter.refresh();

        refreshAll();

        new ToastAction(GenericHelperDemo.this).setMessage("success").execute();
    }

    public boolean deleteEntrance(TextView id, String b, String a) {
        int d = 0;
        if (true) {
            if (id.getText().length() > 0) {
                deleteData("_id", id.getText().toString());
                d++;
            }
            if (b.length() > 0) {
                deleteData("bookName", b);
                d++;
            }
            if (a.length() > 0) {
                deleteData("bookAuthor", a);
                d++;
            }
        }
        if (d > 0) {
            return true;
        } else {
            return false;
        }
    }

    protected void deleteData(String c, String s) {
        try {
            helper.delete("where " + c + " = '" + s + "'");
            listAdapter.refresh();
            refreshAll();
        } catch (Exception e) {
            new ToastAction(GenericHelperDemo.this).setMessage("delete failed");
        }

    }

    public boolean updateData(String u, TextView b, TextView a) {
        if (!"-select-".equals(u) && b.getText().toString().length() > 0 && a.getText().toString().length() > 0) {
            Book bookUp = new Book(b.getText().toString(), a.getText().toString());
            bookUp.set_id(Integer.valueOf(u));
            helper.update(bookUp);
            listAdapter.refresh();
            refreshAll();
            new ToastAction(GenericHelperDemo.this).setMessage("Update success").execute();
            return true;
        } else {
            new ToastAction(GenericHelperDemo.this).setMessage("Update failed").execute();
            return false;
        }
    }

    public boolean selectEntrance(String id, String book, String author) {
        int d = 0;
        ArrayList<String> colText = new ArrayList<String>();
        ArrayList<String> colName = new ArrayList<String>();
        if (true) {
            if (id.length() > 0) {
                colName.add("_id");
                colText.add(id);
                d++;
            }
            if (book.length() > 0) {
                colName.add("bookName");
                colText.add(book);
                d++;
            }
            if (author.length() > 0) {
                colName.add("bookAuthor");
                colText.add(author);
                d++;
            }
        }
        if (d > 0) {
            selectData(colName, colText);
            new ToastAction(GenericHelperDemo.this).setMessage("Select success").execute();
            return true;
        } else {
            new ToastAction(GenericHelperDemo.this).setMessage("Select failed").execute();
            return false;
        }
    }

    public void selectData(ArrayList colName, ArrayList colText) {
        List<Book> select;
        if (colName.size() > 0) {
            if (colName.size() == 1) {
                select = helper.select("where " + colName.get(0) + " = '" + colText.get(0) + "'");
//            } else if (colName.size() == 4) {
//                select = helper.select("where " + colName.get(0) + " = '" + colName.get(1) + "'" + " or " + colName.get(2) + " = '" + colName.get(3) + "'");
//            } else {
//                select = helper.select("where " + colName.get(0) + " = '" + colName.get(1) + "'" + " or " + colName.get(2) + " = '" + colName.get(3) + "'" + " or " + colName.get(4) + " = '" + colName.get(5) + "'");
//            }
            } else {
                String s = "where " + colName.get(0) + " = '" + colText.get(0) + "'";
                for (int i = 1; i < colName.size(); i++) {
                    s = s + " or " + colName.get(i) + " = '" + colText.get(i) + "'";
                }
                select = helper.select(s);
            }
            listAdapter.setList(select);
            listAdapter.notifyDataSetChanged();
        }
    }

    protected void refreshAll() {
        refreshListTmp(idAdapter, idList, "_id");
        refreshListTmp(bookAdapter, bookList, "bookName");
        refreshListTmp(authorAdapter, authorList, "bookAuthor");
    }

    protected void refreshListTmp(ObjectAdapter adapter, ArrayList list, String col) {
        Cursor cursor = faceData.query("Select " + col + " from Book");
        list = new ArrayList<String>();
        list.add("-select-");
        while (cursor.moveToNext()) {
            boolean flag = false;
            Object obj = cursor.getString(0);
//            Log.e("cus", "cursor " + cursor.getString(0));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(obj.toString())) {
                    flag = true;
                }
            }
            if (!flag) {
                list.add((String) obj);
            }
        }
        cursor.close();
        adapter.setList(list);
        adapter.notifyDataSetChanged();
//        Log.e("cus", "bookList " + bookList.toString());
    }

//    protected void initObjectAdapter(ObjectAdapter oa, ArrayList l) {
//        oa = new ObjectAdapter<String>(this, l) {
//            @Override
//            public void fillRowView(int index, View cellRenderer, String item) throws Exception {
//                findView(cellRenderer, "init", TextView.class).setText(item);
//            }
//
//            @Override
//            public View createRowView(int index, String item) {
//                LinearLayout layout_main = new LinearLayout(context);
//                final LayoutMaker m = new LayoutMaker(context, layout_main, false);
//                m.addRowLayout(false, m.layFF());
//                {
//                    m.add(m.createStyledText("").tag("init").center().get(), m.layFW());
//                }
//                return layout_main;
//            }
//        };
//    }
    
    protected void initArrayList(ArrayList l){
        l = new ArrayList<String>();
        l.add("-select-");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); //To change body of generated methods, choose Tools | Templates.
        helper.drop();
//        Log.e("cus", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop(); //To change body of generated methods, choose Tools | Templates.
        helper.drop();
//        Log.e("cus", "onStop");
    }

}
