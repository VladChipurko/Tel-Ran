package telran.java2022.forum.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5958056815402737926L;

	public PostNotFoundException(String id) {
		super("Post with id " + id + " not found");
	}
}
