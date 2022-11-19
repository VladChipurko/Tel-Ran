package telran.java2022.security.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.account.dao.UserRepository;
import telran.java2022.account.model.User;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.model.Post;

@Service("customSecurity")
@RequiredArgsConstructor
public class CustomWebSecurity {
	
	final ForumRepository forumRepository;
	final UserRepository userRepository;
	
	public boolean checkPostAuthor(String postId, String userName) {
		Post post = forumRepository.findById(postId).orElse(null);
		return post != null && userName.equalsIgnoreCase(post.getAuthor());
	}
	
	public boolean checkPasswordExpiration(String userName) {
		User user = userRepository.findById(userName).orElse(null);
		return user != null && user.getPasswordExpiration().isAfter(LocalDate.now());
	}

}
