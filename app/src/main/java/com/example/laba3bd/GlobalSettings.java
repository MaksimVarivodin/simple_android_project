package com.example.laba3bd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.laba3bd.database.Database;
public class GlobalSettings {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mydatabase.db";

    public static final String TABLE_NAME = "mytable";

    public static final String ServiceId_FIELD = "s_id";

    public static final String ServiceTimeAdded_FIELD = "s_added_time";

    public static final String ServiceName_FIELD = "s_name";

    public static final String ServiceDescription_FIELD = "s_description";

    public static final String ServicePrice_FIELD = "s_price";

    public static String createTableQuery() {
        return "CREATE TABLE " + TABLE_NAME +
                " (" +
                ServiceId_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ServiceTimeAdded_FIELD + " TEXT," +
                ServiceName_FIELD + " TEXT, " +
                ServiceDescription_FIELD + " TEXT, " +
                ServicePrice_FIELD + " REAL" +
                ")";

    }

    public static String dropTableQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    }

    public static String selectTableQuery() {
        return "SELECT * FROM " + TABLE_NAME + ";";
    }
    public static int countTableRecordsQuery(){
        SQLiteDatabase db = Database.getDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+TABLE_NAME+";", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }
    public static int countFoundInTableQuery(int serviceId){
        SQLiteDatabase db = Database.getDatabase();
        Cursor cursor = db.rawQuery(
                    "SELECT COUNT(*) FROM " + TABLE_NAME +
                        " WHERE " + ServiceId_FIELD + " = "+ serviceId +  ";"
                , null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }
    public static String deleteByIdQuery(int serviceId){
        return "DELETE FROM "+ TABLE_NAME +" WHERE " + ServiceId_FIELD + " = "+ serviceId +  ";";
    }
}
