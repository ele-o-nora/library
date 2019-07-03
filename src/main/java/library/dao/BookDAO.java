package library.dao;

import java.util.List;

import library.models.Author;
import library.models.Book;
import library.models.Country;
import library.models.Genre;

public interface BookDAO {
	List<Book> getAllBooksByGenre(Genre genre);
	List<Book> getAllBooksByAuthor(Author author);
	List<Book> getAllBooksFromCountry(Country country);
	List<Genre> getAllGenres();
	List<Country> getAllCountries();
	List<Author> getAllAuthors();
	Author getAuthor(String firstName, String lastName);
	Genre getGenre(String genre);
	Country getCountry(String country);
	void updateBook(Book book);
	Book getBook(int id);
}
