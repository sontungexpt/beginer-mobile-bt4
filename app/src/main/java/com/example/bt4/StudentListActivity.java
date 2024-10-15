package com.example.bt4;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private StudentRepository studentRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        // Nhận classId từ intent
        int classId = getIntent().getIntExtra("classId", -1);

        recyclerView = findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentRepository = new StudentRepository(this);
        studentRepository.open();

        // Lấy danh sách sinh viên theo classId
        List<StudentModel> studentList = studentRepository.getStudentsByClassId(classId);

        // Gán danh sách sinh viên vào Adapter
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);

        studentRepository.close();
    }
}
