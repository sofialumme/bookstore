package hh.swd20.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.swd20.bookstore.domain.Book;

@Controller
public class BookController {

	@GetMapping("/index")
	public String getBooks(Model model) {
		List<Book> books = new ArrayList<Book>();
		
		//test data
		books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "12345123-1", 10.99));
		books.add(new Book("The Name of the Rose", "Umberto Eco", 1980, "1212123-45", 12.99));
		
		model.addAttribute("books", books);
		return "booklist";
	}
}
