package library.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import library.dao.BookDAO;
import library.dao.CheckoutDAO;
import library.dao.ClassroomDAO;
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
	private final @NonNull ClassroomDAO classroomDAO;

	@Override
	@Transactional
	public void checkOutBook(int studentId, int bookId) {
		Book book = bookDAO.getBook(bookId);
		Student student  = classroomDAO.getStudent(studentId);
		Checkout checkout = new Checkout();
		checkout.setBook(book);
		checkout.setStudent(student);
		checkout.setCheckoutDate(LocalDate.now());
		checkoutDAO.addCheckedBook(checkout);
		book.setAvailable(book.getAvailable() - 1);
		bookDAO.updateBook(book);
	}

	@Override
	@Transactional
	public void returnBook(int studentId, int bookId, Model model) {
		Student student = classroomDAO.getStudent(studentId);
		Book book = bookDAO.getBook(bookId);
		Checkout checkout = checkoutDAO.getCheckedBook(student, book);
		checkoutDAO.removeCheckedBook(checkout);
		book.setAvailable(book.getAvailable() + 1);
		bookDAO.updateBook(book);
		LocalDate today = LocalDate.now();
		LocalDate checkoutDate = checkout.getCheckoutDate();
		int fine = calculateFine(today, checkoutDate);
		if (fine > 0) {
			model.addAttribute("fine", fine);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public void listCheckedBooks(Model model, int studentId, int fine) {
		Student student = classroomDAO.getStudent(studentId);
		List<Book> books = checkoutDAO.getAllCheckedBooks(student);
		if (fine > 0) {
			model.addAttribute("fine", fine);
		}
		model.addAttribute("studentId", studentId);
		model.addAttribute("books", books);
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
