package com.example.bt4;

public class ClassModel {
    private int id;
    private String name;
    private int studentCount; // Số lượng sinh viên
    private String teacher; // Giáo viên phụ trách

    public ClassModel(int id, String name, int studentCount, String teacher) {
        this.id = id;
        this.name = name;
        this.studentCount = studentCount;
        this.teacher = teacher;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
