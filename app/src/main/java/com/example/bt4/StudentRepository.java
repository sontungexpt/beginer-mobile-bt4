package com.example.bt4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public StudentRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public void insertStudent(ContentValues values) {
        database.insert(DatabaseHelper.TABLE_STUDENT, null, values);
    }
    // Lấy danh sách sinh viên theo ID lớp học
    public List<StudentModel> getStudentsByClassId(int classId) {
        List<StudentModel> students = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_STUDENT,
                null, DatabaseHelper.COLUMN_STUDENT_CLASS_ID + "=?", new String[]{String.valueOf(classId)}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENT_NAME));
                students.add(new StudentModel(id, name, classId));
            }
            cursor.close();
        }

        return students;
    }
}
