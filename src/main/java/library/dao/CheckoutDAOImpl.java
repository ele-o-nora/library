package library.dao;

import java.time.LocalDate;
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
	public Student getStudent(int id) {
		Session session = sessionFactory.openSession();
		Student student = session.get(Student.class, id);
		session.close();
		return student;
	}

	@Override
	public Book getBook(int id) {
		Session session = sessionFactory.openSession();
		Book book = session.get(Book.class, id);
		session.close();
		return book;
	}

	@Override
	public void addCheckedBook(int studentId, int bookId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Book book = session.get(Book.class, bookId);
		Student student = session.get(Student.class, studentId);
		Checkout checkout = new Checkout();
		checkout.setBook(book);
		checkout.setStudent(student);
		checkout.setCheckoutDate(LocalDate.now());
		session.persist(checkout);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void removeCheckedBook(Checkout checkout) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(checkout);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Checkout getCheckedBook(Student student, Book book) {
		Session session = sessionFactory.openSession();
		Checkout checkout = session.createQuery("from Checkout ch where ch.student=:s and ch.book=:b", Checkout.class)
				.setParameter("s", student).setParameter("b", book).getSingleResult();
		session.close();
		return checkout;
	}

	@Override
	public List<Book> getAllCheckedBooks(Student student) {
		Session session = sessionFactory.openSession();
		List<Book> books = session.createQuery("select b from Book b join b.checkedList ch on ch.student=:s", Book.class)
				.setParameter("s", student).getResultList();
		session.close();
		return books;
	}

	@Override
	public void updateBookAvailability(int bookId, int change) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Book book  = session.get(Book.class, bookId);
		book.setAvailable(book.getAvailable() + change);
		session.update(book);
		session.getTransaction().commit();
		session.close();
	}

}
