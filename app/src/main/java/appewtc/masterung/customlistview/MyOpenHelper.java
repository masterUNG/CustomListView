package appewtc.masterung.customlistview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 2/20/15 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_menu.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_FOOD_TABLE = "create table foodTABLE (_id integer primary key, Food text, Price text);";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   // Main Class
