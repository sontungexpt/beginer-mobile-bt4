package com.example.bt4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    private EditText studentNameEditText;
    private Button confirmButton;
    private  ClassRepository classRepository;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        classRepository.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_name_input);

        studentNameEditText = findViewById(R.id.studentNameEditText);
        confirmButton = findViewById(R.id.confirmButton);

        // Get the class ID from the intent
        int classId = getIntent().getIntExtra("classId", -1); // -1 if no ID is passed
        // Initialize the repository
        classRepository = new ClassRepository(this);
        classRepository.open();
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = studentNameEditText.getText().toString();

                if (!studentName.isEmpty() && classId != -1) {
                    // Add the student to the class directly
                    classRepository.addStudent(new StudentModel(0, studentName, classId));
                    Toast.makeText(AddStudentActivity.this, "Student added successfully!", Toast.LENGTH_SHORT).show();
                    // Set result to OK before finishing
                    setResult(RESULT_OK);
                    finish();  // Close the activity after adding the student
                } else {
                    Toast.makeText(AddStudentActivity.this, "Student name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
