package telran.java2022.forum.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "dateCreated")
public class Comment {

	String user;
    String message;
    LocalDateTime dateCreated;
    int likes;
    
	public Comment(String user, String message) {
		this.user = user;
		this.message = message;
		dateCreated = LocalDateTime.now();
	}
   
}
