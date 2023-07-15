package com.example.restapi.api;

import com.example.restapi.external.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoMapper {
    public List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
//                .map(post -> mapToPostDto(post))
                .map(this::mapToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
