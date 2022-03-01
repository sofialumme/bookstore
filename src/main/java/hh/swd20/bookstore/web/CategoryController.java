package hh.swd20.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@GetMapping("/categorylist")
	public String getCategories(Model model) {
		model.addAttribute("categories", repository.findAll());
		return "categorylist";
	}
	
	@RequestMapping("/categorylist/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	@PostMapping("/categorylist/save")
	public String save(Category category) {
		repository.save(category);
		return "redirect:/categorylist";
	}
	
}
