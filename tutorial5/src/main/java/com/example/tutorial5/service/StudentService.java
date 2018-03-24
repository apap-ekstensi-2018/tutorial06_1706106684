package com.example.tutorial5.service;

import java.util.List;

import com.example.tutorial5.model.StudentModel;

public interface StudentService
{
    StudentModel selectStudent(String npm);

    List<StudentModel> selectAllStudents();

    boolean addStudent(StudentModel student);

    boolean updateStudent(StudentModel student);

    boolean deleteStudent(String npm);
}
