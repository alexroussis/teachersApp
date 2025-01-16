package com.example.teachersApp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "fname", nullable = false, length = 45)
	private String fname;
	@Column(name = "lname", nullable = false, length = 45)
	private String lname;
	@Column(name = "email", nullable = false, length = 45)
	private String email;
	
	public Teacher() {}
	
	public Teacher(int id, String fname, String lname, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {this.id = id;}

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

	public String getEmail() { return email;}

	public void setEmail(String email) { this.email = email;}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", fname='" + fname + '\'' +
				", lname='" + lname + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
