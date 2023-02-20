package com.marklog.blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marklog.blog.domain.post.Post;
import com.marklog.blog.domain.post.PostRepository;
import com.marklog.blog.domain.postlike.PostLike;
import com.marklog.blog.domain.postlike.PostLikeRepository;
import com.marklog.blog.domain.user.Role;
import com.marklog.blog.domain.user.User;
import com.marklog.blog.domain.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PostLikeServiceTest {
	@Mock
	UserRepository userRepository;

	@Mock
	PostRepository postRepository;

	@Mock
	PostLikeRepository postLikeRepository;



	User user;
	Long userId = 1L;
	String name = "name";
	String email = "test@gmail.com";
	String picture = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/How_to_use_icon.svg/40px-How_to_use_icon.svg.png";
	String userTitle = "myblog";
	String introduce = "introduce";

	Post post;
	Long postId = 2L;
	String title = "title";
	String content = "title";

	PostLikeService postLikeService;

	@BeforeEach
	public void setUp() {
		user = new User(name, email, picture, title, introduce, Role.USER);
		post = spy(new Post(title, content, user, null));
		postLikeService = new PostLikeService(postRepository, userRepository, postLikeRepository);
	}

	@Test
	public void testPostLikeSave() {
		// given
		when(postRepository.getReferenceById(postId)).thenReturn(post);
		when(userRepository.getReferenceById(userId)).thenReturn(user);

		// when
		postLikeService.save(postId, userId);

		// then
		verify(postLikeRepository).save(any());
	}


	@Test
	public void testPostLikeFindById() {
		// given
		PostLike postLike = new PostLike(post, user);
		Optional<PostLike> optionalPostLike = Optional.of(postLike);
		when(postLikeRepository.findById(any())).thenReturn(optionalPostLike);

		// when
		Boolean like = postLikeService.findById(postId, userId);

		// then
		assertThat(like).isTrue();
	}


	@Test
	public void testPostLikeDelete() {
		// given
		PostLike postLike = new PostLike(post, user);
		Optional<PostLike> optionalPostLike = Optional.of(postLike);
		when(postLikeRepository.findById(any())).thenReturn(optionalPostLike);

		// when
		postLikeService.delete(postId, userId);

		// then

	}

}