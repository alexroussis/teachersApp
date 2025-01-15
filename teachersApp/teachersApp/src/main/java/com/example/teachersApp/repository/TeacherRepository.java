package com.example.teachersApp.repository;

import com.example.teachersApp.service.exceptions.TeacherNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.teachersApp.model.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("select t from Teacher t where t.email = ?1")
    Teacher findTeacherByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Teacher t set t.fname = ?2, t.lname = ?3, t.email = ?4 where t.id = ?1")
    void updateTeacher(int id, String fname, String lname, String email);


    @Query("select t from Teacher t where lname like ?1%")
    List<Teacher> getTeachersByLname(String lname) throws TeacherNotFoundException;
}
