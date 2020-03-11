package com.example.mapper;

import java.util.List;
import java.util.Optional;

import com.example.dto.StudentDTO;
import com.example.entities.Student;

public interface StudentMapper {
	public List<Student> getAllStudents();
	// List<Student> findByName(String name);

	public int findCountStudents(StudentDTO student);
	
	
	List<Student> search(StudentDTO student);

	public void saveStudent(Student student);

	public void updateStudent(Student student);

	public void deleteStudent(Student student);

	Student findOne(int id);

	public Optional<Student> findStudentById(Long id);

	/*
	 * @Select("select * from student where name like '%${name}%'and (percentage >= ${min} and percentage <= ${max})"
	 * ) List<Student> search(@Param("name") String name, @Param("min") Integer
	 * min, @Param("max") Integer max);
	 */

}
