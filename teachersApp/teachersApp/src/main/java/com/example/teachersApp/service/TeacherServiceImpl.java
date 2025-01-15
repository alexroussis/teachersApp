package com.example.teachersApp.service;

import com.example.teachersApp.model.Teacher;
import com.example.teachersApp.model.TeacherDto;
import com.example.teachersApp.service.exceptions.TeacherEmailAlreadyExistsException;
import com.example.teachersApp.service.exceptions.TeacherNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.teachersApp.repository.TeacherRepository;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    @Override
    public List<Teacher> getTeachers(){                      //RETURNS ALL TEACHERS
        return teacherRepository.findAll();
    }

    @Override
    public void insertTeacher(TeacherDto teacherDto) throws TeacherEmailAlreadyExistsException {        //SAVE TO DB
        Teacher teacher = new Teacher();
        teacher.setFname(teacherDto.getFname());
        teacher.setLname(teacherDto.getLname());
        teacher.setEmail(teacherDto.getEmail());
        if(teacherRepository.findTeacherByEmail(teacherDto.getEmail()) != null){
            throw new TeacherEmailAlreadyExistsException(teacher);
        }
        teacherRepository.save(teacher);
    }
    @Override
    public TeacherDto setTeacherDto(Teacher teacher){                   //SETS TEACHER DTO
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFname(teacher.getFname());
        teacherDto.setLname(teacher.getLname());
        teacherDto.setEmail(teacher.getEmail());
        return teacherDto;
    }
    @Override
    public void updateTeacher(TeacherDto teacherDto) throws TeacherEmailAlreadyExistsException {        //UPDATE TEACHER AND CHECK FOR EMAIL(UNIQUE)
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setFname(teacherDto.getFname());
        teacher.setLname(teacherDto.getLname());
        teacher.setEmail(teacherDto.getEmail());

        if(teacherRepository.findTeacherByEmail(teacherDto.getEmail())!= null){
            if(teacherRepository.findTeacherByEmail(teacherDto.getEmail()).getId() != teacherDto.getId()){
                throw new TeacherEmailAlreadyExistsException(teacher);
            }
            try {
                teacherRepository.updateTeacher(teacher.getId(), teacher.getFname(), teacher.getLname(), teacher.getEmail());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        try {
            teacherRepository.updateTeacher(teacher.getId(), teacher.getFname(), teacher.getLname(), teacher.getEmail());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @Override
    public void deleteTeacher(Teacher teacher){          //DELETE TEACHER
        teacherRepository.delete(teacher);
    }
    @Override
    public Teacher findById(int id){                //SEARCH BY ID
        return teacherRepository.findById(id).get();
    }

    @Override
    public List<Teacher> findByLname(String lname) throws TeacherNotFoundException {             //SEARCH BY LAST NAME
        try {
            if(teacherRepository.getTeachersByLname(lname).isEmpty()){
                throw new TeacherNotFoundException(lname);
            }
        }
        catch (TeacherNotFoundException e){
            System.out.println(e);

        }
        return teacherRepository.getTeachersByLname(lname);
    }
}
