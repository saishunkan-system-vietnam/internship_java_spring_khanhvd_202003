package com.example.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.entities.Student;

public interface StudentMapper {
	public List<Student> getAllStudents();
	//List<Student> findByName(String name);
	@Select("select * from student where name like '%${name}%'and (percentage >= ${min} and percentage <= ${max})")
	List<Student> search(@Param("name") String name, @Param("min") Integer min, @Param("max") Integer max);
	public void saveStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(Student student);
	Student findOne(int id);
	/*
	 * @Select("select * from student where name like '%${name}%'and (percentage >= ${min} and percentage <= ${max})"
	 * ) List<Student> search(@Param("name") String name, @Param("min") Integer
	 * min, @Param("max") Integer max);
	 */

	public Optional<Student> findStudentById(Long id);
	
}
