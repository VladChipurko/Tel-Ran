package telran.java2022.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
//@Document(collection = "posts")
public class Post {

	@Id
	String id;
	@Setter
    String title;
    String content;
    String author;
    LocalDateTime dateCreated;
    @Setter
    List<String> tags = new ArrayList<>();
    int likes;
    List<Comment> comments = new ArrayList<>();
    
	public Post(String title, String content, String author, List<String> tags) {
		this.title = title;
		this.content = content;
		this.author = author;
		dateCreated = LocalDateTime.now();
		if(tags != null) {
			this.tags = tags;
		}
	}
	
	public void addLike() {
		likes++;
	}
	
	public Post addComment(Comment comment) {
		comments.add(comment);
		return this;
	}
    
    
}
