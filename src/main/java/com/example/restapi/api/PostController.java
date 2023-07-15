package com.example.restapi.api;

import com.example.restapi.external.model.Post;
import com.example.restapi.external.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    private final PostDtoMapper postDtoMapper;

    @GetMapping("/posts")
    public List<PostDto> getPost() {
//        throw new IllegalArgumentException("Not implemented yet");
//        return postService.getPosts();
        return postDtoMapper.mapToPostDtos(postService.getPosts());
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable Long id) {
        return postService.getPost(id);
//        throw new IllegalArgumentException("Not implemented yet " + id);
    }
}
