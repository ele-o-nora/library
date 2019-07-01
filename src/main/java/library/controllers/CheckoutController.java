package library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import library.models.Book;
import library.services.CheckoutService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class CheckoutController {
	
	private final @NonNull CheckoutService checkoutService;
	
	@RequestMapping("/books/{id}")
	public String listCheckedBooks(@RequestParam(value="fine", required=false, defaultValue="0") int fine, @PathVariable("id") int id, Model model) {
		List<Book> books = checkoutService.getAllCheckedBooks(id);
		if (fine > 0) {
			model.addAttribute("fine", fine);
		}
		model.addAttribute("studentId", id);
		model.addAttribute("books", books);
		return "books";
	}
	
	@PostMapping("/returnbook")
	public String returnBook(@ModelAttribute("studentId") int studentId, @ModelAttribute("bookId") int bookId, Model model) {
		int fine = checkoutService.returnBook(studentId, bookId);
		if (fine > 0) {
			model.addAttribute("fine", fine);
		}
		return "redirect:/books/" + studentId;
	}
	
	@PostMapping("/checkoutbook")
	public String checkoutBook(@ModelAttribute("studentId") int studentId, @ModelAttribute("bookId") int bookId, Model model) {
		checkoutService.checkOutBook(studentId, bookId);
		return "redirect:/books/" + studentId;
	}
	
}
