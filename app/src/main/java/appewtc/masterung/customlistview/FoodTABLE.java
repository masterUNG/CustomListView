package appewtc.masterung.customlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 2/21/15 AD.
 */
public class FoodTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABEL_FOOD = "foodTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FOOD = "Food";
    public static final String COLUMN_PRIVE = "Price";

    public FoodTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public String[] listPrice() {

        String strListPrice[] = null;
        Cursor objCursor = readSQLite.query(TABEL_FOOD, new String[]{COLUMN_ID, COLUMN_PRIVE}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListPrice = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListPrice[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_PRIVE));
            objCursor.moveToNext();
        }

        objCursor.close();
        return strListPrice;
    }

    public String[] listFood() {

        String strListFood[] = null;

        Cursor objCursor = readSQLite.query(TABEL_FOOD, new String[]{COLUMN_ID, COLUMN_FOOD}, null, null, null, null, null);

        strListFood = new String[objCursor.getCount()];

        objCursor.moveToFirst();

        for (int i = 0; i < objCursor.getCount(); i++) {

            strListFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_FOOD));
            objCursor.moveToNext();

        }   //for
        objCursor.close();

        return strListFood;
    }

    public long addValueFood(String strFood, String strPrice) {
        ContentValues objConteValue = new ContentValues();
        objConteValue.put(COLUMN_FOOD, strFood);
        objConteValue.put(COLUMN_PRIVE, strPrice);
        return writeSQLite.insert(TABEL_FOOD, null, objConteValue);
    }

}   // Main Class
