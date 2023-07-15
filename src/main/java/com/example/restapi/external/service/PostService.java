package com.example.restapi.external.service;

import com.example.restapi.domain.repository.PostRepository;
import com.example.restapi.external.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    public List<Post> getPosts() {
//        return postRepository.findAll();
        return postRepository.findAllPosts(PageRequest.of(0, 5));//wyswietla ilosc tylko 5
    }
    public Post getPost(Long id) {
//        return postRepository.findById(id).get();
        return postRepository.findById(id)
                .orElseThrow();
    }
}
