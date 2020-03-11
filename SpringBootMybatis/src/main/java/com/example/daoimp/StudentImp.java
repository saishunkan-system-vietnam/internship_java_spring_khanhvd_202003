package com.example.daoimp;

import java.util.List;
import java.util.Optional;

import com.example.dto.StudentDTO;
import com.example.entities.Student;

public interface StudentImp {
	public abstract List<Student> getAllListStudent();

	void saveStudent(Student student);

	void updateStudent(Student student);

	void deleteStudent(Student student);

	Student findOne(int id);

	// List<Student> findByName(String name);
	List<Student> search(StudentDTO student);

	Optional<Student> findStudentById(Long id);
	
	int findCountStudents(StudentDTO student);
}