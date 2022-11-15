package telran.java2022.forum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.PostUpdateDto;
import telran.java2022.forum.service.ForumService;

@RestController
//@RequestMapping("/forum")
@RequiredArgsConstructor
public class ForumController {

	final ForumService forumService;

	@PostMapping("/forum/post/{author}")
	public PostDto addPost(@PathVariable String author, @RequestBody PostCreateDto postCreateDto) {
		return forumService.addPost(author, postCreateDto);
	}

	@GetMapping("/forum/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return forumService.findPostById(id);
	}

	@PutMapping("/forum/post/{id}/like")
	public void addLike(@PathVariable String id) {
		forumService.addLike(id);
	}

	@GetMapping("/forum/posts/author/{author}")
	public List<PostDto> findPostsByAuthor(@PathVariable String author){
		return forumService.findPostsByAuthor(author);
	}

	@PutMapping("/forum/post/{id}/comment/{user}")
	public PostDto addComment(@PathVariable String id, 
			@PathVariable String user, @RequestBody CommentDto commentDto) {
		return forumService.addComment(id, user, commentDto);
	}

	@DeleteMapping("/forum/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}

	@PostMapping("/forum/posts/tags")
	public List<PostDto> findPostsByTags(@RequestBody List<String> tags){
		return forumService.findPostsByTags(tags);
	}

	@PostMapping("/forum/posts/period")
	public List<PostDto> findPostsByPeriod(@RequestBody PeriodDto periodDto){
		return forumService.findPostsByPeriod(periodDto);
	}

	@PutMapping("/forum/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody PostUpdateDto postUpdateDto) {
		return forumService.updatePost(id, postUpdateDto);
	}
}
