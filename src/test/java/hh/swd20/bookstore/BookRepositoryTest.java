package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository brepository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Seitsemän veljestä", "Aleksis Kivi", 1870, "1234567-98", 14.99, null);
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		assertThat(brepository.findById((long) 6)).isNotEmpty();
		brepository.deleteById((long) 6);
		assertThat(brepository.findById((long) 6)).isEmpty();
	}
	
	@Test
	public void findBookByYear() {
		List<Book> books = brepository.findByYear(1996);
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("A Game of Thrones");
	}

}
