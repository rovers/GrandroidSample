/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.database;

import android.content.Context;
import android.database.Cursor;
import grandroid.database.Identifiable;
import grandroid.database.RawFaceData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jack Huang
 * @param <T>
 */
public abstract class Widget<T extends Identifiable> extends RawFaceData {

    public Widget(Context context, String dbName, boolean transactionMode) {
        super(context, dbName, transactionMode);
    }
    public List<T> selectColumn(String tableName,String columnName){
        Cursor cursor = db4read.rawQuery("Select "+columnName+" from " + tableName +" GROUP BY "+columnName, null);
        ArrayList<T> list = new ArrayList<T>();
        while (cursor.moveToNext()) {
            T obj = this.getObject(cursor);
            if (obj != null) {
                list.add(obj);
            }
        }
        cursor.close();
        return list;
    }
    public abstract T getObject(Cursor cursor);
}
