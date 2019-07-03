package library.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import library.models.Classroom;
import library.models.Student;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ClassroomDAOImpl implements ClassroomDAO {
	
	private final @NonNull SessionFactory sessionFactory;
	
	@Override
	public Student getStudent(int id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		return student;
	}
	
	@Override
	public List<Classroom> getAllClassrooms() {
		Session session = sessionFactory.getCurrentSession();
		List<Classroom> list = session.createQuery("from Classroom c order by c.id", Classroom.class).getResultList();
		return list;
	}
	
	@Override
	public Classroom getClassroom(String name) {
		Session session = sessionFactory.getCurrentSession();
		Classroom c = session.createQuery("from Classroom c where c.name=:n", Classroom.class)
				.setParameter("n", name).getSingleResult();
		return c;
	}

	@Override
	public void addStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(student);
	}

	@Override
	public List<Student> getAllStudents(Classroom classroom) {
		Session session = sessionFactory.getCurrentSession();
		List<Student> list =  session.createQuery("from Student s where s.classroom =:c", Student.class)
				.setParameter("c", classroom).getResultList();
		return list;
	}

}
