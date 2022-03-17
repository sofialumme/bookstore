package hh.swd20.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository brepository;

	@Autowired
	private CategoryRepository crepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping("/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}

	@RequestMapping("/booklist/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@PostMapping("/booklist/save")
	public String save(Book book) {
		brepository.save(book);
		return "redirect:/booklist";
	}

	@GetMapping("/booklist/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId) {
		brepository.deleteById(bookId);
		return "redirect:/booklist";
	}

	@RequestMapping("/booklist/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", brepository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}

	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}

	@GetMapping("/books/{id}")
	public @ResponseBody Optional<Book> findBookByIdRest(@PathVariable("id") Long bookId) {
		return brepository.findById(bookId);
	}
}
