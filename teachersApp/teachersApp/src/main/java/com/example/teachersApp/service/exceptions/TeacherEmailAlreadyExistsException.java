package com.example.teachersApp.service.exceptions;
import com.example.teachersApp.model.Teacher;

public class TeacherEmailAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeacherEmailAlreadyExistsException(Teacher teacher) {
		super("Teacher with E-Mail = " + teacher.getEmail() + " already exist");
	}

}