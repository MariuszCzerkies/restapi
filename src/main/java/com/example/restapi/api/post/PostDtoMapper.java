package com.example.restapi.api.post;

import com.example.restapi.external.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {
    public static List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
//                .map(post -> mapToPostDto(post))
//                .map(this::mapToPostDto)
//                .map(post -> mapToPostDto(post))
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    private static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
