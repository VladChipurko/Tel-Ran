package telran.java2022.account.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.account.dao.UserRepository;
import telran.java2022.account.dto.RolesChangeDto;
import telran.java2022.account.dto.UserDto;
import telran.java2022.account.dto.UserRegisterDto;
import telran.java2022.account.dto.UserUpdateDto;
import telran.java2022.account.dto.exceptions.UserExistException;
import telran.java2022.account.dto.exceptions.UserNotFoundException;
import telran.java2022.account.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	final UserRepository userRepository;
	final ModelMapper modelMapper;
	final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		User user = new User(userRegisterDto.getLogin(), 
				userRegisterDto.getPassword(), userRegisterDto.getFirstName(), 
				userRegisterDto.getLastName());
		if(userRepository.existsById(user.getLogin())) {
			throw new UserExistException(user.getLogin());
		}
		String password = passwordEncoder.encode(userRegisterDto.getPassword());
		user.setPassword(password);
		user = userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto deleteUser(String login) {
		User user = userRepository.findById(login)
				.orElseThrow(() -> new UserNotFoundException(login));
		userRepository.deleteById(login);;
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserUpdateDto userUpdateDto) {
		User user = userRepository.findById(login)
				.orElseThrow(() -> new UserNotFoundException(login));
		if (userUpdateDto.getFirstName() != null) {
			user.setFirstName(userUpdateDto.getFirstName());
		}
		if (userUpdateDto.getLastName() != null) {
			user.setLastName(userUpdateDto.getLastName());
		}
		user = userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public RolesChangeDto addRole(String login, String role) {
		User user = userRepository.findById(login)
				.orElseThrow(() -> new UserNotFoundException(login));
		user.addRole(role);
		user = userRepository.save(user);
		return modelMapper.map(user, RolesChangeDto.class);
	}

	@Override
	public RolesChangeDto deleteRole(String login, String role) {
		User user = userRepository.findById(login)
				.orElseThrow(() -> new UserNotFoundException(login));
		user.deleteRole(role);
		user = userRepository.save(user);
		return modelMapper.map(user, RolesChangeDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		User user = userRepository.findById(login)
				.orElseThrow(() -> new UserNotFoundException(login));
		String password = passwordEncoder.encode(newPassword);
		user.setPassword(password);
		user.setPasswordExpiration(LocalDate.now().plusDays(60));
		userRepository.save(user);
		
	}

	@Override
	public UserDto getUser(String login) {
		User user = userRepository.findById(login)
				.orElseThrow(() -> new UserNotFoundException(login));
		return modelMapper.map(user, UserDto.class);
	}

}
