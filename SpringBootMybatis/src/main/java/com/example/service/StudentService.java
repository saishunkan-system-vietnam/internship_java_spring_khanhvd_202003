package com.example.service;

import java.util.List;

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

}
