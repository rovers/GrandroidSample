/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.adapter;

import android.os.Bundle;
import android.view.View;
import grandroid.adapter.FaceDataAdapter;
import grandroid.database.FaceData;
import grandroid.database.GenericHelper;
import grandroid.fancyview.combobox.ComboBox;
import grandroid.samples.demos.SampleFace;
import grandroid.samples.demos.database.Book;

public class AutoCompleteDemo extends SampleFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GenericHelper<Book> helper= new GenericHelper<Book>(new FaceData(this, "GenericHelperDemoBase"), Book.class);;
        maker.addColLayout(false, maker.layFF());
        {
            ComboBox cb = new ComboBox(this);
//            cb.setAdapter(new FaceDataAdapter<IdentifiableDemo>(this,helper){
//
//                @Override
//                public View createRowView(int index, Book item) {
//                }
//
//                @Override
//                public void fillRowView(int index, View cellRenderer, Book item) {
//                }
//            });
            maker.add(cb, maker.layFW());
            maker.escape();
        }

    }

}
