package com.example.bt4;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClassRepository {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public ClassRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Thêm lớp học
    public long addClass(ClassModel classModel) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CLASS_NAME, classModel.getName());
        values.put(DatabaseHelper.COLUMN_STUDENT_COUNT, classModel.getStudentCount());
        values.put(DatabaseHelper.COLUMN_TEACHER, classModel.getTeacher());
        return database.insert(DatabaseHelper.TABLE_CLASS, null, values);
    }

    // Lấy danh sách lớp học
    public List<ClassModel> getAllClasses() {
        List<ClassModel> classes = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_CLASS,
                null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASS_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASS_NAME));
                int studentCount = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENT_COUNT));
                String teacher = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER));
                classes.add(new ClassModel(id, name, studentCount, teacher));
            }
            cursor.close();
        }

        return classes;
    }
}
