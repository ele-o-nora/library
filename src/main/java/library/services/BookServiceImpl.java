package library.services;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.BookDAO;
import library.models.Author;
import library.models.Book;
import library.models.Country;
import library.models.Genre;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class BookServiceImpl implements BookService {
	
	private final @NonNull BookDAO bookDAO;

	@Override
	public List<Book> getAllBooksByGenre(String genre) {
		Genre g = bookDAO.getGenre(genre);
		return bookDAO.getAllBooksByGenre(g);
	}

	@Override
	public List<Book> getAllBooksByAuthor(String authorName) {
		String[] tokens = authorName.split(" ");
		Author a = bookDAO.getAuthor(tokens[0], tokens[1]);
		return bookDAO.getAllBooksByAuthor(a);
	}

	@Override
	public Set<Book> getAllBooksFromCountry(String country) {
		Country c = bookDAO.getCountry(country);
		List<Author> authors = bookDAO.getAllAuthorsFromCountry(c);
		Set<Book> books = new TreeSet<Book>();
		for (Author a: authors) {
			books.addAll(bookDAO.getAllBooksByAuthor(a));
		}
		return books;
	}

	@Override
	public List<Genre> getAllGenres() {
		return bookDAO.getAllGenres();
	}

	@Override
	public List<Country> getAllCountries() {
		return bookDAO.getAllCountries();
	}

	@Override
	public List<Author> getAllAuthors() {
		return bookDAO.getAllAuthors();
	}

}
