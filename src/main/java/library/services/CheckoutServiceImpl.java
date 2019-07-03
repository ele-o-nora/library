package library.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.BookDAO;
import library.dao.CheckoutDAO;
import library.models.Book;
import library.models.Checkout;
import library.models.Student;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckoutServiceImpl implements CheckoutService {

	private final @NonNull CheckoutDAO checkoutDAO;
	private final @NonNull BookDAO bookDAO;

	@Override
	public void checkOutBook(int studentId, int bookId) {
		checkoutDAO.addCheckedBook(studentId, bookId);
		bookDAO.updateBookAvailability(bookId, -1);
	}

	@Override
	public int returnBook(int studentId, int bookId) {
		Student student = checkoutDAO.getStudent(studentId);
		Book book = checkoutDAO.getBook(bookId);
		Checkout checkout = checkoutDAO.getCheckedBook(student, book);
		checkoutDAO.removeCheckedBook(checkout);
		bookDAO.updateBookAvailability(bookId, 1);
		LocalDate today = LocalDate.now();
		LocalDate checkoutDate = checkout.getCheckoutDate();
		return calculateFine(today, checkoutDate);
	}

	@Override
	public List<Book> getAllCheckedBooks(int studentId) {
		Student student = checkoutDAO.getStudent(studentId);
		List<Book> books = checkoutDAO.getAllCheckedBooks(student);
		return books;
	}

	private int calculateFine(LocalDate returnDate, LocalDate checkoutDate) {
		boolean checkoutSepDec = checkoutDate.getMonthValue() >= 9;
		boolean returnSepDec = returnDate.getMonthValue() >= 9;
		boolean sameYear = returnDate.getYear() == checkoutDate.getYear();
		boolean nextYear = returnDate.getYear() == checkoutDate.getYear() + 1;

		if ((checkoutSepDec && returnSepDec && sameYear) || (checkoutSepDec && !returnSepDec && nextYear)
				|| (!checkoutSepDec && !returnSepDec && sameYear)) {
			return 0;
		}
		LocalDate startFine;
		if (checkoutDate.getMonthValue() >= 9) {
			startFine = LocalDate.of(checkoutDate.getYear() + 1, 9, 1);
		} else {
			startFine = LocalDate.of(checkoutDate.getYear(), 9, 1);
		}
		int days = (int)startFine.until(returnDate, ChronoUnit.DAYS);
		return days * 10;
	}

}
