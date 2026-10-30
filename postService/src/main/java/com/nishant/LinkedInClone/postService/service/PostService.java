package com.nishant.LinkedInClone.postService.service;

import com.nishant.LinkedInClone.postService.dto.PostCreateRequestDto;
import com.nishant.LinkedInClone.postService.dto.PostDto;
import com.nishant.LinkedInClone.postService.entity.Post;
import com.nishant.LinkedInClone.postService.exception.ResourceNotFoundException;
import com.nishant.LinkedInClone.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PostDto getPostById(Long PostId) {
        log.info("Fetching post by Id : " + PostId);
        Post post = postRepository.findById(PostId).orElseThrow(() -> new ResourceNotFoundException("Post not found with Id : " + PostId));
        return modelMapper.map(post,PostDto.class);
    }

    public List<PostDto> getAllPostOfUser(Long userId) {
        log.info("Fetching all post of user Id : " + userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList
                .stream()
                .map(Post -> modelMapper.map(Post,PostDto.class))
                .collect(Collectors.toList());
    }

}
