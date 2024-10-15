package com.example.bt4;

import android.content.Intent;
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
    public static final int ADD_STUDENT_REQUEST_CODE = 1;

    private EditText classNameEditText;
    private Button addClassButton;
    private RecyclerView classRecyclerView;
    private ClassAdapter classAdapter;
    private ClassRepository classRepository;
    private EditText teacherEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classNameEditText = findViewById(R.id.classNameEditText);
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
                String teacherName = teacherEditText.getText().toString();

                if (!className.isEmpty()  && !teacherName.isEmpty()) {
                    classRepository.addClass(new ClassModel(0, className,  teacherName));
                    updateClassList();
                    clearInputs();
                } else {
                    Toast.makeText(MainActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateClassList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_REQUEST_CODE && resultCode == RESULT_OK) {
            // Refresh the class list
            updateClassList();
        }
    }


    private void clearInputs() {
        classNameEditText.setText("");
        teacherEditText.setText("");
    }

    private void updateClassList() {
        List<ClassModel> classList = classRepository.getAllClasses();
        classAdapter = new ClassAdapter(classList, classRepository);
        classRecyclerView.setAdapter(classAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        classRepository.close();
    }
}
