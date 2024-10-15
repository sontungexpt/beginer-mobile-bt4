package com.example.bt4;


import static com.example.bt4.MainActivity.ADD_STUDENT_REQUEST_CODE;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private List<ClassModel> classList;
    private ClassRepository classRepository;

    public ClassAdapter(List<ClassModel> classList, ClassRepository classRepository) {
        this.classList = classList;
        this.classRepository = classRepository;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassModel classModel = classList.get(position);
        holder.classNameTextView.setText("Class: " + classModel.getName());
        classRepository.open();
        List<StudentModel> studentModels = classRepository.getStudentsByClassId(classModel.getId());
        holder.studentCountTextView.setText("Students: " + studentModels.size());
        holder.teacherTextView.setText("Teacher: " + classModel.getTeacher());

        // Set an OnClickListener to handle clicks
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudentListActivity.class);
                intent.putExtra("classId", classModel.getId()); // Pass the class ID
                v.getContext().startActivity(intent);
            }
        });

        holder.buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddStudentActivity.class);
                intent.putExtra("classId", classModel.getId()); // Pass class ID

                ((MainActivity) v.getContext()).startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE);
            }
        });
    }



    // Cập nhật ViewHolder để thêm các TextView mới
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView classNameTextView;
        TextView studentCountTextView;
        TextView teacherTextView;
        Button buttonAddStudent;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            classNameTextView = itemView.findViewById(R.id.classNameTextView);
            studentCountTextView = itemView.findViewById(R.id.studentCountTextView);
            teacherTextView = itemView.findViewById(R.id.teacherTextView);
            buttonAddStudent = itemView.findViewById(R.id.buttonAddStudent);
        }
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }


}
