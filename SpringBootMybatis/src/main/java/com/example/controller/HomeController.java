package com.example.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dto.StudentDTO;
//import com.example.entities.Product;
import com.example.entities.Student;
//import com.example.service.ProductService;
import com.example.service.StudentService;

@Controller
@RequestMapping(path = "/")
public class HomeController {
	@Autowired
	StudentService studentService;

	/*
	 * @GetMapping("/student") public String Default(ModelMap modelMap) {
	 * List<Student> students = studentService.getAllListStudent();
	 * modelMap.addAttribute("students", students);
	 * modelMap.addAttribute("studentDto", new StudentDTO()); return "index"; }
	 */

	@GetMapping("/")
	public String home() {
		return "redirect:/student";
	}

	@GetMapping("/student")
	public String index(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		request.getSession().setAttribute("students", null);
		List<Student> list = (List<Student>) studentService.getAllListStudent();
		return "redirect:/student/page/1";
	}

	@GetMapping("/student/page/{pageNumber}")
	public String showEmployeePage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<Student> pages = (PagedListHolder<Student>) request.getSession().getAttribute("studentlist");
		int pagesize = 5;
		List<Student> list = (List<Student>) studentService.getAllListStudent();
		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			pages.setSource(list);
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("studentlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/student/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("studentDto", new StudentDTO());
		model.addAttribute("students", pages);

		return "index";
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

	/*
	 * @GetMapping("/student/search") public String search(@Param("name") String
	 * name, @Param("min") Integer min, @Param("max") Integer max, ModelMap model) {
	 * List<Student> students = studentService.search(name, min,max);
	 * model.addAttribute("students", students); return "index"; }
	 */
	/*
	 * @PostMapping("/student/search") public String
	 * search(@ModelAttribute("studentDto") StudentDTO student, ModelMap model) {
	 * List<StudentDTO> students = studentService.search(student.getName(),
	 * student.getMin(), student.getMax()); model.addAttribute("students",
	 * students); model.addAttribute("studentDto", student); return "index"; }
	 */
	@GetMapping("/student/search/{pageNumber}")
	public String search(@ModelAttribute("studentDto") StudentDTO student, Model model, HttpServletRequest request,
			@PathVariable int pageNumber) {

		List<StudentDTO> list = studentService.search(student.getName(), student.getMin(), student.getMax());
		model.addAttribute("students", list);
		model.addAttribute("studentDto", new StudentDTO());
		if (list == null) {
			return "redirect:/student";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("studentlist");
		int pagesize = 5;
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("studentlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/student/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);

		model.addAttribute("students", pages);

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
