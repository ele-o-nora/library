package library.rest.services;

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
public class ClassroomRestServiceImpl implements ClassroomRestService {
	
	private final @NonNull ClassroomDAO classroomDAO;

	@Override
	public List<Classroom> getAllClassrooms() {
		return classroomDAO.getAllClassrooms();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Student> getAllStudents(String classroomName) {
		Classroom c = classroomDAO.getClassroom(classroomName);
		return classroomDAO.getAllStudents(c);
	}

}
