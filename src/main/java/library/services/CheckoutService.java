package library.services;

import org.springframework.ui.Model;

public interface CheckoutService {
	void checkOutBook(int studentId, int bookId);
	void returnBook(int studentId, int bookId, Model model);
	void listCheckedBooks(Model model, int studentId, int fine);
}
