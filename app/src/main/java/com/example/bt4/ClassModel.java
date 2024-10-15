package com.example.bt4;

public class ClassModel {
    private int id;
    private String name;
    private String teacher; // Giáo viên phụ trách

    public ClassModel(int id, String name,  String teacher) {
        this.id = id;
        this.name = name;
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


    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
