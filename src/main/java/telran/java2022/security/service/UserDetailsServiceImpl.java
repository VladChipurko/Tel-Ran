package telran.java2022.security.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.account.dao.UserRepository;
import telran.java2022.account.dto.exceptions.UserNotFoundException;
import telran.java2022.account.model.User;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findById(username)
				.orElseThrow(() -> new UserNotFoundException(username));
		String[] roles = user.getRoles().stream()
				.map(r -> "ROLE_" + r.toUpperCase())
				.toArray(String[]::new);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), 
				AuthorityUtils.createAuthorityList(roles));
	}

}
