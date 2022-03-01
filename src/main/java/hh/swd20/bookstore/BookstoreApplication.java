package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save some categories");
			Category category1 = new Category("Literary Fiction");
			crepository.save(category1);
			Category category2 = new Category("Crime & Mystery");
			crepository.save(category2);
			Category category3 = new Category("Fantasy");
			crepository.save(category3);
			Category category4 = new Category("Comic");
			crepository.save(category4);
			
			log.info("save some books");
			brepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "12345123-1", 10.99, category1));
			brepository.save(new Book("The Name of the Rose", "Umberto Eco", 1980, "1212123-45", 12.99, category2));
			brepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "2345656-11", 13.99, category1));
			brepository.save(new Book("A Game of Thrones", "George R. R. Martin", 1996, "3216543-12", 12.99, category3));
			brepository.save(new Book("Watchmen", "Alan Moore", 1987, "1234567-12", 15.99, category4));
			
			log.info("fetch all categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
