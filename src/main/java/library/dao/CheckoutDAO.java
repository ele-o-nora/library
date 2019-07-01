package library.dao;

import java.util.List;

import library.models.Book;
import library.models.Checkout;
import library.models.Student;

public interface CheckoutDAO {
	Student getStudent(int id);
	Book getBook(int id);
	void addCheckedBook(int studentId, int bookId);
	void removeCheckedBook(Checkout checkout);
	Checkout getCheckedBook(Student student, Book book);
	List<Book> getAllCheckedBooks(Student student);
	void updateBookAvailability(int bookId, int change);
}
