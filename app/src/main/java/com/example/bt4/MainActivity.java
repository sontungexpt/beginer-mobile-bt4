package com.example.bt4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText classNameEditText;
    private Button addClassButton;
    private RecyclerView classRecyclerView;
    private ClassAdapter classAdapter;
    private ClassRepository classRepository;
    private EditText studentCountEditText;
    private EditText teacherEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classNameEditText = findViewById(R.id.classNameEditText);
        studentCountEditText = findViewById(R.id.studentCountEditText);
        teacherEditText = findViewById(R.id.teacherEditText);
        addClassButton = findViewById(R.id.addClassButton);
        classRecyclerView = findViewById(R.id.classRecyclerView);

        classRepository = new ClassRepository(this);
        classRepository.open();

        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = classNameEditText.getText().toString();
                String studentCountStr = studentCountEditText.getText().toString();
                String teacherName = teacherEditText.getText().toString();

                if (!className.isEmpty() && !studentCountStr.isEmpty() && !teacherName.isEmpty()) {
                    int studentCount = Integer.parseInt(studentCountStr);
                    classRepository.addClass(new ClassModel(0, className, studentCount, teacherName));
                    updateClassList();
                    classNameEditText.setText("");
                    studentCountEditText.setText("");
                    teacherEditText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateClassList();
    }
    private void updateClassList() {
        List<ClassModel> classList = classRepository.getAllClasses();
        classAdapter = new ClassAdapter(classList);
        classRecyclerView.setAdapter(classAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        classRepository.close();
    }
}
