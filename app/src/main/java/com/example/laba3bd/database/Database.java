package com.example.laba3bd.database;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import lombok.Getter;

public class Database extends Application {
    private static DatabaseHelper databaseHelper;
    @Getter
    private static SQLiteDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        // Создание экземпляра класса-помощника базы данных
        databaseHelper = new DatabaseHelper(this);
        // Получение базы данных для записи
        database = databaseHelper.getWritableDatabase();
    }

}
