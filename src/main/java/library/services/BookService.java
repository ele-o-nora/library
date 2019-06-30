package library.services;

import java.util.List;
import java.util.Set;

import library.models.Author;
import library.models.Book;
import library.models.Country;
import library.models.Genre;

public interface BookService {
	List<Book> getAllBooksByGenre(String genre);
	List<Book> getAllBooksByAuthor(String authorName);
	Set<Book> getAllBooksFromCountry(String country);
	List<Genre> getAllGenres();
	List<Country> getAllCountries();
	List<Author> getAllAuthors();
}
