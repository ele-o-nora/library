package library.dao;

import java.util.List;

import library.models.Classroom;
import library.models.Student;

public interface ClassroomDAO {
	List<Classroom> getAllClassrooms();
	Classroom getClassroom(String name);
	void addStudent(Student student);
	List<Student> getAllStudents(Classroom classroom);
	Student getStudent(int id);
}
