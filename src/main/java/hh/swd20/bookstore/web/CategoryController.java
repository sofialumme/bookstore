package hh.swd20.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping("/categorylist")
	public String getCategories(Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
	}
	
	@RequestMapping("/categorylist/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	@PostMapping("/categorylist/save")
	public String save(Category category) {
		crepository.save(category);
		return "redirect:/categorylist";
	}
	
	@GetMapping("/categories")
	public @ResponseBody List<Category> categoryListRest() {
		return (List<Category>) crepository.findAll();
	}

	@GetMapping("/categories/{id}")
	public @ResponseBody Optional<Category> findCategoryByIdRest(@PathVariable("id") Long categoryId) {
		return crepository.findById(categoryId);
	}
	
}
