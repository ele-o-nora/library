package library.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import library.models.Author;
import library.models.Book;
import library.models.Country;
import library.models.Genre;
import library.services.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class BookController {
	private final @NonNull BookService bookService;
	
	@GetMapping("/findbook")
	public String findBook(Model model) {
		List<Genre> genres = bookService.getAllGenres();
		List<Author> authors = bookService.getAllAuthors();
		List<Country> countries = bookService.getAllCountries();
		model.addAttribute("genres", genres);
		model.addAttribute("authors", authors);
		model.addAttribute("countries", countries);
		return "findBook";
	}
	
	@GetMapping("/bygenre")
	public String byGenre(@ModelAttribute("genre") String genre, Model model) {
		List<Book> books = bookService.getAllBooksByGenre(genre);
		model.addAttribute("genre", genre);
		model.addAttribute("books", books);
		return "books";
	}
	
	@GetMapping("/byauthor")
	public String byAuthor(@ModelAttribute("authorName") String authorName, Model model) {
		List<Book> books = bookService.getAllBooksByAuthor(authorName);
		model.addAttribute("author", authorName);
		model.addAttribute("books", books);
		return "books";
	}
	
	@GetMapping("/bycountry")
	public String byCountry(@ModelAttribute("country") String country, Model model) {
		Set<Book> books = bookService.getAllBooksFromCountry(country);
		model.addAttribute("country", country);
		model.addAttribute("books", books);
		return "books";
	}
}
