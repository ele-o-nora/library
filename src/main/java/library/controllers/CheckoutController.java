package library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import library.services.CheckoutService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class CheckoutController {
	
	private final @NonNull CheckoutService checkoutService;
	
	@RequestMapping("/books/{id}")
	public String listCheckedBooks(@RequestParam(value="fine", required=false, defaultValue="0") int fine, @PathVariable("id") int id, Model model) {
		checkoutService.listCheckedBooks(model, id, fine);
		return "books";
	}
	
	@PostMapping("/returnbook")
	public String returnBook(@ModelAttribute("studentId") int studentId, @ModelAttribute("bookId") int bookId, Model model) {
		checkoutService.returnBook(studentId, bookId, model);
		return "redirect:/books/" + studentId;
	}
	
	@PostMapping("/checkoutbook")
	public String checkoutBook(@ModelAttribute("studentId") int studentId, @ModelAttribute("bookId") int bookId, Model model) {
		checkoutService.checkOutBook(studentId, bookId);
		return "redirect:/books/" + studentId;
	}
	
}
