package library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import library.services.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class BookController {
	private final @NonNull BookService bookService;
	
	@GetMapping("/findbook")
	public String findBook(@RequestParam(value="studentId", required=false, defaultValue="0") int id, Model model) {
		bookService.listSearchVariants(model, id);
		return "findBook";
	}
	
	@GetMapping("/bygenre")
	public String byGenre(@RequestParam(value="studentId", required=false, defaultValue="0") int id, @ModelAttribute("genre") String genre, Model model) {
		bookService.listAllBooksByGenre(model, genre, id);
		return "books";
	}
	
	@GetMapping("/byauthor")
	public String byAuthor(@RequestParam(value="studentId", required=false, defaultValue="0") int id, @ModelAttribute("authorName") String authorName, Model model) {
		bookService.listAllBooksByAuthor(model, authorName, id);
		return "books";
	}
	
	@GetMapping("/bycountry")
	public String byCountry(@RequestParam(value="studentId", required=false, defaultValue="0") int id, @ModelAttribute("country") String country, Model model) {
		bookService.listAllBooksFromCountry(model, country, id);
		return "books";
	}
}
