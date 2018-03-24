package com.example.tutorial5.service;

import com.example.tutorial5.dao.StudentMapper;
import com.example.tutorial5.model.StudentModel;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class StudentServiceDatabaseTest {
    private StudentService studentService = new StudentServiceDatabase();
    @Mock
    private StudentMapper studentMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.studentService = new StudentServiceDatabase(this.studentMapper);
    }

    @Test
    public void selectStudent() {
        // Given
        StudentModel studentModel = new StudentModel("1506737823", "Chanek", 3.5);
        BDDMockito.given(studentMapper.selectStudent("1506737823")).willReturn(studentModel);

        // When
        StudentModel test = studentService.selectStudent("1506737823");

        // Then
        assertThat(test, notNullValue()); // Check if Not Null
        assertThat(test, equalTo(studentModel)); // Check if Same
    }

    @Test
    public void selectAllStudents() {
        // Given
        List<StudentModel> studentModels = new ArrayList<>();
        StudentModel studentModel = new StudentModel("1506737823", "Chanek", 3.5);
        studentModels.add(studentModel);

        BDDMockito.given(studentMapper.selectAllStudents()).willReturn(studentModels);

        // When
        List<StudentModel> test = studentService.selectAllStudents();

        // Then
        assertThat(test, notNullValue()); // Check if Not Null
        assertThat(test.isEmpty(), equalTo(false)); // Check kalo ngga kosong
        assertThat(test.size(), equalTo(1)); // Check if Size same
        assertThat(test, equalTo(studentModels)); // Check kalo konten sama
    }

    @Test
    public void addStudent() {
        // Given
        StudentModel studentModel = new StudentModel("1506737823", "Chanek", 3.5);
        BDDMockito.given(studentService.addStudent(studentModel)).willReturn(true);

        // When
        boolean test = studentService.addStudent(studentModel);

        // Then
        BDDMockito.then(studentMapper).should().addStudent(studentModel);
        assertThat(test, equalTo(true)); // Check if Same
    }

    @Test
    public void updateStudent() {
        StudentModel studentModel = new StudentModel("1506737823", "Chanek", 3.5);
        BDDMockito.given(studentService.updateStudent(studentModel)).willReturn(true);

        boolean test = studentService.updateStudent(studentModel);
        BDDMockito.then(studentMapper).should().updateStudent(studentModel);

        assertEquals(test, true); // Check if Same
    }

    @Test
    public void deleteStudent() {
        String npm = "1506737823";
        BDDMockito.given(studentService.deleteStudent(npm)).willReturn(true);

        boolean test = studentService.deleteStudent(npm);
        BDDMockito.then(studentMapper).should().deleteStudent(npm);

        assertEquals(test, true); // Check if Same
    }
}
