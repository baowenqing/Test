package win.test.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import win.test.App;

/**
 * Created by win on 2016/12/21.
 */

public class Db extends SQLiteOpenHelper {
    private static Db instance;

    public static Db getInstance() {
        if (instance == null) {
            instance = new Db(App.getInstance());
        }
        return instance;
    }

    private Db(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //存储  用户信息
        db.execSQL("CREATE TABLE IF NOT EXISTS user ( "+
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "userId TEXT DEFAULT \"\"," +
                "password TEXT DEFAULT \"\")" );


        //存储  预约记录
        db.execSQL("CREATE TABLE IF NOT EXISTS record (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "userId TEXT DEFAULT \"\"," +
                "name TEXT DEFAULT \"\"," +
                "isYue TEXT DEFAULT \"\")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
