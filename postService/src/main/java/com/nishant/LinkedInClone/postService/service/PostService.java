package com.nishant.LinkedInClone.postService.service;

import com.nishant.LinkedInClone.postService.dto.PostCreateRequestDto;
import com.nishant.LinkedInClone.postService.dto.PostDto;
import com.nishant.LinkedInClone.postService.entity.Post;
import com.nishant.LinkedInClone.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto,Long userId) {
        log.info("Creating post for userId : " + userId);
        Post post = modelMapper.map(postCreateRequestDto,Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
       return modelMapper.map(post,PostDto.class);
    }
}
