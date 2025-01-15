package com.example.teachersApp.service;

import com.example.teachersApp.model.Teacher;
import com.example.teachersApp.model.TeacherDto;
import com.example.teachersApp.service.exceptions.TeacherEmailAlreadyExistsException;
import com.example.teachersApp.service.exceptions.TeacherNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService {


    List<Teacher> getTeachers();

    void insertTeacher(TeacherDto teacherDtO) throws TeacherEmailAlreadyExistsException, SQLException;

    TeacherDto setTeacherDto(Teacher teacher);

    void updateTeacher(TeacherDto teacherDto) throws TeacherEmailAlreadyExistsException, SQLException;

    void deleteTeacher(Teacher teacher);

    Teacher findById(int id);

    List<Teacher> findByLname(String lname) throws TeacherNotFoundException, SQLException;
}
