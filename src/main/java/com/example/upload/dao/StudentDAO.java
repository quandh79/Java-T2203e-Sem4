package com.example.upload.dao;

import com.example.upload.entity.StudentEntity;

import java.util.List;

public interface StudentDAO {
void createStudent(StudentEntity student);
void updateStudent(StudentEntity student);
StudentEntity getStudentById(int id);
void deleteStudent(int id);
List<StudentEntity> getAllStudent();
List<StudentEntity> getStudentsWithPagination(int pageNumber, int pageSize);
List<StudentEntity> searchStudents(String keyword);
}
