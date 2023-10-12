package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Student saveStudent(Student student) {
		Student save = studentRepo.save(student);
		return save;
	}

	@Override
	public List<Student> getAllData() {

		return studentRepo.findAll();
	}

	@Override
	public Student updateStudentData(long id, Student student) {
		Student findById = studentRepo.findById(id).get();
		findById.setName(student.getName());
		findById.setEmail(student.getEmail());
		findById.setFee(student.getFee());
		return studentRepo.save(findById);
	}

	@Override
	public Student getStudentById(long id) {
		Student orElse = studentRepo.findById(id).orElse(null);
		return orElse;
	}

	@Override
	public boolean deleteStudentById(Long id) {

		if (studentRepo.existsById(id)) {
			studentRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
