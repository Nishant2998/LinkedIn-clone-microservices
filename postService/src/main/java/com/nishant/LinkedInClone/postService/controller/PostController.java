package com.nishant.LinkedInClone.postService.controller;


import com.nishant.LinkedInClone.postService.dto.PostCreateRequestDto;
import com.nishant.LinkedInClone.postService.dto.PostDto;
import com.nishant.LinkedInClone.postService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostDto post = postService.createPost(postCreateRequestDto,1L);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.FOUND);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId) {
        List<PostDto> postsList = postService.getAllPostOfUser(userId);
        return new ResponseEntity<>(postsList,HttpStatus.FOUND);
    }

}
