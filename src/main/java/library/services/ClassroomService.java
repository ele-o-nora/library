package library.services;

import java.util.List;

import library.models.Classroom;
import library.models.Student;

public interface ClassroomService {
	List<Classroom> getAllClassrooms();
	Classroom getClassroom(String name);
	void addStudent(Student student);
	List<Student> getAllStudents(Classroom classroom);
}
