package com.example.teachersApp.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

public class TeacherDto {
    @Id
    private int id;
    @NotEmpty(message = "First name is required!")
    private String fname;
    @NotEmpty(message = "Last name is required!")
    private String lname;
    @NotEmpty(message = "E-Mail is required!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Please input a valid E-mail!")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
