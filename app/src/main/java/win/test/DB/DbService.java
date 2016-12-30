package win.test.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by win on 2016/12/21.
 */

public class DbService {



    /**
     * 增加用户
     *
     * @param userId
     * @param password
     */
    public static void addUser(String userId, String password ) {
        Db db = Db.getInstance();
        SQLiteDatabase dbWrite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", password);
        cv.put("userId", userId);

        dbWrite.execSQL("DELETE FROM user WHERE userId=?", new String[]{ userId});

        dbWrite.insert("user", null, cv);
        dbWrite.close();
    }

    /**
     * 查询   用户密码
     * @param userId
     */
    public static String queryPassword(String userId) {
        Db db = Db.getInstance();
        SQLiteDatabase dbRead = db.getReadableDatabase();
        Cursor cursor = dbRead.query("user", null, "userId=?", new String[]{userId}, null, null, null);

        String password = null;
        while (cursor.moveToNext()) {
            password = cursor.getString(2);//获取第二列的值
        }
        cursor.close();
        db.close();
        return password;
    }





    /**
     * 增加记录
     *
     * @param name
     * @param isYue
     */
    public static void addRecord(String name, String isYue, String userId) {
        Db db = Db.getInstance();
        SQLiteDatabase dbWrite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("isYue", isYue);
        cv.put("userId", userId);
        dbWrite.execSQL("DELETE FROM record WHERE name = ? AND userId=?", new String[]{name, userId});

        dbWrite.insert("record", null, cv);
        dbWrite.close();
    }



    /**
     * 查询单条记录
     * @param name
     */
    public static String queryRecord(String name,String userId) {
        Db db = Db.getInstance();
        SQLiteDatabase dbRead = db.getReadableDatabase();
        Cursor cursor = dbRead.query("record", null, "name=? AND userId=?", new String[]{name,userId}, null, null, null);

        String isYue = null;
        while (cursor.moveToNext()) {
            isYue = cursor.getString(3);//获取第二列的值
        }
        cursor.close();
        db.close();
        return isYue;
    }


    /**
     * 查询全部记录
     */
    public static void queryWholeRecord() {
        Db db = Db.getInstance();
        SQLiteDatabase dbRead = db.getReadableDatabase();


        dbRead.query("record", null, null, null, null, null, null);
    }


}
