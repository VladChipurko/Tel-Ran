package telran.java2022.account.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.account.dto.RolesChangeDto;
import telran.java2022.account.dto.UserDto;
import telran.java2022.account.dto.UserRegisterDto;
import telran.java2022.account.dto.UserUpdateDto;
import telran.java2022.account.service.UserService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
	final UserService userService;
	
	@PostMapping("/register")
	public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return userService.register(userRegisterDto);
	}
	
	@PostMapping("/login")
	public UserDto login(Principal principal) {
		return userService.getUser(principal.getName());
	}
	
	@DeleteMapping("/user/{login}")
	public UserDto deleteUser(@PathVariable String login) {
		return userService.deleteUser(login);
	}
	
	@PutMapping("/user/{login}")
	public UserDto updateUser(@PathVariable String login, @RequestBody UserUpdateDto userUpdateDto) {
		return userService.updateUser(login, userUpdateDto);
	}
	
	@PutMapping("/user/{login}/role/{role}")
	public RolesChangeDto addRole(@PathVariable String login, @PathVariable String role) {
		return userService.addRole(login, role);
	}
	
	@DeleteMapping("/user/{login}/role/{role}")
	public RolesChangeDto deleteRole(@PathVariable String login, @PathVariable String role) {
		return userService.deleteRole(login, role);
	}
	
	@PutMapping("/password")
	public void changePassword (Principal principal, @RequestHeader("X-Password") String newPassword) {
		userService.changePassword(principal.getName(), newPassword);
	}

}
