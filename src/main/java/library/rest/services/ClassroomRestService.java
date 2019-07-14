package library.rest.services;

import java.util.List;

import library.models.Classroom;
import library.models.Student;

public interface ClassroomRestService {
	List<Classroom> getAllClassrooms();
	List<Student> getAllStudents(String classroomName);
}
