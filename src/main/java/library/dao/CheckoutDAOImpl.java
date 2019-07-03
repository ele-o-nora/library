package library.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import library.models.Book;
import library.models.Checkout;
import library.models.Student;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class CheckoutDAOImpl implements CheckoutDAO {

	private final @NonNull SessionFactory sessionFactory;

	@Override
	public void addCheckedBook(Checkout checkout) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(checkout);
	}

	@Override
	public void removeCheckedBook(Checkout checkout) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(checkout);
	}

	@Override
	public Checkout getCheckedBook(Student student, Book book) {
		Session session = sessionFactory.getCurrentSession();
		Checkout checkout = session.createQuery("from Checkout ch where ch.student=:s and ch.book=:b", Checkout.class)
				.setParameter("s", student).setParameter("b", book).getSingleResult();
		return checkout;
	}

	@Override
	public List<Book> getAllCheckedBooks(Student student) {
		Session session = sessionFactory.getCurrentSession();
		List<Book> books = session.createQuery("select b from Book b join b.checkedList ch on ch.student=:s order by b.title", Book.class)
				.setParameter("s", student).getResultList();
		return books;
	}

}
