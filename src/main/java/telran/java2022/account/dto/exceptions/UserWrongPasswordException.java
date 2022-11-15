package telran.java2022.account.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserWrongPasswordException extends RuntimeException{

	private static final long serialVersionUID = 7074516238745840109L;

	public UserWrongPasswordException() {
		super("Wrong password");
	}
}
