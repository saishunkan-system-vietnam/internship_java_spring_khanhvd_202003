package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.example.entities.Product;
import com.example.entities.Student;
//import com.example.service.ProductService;
import com.example.service.StudentService;

@Controller
@RequestMapping(path = "/")
public class HomeController {
	@Autowired
	StudentService studentService;

	@GetMapping("/student")
	public String Default(ModelMap modelMap) {
		List<Student> students = studentService.getAllListStudent();
		modelMap.addAttribute("students", students);
		return "index";
	}

	@GetMapping(value = "/addStudent/create")
	public String addStudent(ModelMap model) {
		model.addAttribute("student", new Student());
		return "addStudent";
	}

	@PostMapping("/student/save")
	public String saveStudent(@Valid Student student, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "addStudent";
		}
		studentService.saveStudent(student);
		redirect.addFlashAttribute("success");
		return "redirect:/student";
	}

	@GetMapping("/student/{id}/edit")
	public String edit(@PathVariable int id, ModelMap model) {
		model.addAttribute("student", studentService.findOne(id));
		return "editStudent";
	}

	@PostMapping("/student/{id}/edit")
	public String updateStudent(@Valid Student student, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "editStudent";
		}

		studentService.updateStudent(student);
		redirect.addFlashAttribute("success");
		return "redirect:/student";
	}

	@GetMapping("/student/{id}/delete")
	public String deleteStudent(@PathVariable int id, RedirectAttributes redirect) {
		Student student = studentService.findOne(id);
		studentService.deleteStudent(student);
		redirect.addFlashAttribute("success");
		return "redirect:/student";
	}

	@GetMapping("/student/search")
	public String search(@RequestParam("name") String s, @Param("min") Integer min, @Param("max") Integer max,
			ModelMap model) {
		if (s.equals("")) {
			return "redirect:/student";
		}
		model.addAttribute("students", studentService.search(s, min, max));
		return "index";
	}

	@GetMapping("/student/{id}/info")
	public String info(@PathVariable int id, ModelMap model) {
		Student student = studentService.findOne(id);
		model.addAttribute("student", student);
		return "info";
	}
	@PostMapping("/student/{id}/info")
	public String info(@Valid Student student) {
		return "redirect:/student";
	}

}
