package library.dao;

import java.util.List;

import library.models.Book;
import library.models.Checkout;
import library.models.Student;

public interface CheckoutDAO {
	void addCheckedBook(Checkout checkout);
	void removeCheckedBook(Checkout checkout);
	Checkout getCheckedBook(Student student, Book book);
	List<Book> getAllCheckedBooks(Student student);
}
