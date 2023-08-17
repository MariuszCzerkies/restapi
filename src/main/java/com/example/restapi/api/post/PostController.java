package com.example.restapi.api.post;

import com.example.restapi.external.model.Post;
import com.example.restapi.external.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
//    private final PostDtoMapper postDtoMapper;

    @GetMapping("/posts")
    public List<PostDto> getPost(@RequestParam(required = false) Integer page, Sort.Direction sort, @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        int pageNumer = page >= 0 && page != null ? page : 0;//zabezpieczenie przed ujemnymi stronami
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumer, sortDirection));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComment(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumer = page >= 0 && page != null ? page : 0;//zabezpieczenie przed ujemnymi stronami
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return postService.getPostWithComments(pageNumer, sortDirection);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable Long id) {
        return postService.getSinglePost(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

//    @PutMapping("/post")
//    public Post editPost(@RequestParam Long id) {
//        return postService.editPost(id);
//    }

    @PutMapping("/post")
    public Post editPost(@RequestBody Post post) {
        return postService.editPost(post);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}
