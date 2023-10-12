package com.example.upload.dao;

import com.example.upload.entity.StudentEntity;

import java.util.List;

public interface StudentDAO {
void createStudent(StudentEntity student);
List<StudentEntity> getAllStudent();
}
