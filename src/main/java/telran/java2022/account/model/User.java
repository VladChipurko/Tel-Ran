package telran.java2022.account.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "login")
@Document(collection = "users")
public class User {

	@Id
	String login;
	@Setter
	String password;
	@Setter
    String firstName;
	@Setter
    String lastName;
	@Setter
    Set<String> roles = new HashSet<>();
	
	public User(String login, String password, String firstName, String lastName) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		roles.add("USER");
	}
	
	public User addRole(String role){
		roles.add(role.toUpperCase());
		return this;
	}
	
	public User deleteRole(String role){
		roles.remove(role.toUpperCase());
		return this;
	}
	
	
}
