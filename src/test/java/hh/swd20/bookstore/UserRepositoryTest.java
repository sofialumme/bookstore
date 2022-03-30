package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void createNewUser() {
		User user = new User("testuser", "$2a$10$DHvFHEGEDAJ8I92tr4Pmwu4SPh2yW2q3IUFl2cVckwNBVpcdC8SYW", "testuser@example.com", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		assertThat(urepository.findById((long) 3)).isNotEmpty();
		urepository.deleteById((long) 3);
		assertThat(urepository.findById((long) 3)).isEmpty();
	}
	
	@Test
	public void findUserByUsername() {
		User user = urepository.findByUsername("admin");
		assertThat(user.getEmail()).isEqualTo("admin@example.com");
	}

}
