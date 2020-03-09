package com.example.daoimp;

import java.util.List;
import java.util.Optional;

import com.example.entities.Student;

public interface StudentImp{
	public abstract List<Student> getAllListStudent();
	void saveStudent(Student student);
	void updateStudent(Student student);
	void deleteStudent(Student student);
	Student findOne(int id);
	//List<Student> findByName(String name);
	List<Student> search(String name, Integer min, Integer max);
	Optional<Student> findStudentById(Long id);
}