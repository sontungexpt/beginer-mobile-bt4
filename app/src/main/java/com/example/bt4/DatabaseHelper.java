package com.example.bt4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "class_manager.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_CLASS = "class_table";
    public static final String COLUMN_CLASS_ID = "class_id";
    public static final String COLUMN_CLASS_NAME = "class_name";

    public static final String TABLE_STUDENT = "student_table";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENT_NAME = "student_name";
    public static final String COLUMN_STUDENT_CLASS_ID = "class_id";
    public static final String COLUMN_STUDENT_COUNT = "student_count"; // Số lượng sinh viên
    public static final String COLUMN_TEACHER = "teacher"; // Tên giáo viên

    // SQL statement to create class table
    private static final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_CLASS + "("
            + COLUMN_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CLASS_NAME + " TEXT NOT NULL,"
            + COLUMN_STUDENT_COUNT + " INTEGER NOT NULL,"
            + COLUMN_TEACHER + " TEXT NOT NULL);";

    // SQL statement to create student table
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "("
            + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_NAME + " TEXT NOT NULL,"
            + COLUMN_STUDENT_CLASS_ID + " INTEGER,"
            + "FOREIGN KEY (" + COLUMN_STUDENT_CLASS_ID + ") REFERENCES " + TABLE_CLASS + "(" + COLUMN_CLASS_ID + "));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLASS);
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }
}
