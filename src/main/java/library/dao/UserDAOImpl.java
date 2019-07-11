package library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import library.models.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserDAOImpl implements UserDAO {
	
	private final @NonNull SessionFactory sessionFactory;

	@Override
	public User getUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		User user = session.createQuery("from User u where u.username=:n", User.class).setParameter("n", username).getSingleResult();
		session.close();
		return user;
	}

}
