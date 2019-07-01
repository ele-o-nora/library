package library.services;

import java.util.List;

import library.models.Book;

public interface CheckoutService {
	void checkOutBook(int studentId, int bookId);
	int returnBook(int studentId, int bookId);
	List<Book> getAllCheckedBooks(int studentId);
}
