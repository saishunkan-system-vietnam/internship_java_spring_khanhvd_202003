package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDAO;
import com.example.daoimp.StudentImp;
import com.example.entities.Student;

@Service
public class StudentService implements StudentImp{

	@Autowired
	StudentDAO studentDAO;
	
	@Override
	public List<Student> getAllListStudent() {
		// TODO Auto-generated method stub
		return studentDAO.getAllListStudent();
	}

	@Override
	public void saveStudent(Student student) {
		// TODO Auto-generated method stub
		studentDAO.saveStudent(student);
	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub
		studentDAO.deleteStudent(student);
	}

	@Override
	public Student findOne(int id) {
		// TODO Auto-generated method stub
		return studentDAO.findOne(id);
	}

	@Override
	public Optional<Student> findStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentDAO.findStudentById(id);
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		studentDAO.updateStudent(student);
	}



	/*
	 * @Override public List<Student> findByName(String name) { // TODO
	 * Auto-generated method stub return studentDAO.findByName(name); }
	 */

	
	  @Override public List<Student> search(String name, Integer min, Integer max)
	  { // TODO Auto-generated method stub 
		  return studentDAO.search(name, min, max); }
	 


}
