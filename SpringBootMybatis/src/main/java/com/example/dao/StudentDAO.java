package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.daoimp.StudentImp;
import com.example.dto.StudentDTO;
import com.example.entities.Student;
import com.example.mapper.StudentMapper;

@Repository
public class StudentDAO implements StudentImp {

	@Autowired
	StudentMapper studentMapper;

	@Override
	public List<Student> getAllListStudent() {
		// TODO Auto-generated method stub
		List<Student> students = studentMapper.getAllStudents();
		return students;
	}

	@Override
	public void saveStudent(Student student) {
		// TODO Auto-generated method stub
		studentMapper.saveStudent(student);

	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub
		studentMapper.deleteStudent(student);
	}

	@Override
	public Student findOne(int id) {
		// TODO Auto-generated method stub
		return studentMapper.findOne(id);
	}

	@Override
	public Optional<Student> findStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentMapper.findStudentById(id);
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		studentMapper.updateStudent(student);
	}

	/*
	 * @Override public List<Student> findByName(String name) { // TODO
	 * Auto-generated method stub return studentMapper.findByName(name); }
	 */

	@Override
	public List<StudentDTO> search(String name, Integer min, Integer max) { // TODO Auto-generated method stub
		return studentMapper.search(name, min, max);
	}


	}
	
