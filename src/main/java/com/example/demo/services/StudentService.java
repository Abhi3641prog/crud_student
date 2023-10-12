package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Student;



public interface StudentService {

	public Student saveStudent(Student student);

	public List<Student> getAllData();

	public Student updateStudentData(long id, Student student);

	public Student getStudentById(long id);

	public boolean deleteStudentById(Long id);

	
   
	
}
