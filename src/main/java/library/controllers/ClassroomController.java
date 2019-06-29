package library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import library.models.Classroom;
import library.models.Student;
import library.services.ClassroomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClassroomController {
	
	private final @NonNull ClassroomService classroomService;
	
	@GetMapping({"/", "/classrooms"})
	public String classroomList(Model model) {
		List<Classroom> classrooms = classroomService.getAllClassrooms();
		model.addAttribute("classrooms", classrooms);
		return "classrooms";
	}
	
	@GetMapping({"/classroom/{name}"})
	public String studentsList(@PathVariable("name") String name, Model model) {
		Classroom c = classroomService.getClassroom(name);
		List<Student> students = classroomService.getAllStudents(c);
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
	public String addStudent(@ModelAttribute("classroomName") String classroomName, 
			@ModelAttribute("firstName") String firstName, 
			@ModelAttribute("lastName") String lastName, 
			Model model) {
		Classroom c = classroomService.getClassroom(classroomName);
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setClassroom(c);
		classroomService.addStudent(student);
		return "redirect:/classroom/" + classroomName;
	}
}
