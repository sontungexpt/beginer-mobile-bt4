package com.example.bt4;


import static com.example.bt4.DatabaseHelper.COLUMN_STUDENT_CLASS_ID;
import static com.example.bt4.DatabaseHelper.COLUMN_STUDENT_ID;
import static com.example.bt4.DatabaseHelper.COLUMN_STUDENT_NAME;
import static com.example.bt4.DatabaseHelper.TABLE_STUDENT;

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

    public long addStudent(StudentModel studentModel) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_STUDENT_NAME, studentModel.getName());
        values.put(COLUMN_STUDENT_CLASS_ID, studentModel.getClassId());
        return database.insert(TABLE_STUDENT, null, values);
    }


    // Thêm lớp học
    public long addClass(ClassModel classModel) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CLASS_NAME, classModel.getName());
        values.put(DatabaseHelper.COLUMN_TEACHER, classModel.getTeacher());
        return database.insert(DatabaseHelper.TABLE_CLASS, null, values);
    }



    // Lấy danh sách sinh viên theo ID lớp học
    public List<StudentModel> getStudentsByClassId(int classId) {
        List<StudentModel> students = new ArrayList<>();

        Cursor cursor = database.query(TABLE_STUDENT,
                null, COLUMN_STUDENT_CLASS_ID + "=?", new String[]{String.valueOf(classId)}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME));
                students.add(new StudentModel(id, name, classId));
            }
            cursor.close();
        }

        return students;
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
                String teacher = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER));
                classes.add(new ClassModel(id, name, teacher));
            }
            cursor.close();
        }

        return classes;
    }
}
