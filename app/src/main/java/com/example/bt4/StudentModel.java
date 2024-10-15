package com.example.bt4;


public class StudentModel {
    private int id;
    private String name;
    private int classId;

    public StudentModel(int id, String name, int classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
