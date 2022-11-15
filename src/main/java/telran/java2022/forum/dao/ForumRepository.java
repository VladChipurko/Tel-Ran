package telran.java2022.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.forum.model.Post;

public interface ForumRepository extends CrudRepository<Post, String>{

	Stream<Post> findPostsByAuthor(String author);
	
	Stream<Post> findPostsByTagsIn(List<String> tags);
	
	Stream<Post> findPostsByDateCreatedBetween(LocalDate from, LocalDate to);
}
