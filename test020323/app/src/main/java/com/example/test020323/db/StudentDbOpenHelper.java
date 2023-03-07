package com.example.test020323.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentDbOpenHelper extends SQLiteOpenHelper {


    public StudentDbOpenHelper(@Nullable Context context) {
        super(context,
                StudentContractReader.DATABASE_NAME,
                null,
                StudentContractReader.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + StudentContractReader.StudentEntry.TABLE_NAME + " (" +
                        StudentContractReader.StudentEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        StudentContractReader.StudentEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        StudentContractReader.StudentEntry.COLUMN_AGE + " INTEGER NOT NULL, " +
                        StudentContractReader.StudentEntry.COLUMN_PHONE + " TEXT NOT NULL, " +
                        StudentContractReader.StudentEntry.COLUMN_EMAIL + " TEXT );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                "DROP TABLE IF EXISTS " + StudentContractReader.StudentEntry.TABLE_NAME + " ;"
        );
        onCreate(db);
    }
}
