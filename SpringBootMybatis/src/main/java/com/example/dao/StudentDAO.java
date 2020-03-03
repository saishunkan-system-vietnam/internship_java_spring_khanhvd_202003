package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.daoimp.StudentImp;
import com.example.entities.Student;
import com.example.mapper.StudentMapper;

@Repository
public class StudentDAO implements StudentImp{

	@Autowired
	StudentMapper studentMapper;
	
	@Override
	public List<Student> getAllListStudent() {
		// TODO Auto-generated method stub
		List<Student> students = studentMapper.getAllStudents();
		return students;
	}

}
