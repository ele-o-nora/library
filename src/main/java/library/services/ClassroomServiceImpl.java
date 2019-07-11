package library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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
	public void getAllClassrooms(Model model) {
		List<Classroom> classrooms =  classroomDAO.getAllClassrooms();
		model.addAttribute("classrooms", classrooms);
	}

	@Override
	@Transactional
	public void addStudent(String firstName, String lastName, String classroomName) {
		Classroom c = classroomDAO.getClassroom(classroomName);
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setClassroom(c);
		classroomDAO.addStudent(student);
	}

	@Override
	@Transactional(readOnly=true)
	public void getAllStudents(String classroomName, Model model) {
		Classroom c = classroomDAO.getClassroom(classroomName);
		List<Student> students = classroomDAO.getAllStudents(c);
		model.addAttribute("students", students);
		model.addAttribute("classroomName", classroomName);
	}

}
