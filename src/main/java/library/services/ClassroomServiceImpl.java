package library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public List<Classroom> getAllClassrooms() {
		return classroomDAO.getAllClassrooms();
	}

	@Override
	@Transactional
	public Classroom getClassroom(String name) {
		return classroomDAO.getClassroom(name);
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		classroomDAO.addStudent(student);
	}

	@Override
	@Transactional
	public List<Student> getAllStudents(Classroom classroom) {
		return classroomDAO.getAllStudents(classroom);
	}

}
