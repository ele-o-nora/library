package library.services;

import org.springframework.ui.Model;

public interface BookService {
	void listAllBooksByGenre(Model model, String genre, int studentId);
	void listAllBooksByAuthor(Model model, String authorName, int studentId);
	void listAllBooksFromCountry(Model model, String country, int studentId);
	void listSearchVariants(Model model, int studentId);
}
