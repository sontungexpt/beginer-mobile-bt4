package com.example.bt4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private List<ClassModel> classList;

    public ClassAdapter(List<ClassModel> classList) {
        this.classList = classList;
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
        holder.studentCountTextView.setText("Students: " + classModel.getStudentCount());
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
    }

    // Cập nhật ViewHolder để thêm các TextView mới
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView classNameTextView;
        TextView studentCountTextView;
        TextView teacherTextView;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            classNameTextView = itemView.findViewById(R.id.classNameTextView);
            studentCountTextView = itemView.findViewById(R.id.studentCountTextView);
            teacherTextView = itemView.findViewById(R.id.teacherTextView);
        }
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }


}
