package com.nishant.LinkedInClone.postService.controller;


import com.nishant.LinkedInClone.postService.dto.PostCreateRequestDto;
import com.nishant.LinkedInClone.postService.dto.PostDto;
import com.nishant.LinkedInClone.postService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto,
                                              HttpServletRequest httpServletRequest) {
        PostDto post = postService.createPost(postCreateRequestDto,1L);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

}
