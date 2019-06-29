package library.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.models.Classroom;
import library.models.Student;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ClassroomDAOImpl implements ClassroomDAO {
	
	private final @NonNull SessionFactory sessionFactory;
	
	@Override
	public List<Classroom> getAllClassrooms() {
		return sessionFactory.getCurrentSession().createQuery("from Classroom c order by c.id", Classroom.class).getResultList();
	}
	
	@Override
	public Classroom getClassroom(String name) {
		return sessionFactory.getCurrentSession().createQuery("from Classroom c where c.name=:n", Classroom.class)
				.setParameter("n", name).getSingleResult();
	}

	@Override
	public void addStudent(Student student) {
		sessionFactory.getCurrentSession().persist(student);
	}

	@Override
	public List<Student> getAllStudents(Classroom classroom) {
		return sessionFactory.getCurrentSession().createQuery("from Student s where s.classroom =:c", Student.class)
				.setParameter("c", classroom).getResultList();
	}

}
