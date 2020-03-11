package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dto.StudentDTO;
//import com.example.entities.Product;
import com.example.entities.Student;
//import com.example.service.ProductService;
import com.example.service.StudentService;

@Controller
@RequestMapping(path = "/")
@SessionAttributes(types = {StudentDTO.class})
public class HomeController {
	@Autowired
	StudentService studentService;

	@GetMapping("/")
	public String home() {
		return "redirect:/student";
	}

	@ModelAttribute("studentDto")
	public StudentDTO setStudentDTO() {
		return new StudentDTO();
	}

	@GetMapping("/student")
	public String index(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		request.getSession().setAttribute("students", null);
		return "redirect:/student/search";
	}

	@GetMapping("/student/search")
	public String search(@ModelAttribute("studentDto") StudentDTO student, ModelMap model, HttpServletRequest request) {

		student.setPageSize(5);

		int totalStudent = studentService.findCountStudents(student);

		List<Student> students = studentService.search(student);

		PagedListHolder<Student> pages = new PagedListHolder<>(students);

		pages.setPageSize(student.getPageSize());

		final int goToPage = student.getPage() - 1;

		if (goToPage >= 0) {
			pages.setPage(goToPage);
		}

		int current = goToPage + 1;
		int begin =1;
		int end = (int) Math.floor(totalStudent / student.getPageSize()) + 1;
		int totalPageCount = (totalStudent/student.getPageSize())+1;

		String baseUrl = "/student/search?page=";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("students", pages);
		return "index";
	}

	@PostMapping("/student/search")
	public String search(@ModelAttribute("studentDto") StudentDTO student) {
		return "redirect:/student/search";
	}
	
	@GetMapping(value = "/addStudent/create")
	public String addStudent(ModelMap model) {
		model.addAttribute("student", new Student());
		return "addStudent";
	}

	@PostMapping("/student/save")
	public String saveStudent(@Valid Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "addStudent";
		}
		studentService.saveStudent(student);
		return "redirect:/student";
	}

	@GetMapping("/student/{id}/edit")
	public String edit(@PathVariable int id, ModelMap model) {
		model.addAttribute("student", studentService.findOne(id));
		return "editStudent";
	}

	@PostMapping("/student/{id}/edit")
	public String updateStudent(@Valid Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "editStudent";
		}
		studentService.updateStudent(student);
		return "redirect:/student";
	}

	@GetMapping("/student/{id}/delete")
	public String deleteStudent(@PathVariable int id, RedirectAttributes redirect) {
		Student student = studentService.findOne(id);
		studentService.deleteStudent(student);
		redirect.addFlashAttribute("success");
		return "redirect:/student";
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
