package library.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.models.Classroom;
import library.models.Student;
import library.rest.services.ClassroomRestService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/classrooms")
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ClassroomRestController {
	
	private final @NonNull ClassroomRestService classroomService;
	
	@GetMapping("/")
	public List<Classroom> getAllClassrooms() {
		return classroomService.getAllClassrooms();
	}
	
	@GetMapping("/{name}/students")
	public List<Student> getAllStudents(@PathVariable("name") String classroomName) {
		return classroomService.getAllStudents(classroomName);
	}

}
