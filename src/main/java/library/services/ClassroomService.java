package library.services;

import java.util.List;

import library.models.Classroom;
import library.models.Student;

public interface ClassroomService {
	List<Classroom> getAllClassrooms();
	void addStudent(String firstName, String lastName, String classroomName);
	List<Student> getAllStudents(String classroomName);
}
