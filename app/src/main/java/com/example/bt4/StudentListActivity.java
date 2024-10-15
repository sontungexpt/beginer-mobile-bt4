package com.example.bt4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private RecyclerView studentRecyclerView;
    private StudentAdapter studentAdapter;
    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        studentRecyclerView = findViewById(R.id.studentRecyclerView);
        studentRepository = new StudentRepository(this);

        // Get the class ID from the intent
        int classId = getIntent().getIntExtra("classId", -1);

        // Check if class ID is valid
        if (classId != -1) {
            updateStudentList(classId);
        } else {
            Toast.makeText(this, "Invalid Class ID", Toast.LENGTH_SHORT).show();
        }

        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateStudentList(int classId) {
        List<StudentModel> studentList = studentRepository.getStudentsByClassId(classId);
        studentAdapter = new StudentAdapter(studentList);
        studentRecyclerView.setAdapter(studentAdapter);
    }
}
