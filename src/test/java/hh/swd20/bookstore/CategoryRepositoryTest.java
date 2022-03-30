package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Romance");
		crepository.save(category);
		assertThat(category.getId()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		assertThat(crepository.findById((long) 1)).isNotEmpty();
		crepository.deleteById((long) 1);
		assertThat(crepository.findById((long) 1)).isEmpty();
	}

}
