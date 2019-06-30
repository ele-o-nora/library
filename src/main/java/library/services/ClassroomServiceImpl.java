package library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.ClassroomDAO;
import library.models.Classroom;
import library.models.Student;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ClassroomServiceImpl implements ClassroomService {
	
	private final @NonNull ClassroomDAO classroomDAO;
	
	@Override
	public List<Classroom> getAllClassrooms() {
		return classroomDAO.getAllClassrooms();
	}

	@Override
	public void addStudent(String firstName, String lastName, String classroomName) {
		Classroom c = classroomDAO.getClassroom(classroomName);
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setClassroom(c);
		classroomDAO.addStudent(student);
	}

	@Override
	public List<Student> getAllStudents(String classroomName) {
		Classroom c = classroomDAO.getClassroom(classroomName);
		return classroomDAO.getAllStudents(c);
	}

}
