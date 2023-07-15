package com.example.restapi.external.service;

import com.example.restapi.domain.repository.CommentRepository;
import com.example.restapi.domain.repository.PostRepository;
import com.example.restapi.external.model.Comment;
import com.example.restapi.external.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final int PAGE_SIZE = 20;

    public List<Post> getPosts(int page, Sort.Direction sort) {
//        return postRepository.findAll();
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }
    public Post getPost(Long id) {
//        return postRepository.findById(id).get();
        return postRepository.findById(id)
                .orElseThrow();
    }

    public List<Post> getPostWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = allPosts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, Long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setContent(post.getContent());
        return postEdited;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }


//    public Post editPost(Post post) {
//        return postRepository.save(post);
//    }

//    public Post editPost(Long id) {
//        Post post = postRepository.findById(id).get();
//        post.setTitle("jestemWEdit");
//        post.setCreated(LocalDateTime.now());
//        post.setContent("contentEdit");
//        return post;
//    }
}
