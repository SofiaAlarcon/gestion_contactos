package com.alarcon063.gestioncontactos.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "mi_agenda.db";

    public static final String TABLE_CONTACTOS = "contactos";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "phone TEXT NOT NULL," +
                        "lastName TEXT," +
                        "address TEXT," +
                        "gender TEXT)"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        onCreate(sqLiteDatabase);
    }
}
