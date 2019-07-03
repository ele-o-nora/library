package library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import library.dao.BookDAO;
import library.models.Author;
import library.models.Book;
import library.models.Country;
import library.models.Genre;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class BookServiceImpl implements BookService {
	
	private final @NonNull BookDAO bookDAO;
	
	@Override
	public void listSearchVariants(Model model, int studentId) {
		if (studentId > 0) {
			model.addAttribute("studentId", studentId);
		}
		List<Genre> genres = bookDAO.getAllGenres();
		List<Country> countries = bookDAO.getAllCountries();
		List<Author> authors = bookDAO.getAllAuthors();
		model.addAttribute("genres", genres);
		model.addAttribute("authors", authors);
		model.addAttribute("countries", countries);
	}

	@Override
	public void listAllBooksByGenre(Model model, String genre, int studentId) {
		if (studentId > 0) {
			model.addAttribute("studentId", studentId);
		}
		Genre g = bookDAO.getGenre(genre);
		List<Book> books = bookDAO.getAllBooksByGenre(g);
		model.addAttribute("genre", genre);
		model.addAttribute("books", books);
	}

	@Override
	public void listAllBooksByAuthor(Model model, String authorName, int studentId) {
		if (studentId > 0) {
			model.addAttribute("studentId", studentId);
		}
		String[] tokens = authorName.split(" ");
		Author a = bookDAO.getAuthor(tokens[0], tokens[1]);
		List<Book> books = bookDAO.getAllBooksByAuthor(a);
		model.addAttribute("author", authorName);
		model.addAttribute("books", books);
	}

	@Override
	public void listAllBooksFromCountry(Model model, String country, int studentId) {
		if (studentId > 0) {
			model.addAttribute("studentId", studentId);
		}
		Country c = bookDAO.getCountry(country);
		List<Book> books = bookDAO.getAllBooksFromCountry(c);
		model.addAttribute("country", country);
		model.addAttribute("books", books);
	}

}
