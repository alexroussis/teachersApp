package com.example.teachersApp.service.exceptions;

public class TeacherNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TeacherNotFoundException(String lname) {
		super("Teacher with last name = " + lname + " does no exist");
	}

}
