package telran.java2022.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.PostUpdateDto;
import telran.java2022.forum.dto.exceptions.PostNotFoundException;
import telran.java2022.forum.model.Comment;
import telran.java2022.forum.model.Post;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {
	
	final ForumRepository forumRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String author, PostCreateDto postCreateDto) {
		Post post = new Post(postCreateDto.getTitle(), 
				postCreateDto.getContent(), author, postCreateDto.getTags());
		forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = forumRepository.findById(id).orElseThrow(()->new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.addLike();
		forumRepository.save(post);

	}

	@Override
	public PostDto addComment(String id, String user, CommentDto commentDto) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = new Comment(user, commentDto.getMessage());
		post.addComment(comment);
		forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		forumRepository.deleteById(id);
		return modelMapper.map(post, PostDto.class);
	}
	
	@Override
	public PostDto updatePost(String id, PostUpdateDto postUpdateDto) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.setTitle(postUpdateDto.getTitle());
		List<String> newTags = post.getTags();
		newTags.addAll(postUpdateDto.getTags());
		post.setTags(newTags);
		forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return forumRepository.findPostsByTagsIn(tags)
				.map(p->modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto periodDto) {
		return forumRepository.findPostsByDateCreatedBetween(periodDto.getDateFrom(), periodDto.getDateTo())
				.map(p->modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		return forumRepository.findPostsByAuthor(author)
				.map(p->modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

}
