package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {

		Student saveStudent = studentService.saveStudent(student);
		return ResponseEntity.ok(saveStudent);
	}

	@GetMapping
	public List<Student> getAll() {

		List<Student> allData = studentService.getAllData();
		return allData;
	}

	@PutMapping("/update/{id}")
	public Student updateStudentData(@PathVariable("id") long id, @RequestBody Student student) {

		Student updateStudentData = studentService.updateStudentData(id, student);
		return updateStudentData;
	}

	@GetMapping("/{id}")
	public Student getOneStudentData(@PathVariable("id") long id) {

		Student studentById = studentService.getStudentById(id);
		return studentById;
	}

	@DeleteMapping("/delete/{id}")
	public boolean deletDataById(@PathVariable("id") long id) {

		boolean deleteStudentById = studentService.deleteStudentById(id);
		return deleteStudentById;
	}
}
