package library.services;

import org.springframework.ui.Model;

public interface ClassroomService {
	void getAllClassrooms(Model model);
	void addStudent(String firstName, String lastName, String classroomName);
	void getAllStudents(String classroomName, Model model);
}
