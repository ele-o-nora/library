package library.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import library.models.Author;
import library.models.Book;
import library.models.Country;
import library.models.Genre;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class BookDAOImpl implements BookDAO {
	
	private final @NonNull SessionFactory sessionFactory;

	@Override
	public List<Book> getAllBooksByGenre(Genre genre) {
		Session session = sessionFactory.openSession();
		List<Book> books = session.createQuery("select b from Book b join b.genres g where g=:genre order by b.title", Book.class).setParameter("genre", genre).getResultList();
		session.close();
		return books;
	}

	@Override
	public List<Book> getAllBooksByAuthor(Author author) {
		Session session = sessionFactory.openSession();
		List<Book> books = session.createQuery("select b from Book b join b.authors a where a=:author order by b.title", Book.class).setParameter("author", author).getResultList();
		session.close();
		return books;
	}

	@Override
	public List<Book> getAllBooksFromCountry(Country country) {
		Session session = sessionFactory.openSession();
		List<Book> authors = session.createQuery("select distinct b from Book b join b.authors a where a.country=:c order by b.title", Book.class).setParameter("c", country).getResultList();
		session.close();
		return authors;
	}

	@Override
	public List<Genre> getAllGenres() {
		Session session = sessionFactory.openSession();
		List<Genre> genres = session.createQuery("from Genre", Genre.class).getResultList();
		session.close();
		return genres;
	}

	@Override
	public List<Country> getAllCountries() {
		Session session = sessionFactory.openSession();
		List<Country> countries = session.createQuery("from Country", Country.class).getResultList();
		session.close();
		return countries;
	}

	@Override
	public List<Author> getAllAuthors() {
		Session session = sessionFactory.openSession();
		List<Author> authors = session.createQuery("from Author a order by a.lastName, a.firstName", Author.class).getResultList();
		session.close();
		return authors;
	}

	@Override
	public Author getAuthor(String firstName, String lastName) {
		Session session = sessionFactory.openSession();
		Author a = session.createQuery("from Author a where a.firstName=:f and a.lastName=:l", Author.class)
				.setParameter("f", firstName).setParameter("l", lastName).getSingleResult();
		session.close();
		return a;
	}

	@Override
	public Genre getGenre(String genre) {
		Session session = sessionFactory.openSession();
		Genre g = session.createQuery("from Genre g where g.name=:n", Genre.class).setParameter("n", genre).getSingleResult();
		session.close();
		return g;
	}

	@Override
	public Country getCountry(String country) {
		Session session = sessionFactory.openSession();
		Country c = session.createQuery("from Country c where c.name=:n", Country.class).setParameter("n", country).getSingleResult();
		session.close();
		return c;
	}

}
