package library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import library.models.Classroom;
import library.models.Student;
import library.services.ClassroomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClassroomController {
	
	private final @NonNull ClassroomService classroomService;
	
	@GetMapping("/classrooms")
	public String classroomList(Model model) {
		List<Classroom> classrooms = classroomService.getAllClassrooms();
		model.addAttribute("classrooms", classrooms);
		return "classrooms";
	}
	
	@GetMapping("/classroom/{name}")
	public String studentsList(@PathVariable("name") String name, Model model) {
		List<Student> students = classroomService.getAllStudents(name);
		model.addAttribute("students", students);
		model.addAttribute("classroomName", name);
		return "classroom";
	}
	
	@GetMapping("/{name}/add")
	public String addStudent(@PathVariable("name") String name, Model model) {
		model.addAttribute("classroomName", name);
		return "add";
	}
	
	@PostMapping("/add")
	public ModelAndView addStudent(@ModelAttribute("classroomName") String classroomName, 
			@ModelAttribute("firstName") String firstName, 
			@ModelAttribute("lastName") String lastName, 
			Model model) {
		classroomService.addStudent(firstName, lastName, classroomName);
		RedirectView redirectView = new RedirectView("/classroom/".concat(classroomName));
		redirectView.setExposeModelAttributes(false);
		return new ModelAndView(redirectView);
	}
}
