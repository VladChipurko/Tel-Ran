package telran.java2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.java2022.account.dao.UserRepository;
import telran.java2022.account.model.User;

@SpringBootApplication
public class ForumServiseSpringSecurityApplication implements CommandLineRunner{
	@Autowired
	UserRepository repository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ForumServiseSpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!repository.existsById("admin")) {
			String password = passwordEncoder.encode("admin");
			User user = new User("admin", password, "", "");
			user.addRole("ADMIN");
			user.addRole("MODERATOR");
			repository.save(user);
		}
		
	}

}
