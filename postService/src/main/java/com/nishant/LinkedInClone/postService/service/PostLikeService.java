package com.nishant.LinkedInClone.postService.service;

import com.nishant.LinkedInClone.postService.entity.PostLike;
import com.nishant.LinkedInClone.postService.exception.BadRequestException;
import com.nishant.LinkedInClone.postService.exception.ResourceNotFoundException;
import com.nishant.LinkedInClone.postService.repository.PostLikeRepository;
import com.nishant.LinkedInClone.postService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void likePost(Long postId) {
        Long userId = 1L;
        log.info("User with id: " + userId + " liking the post with id : " + postId);
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with Id : " + postId));
        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(hasAlreadyLiked) throw new BadRequestException("You cannot like the post again");
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
//      TODO: send notification to the owner of the post
    }

    @Transactional
    public void unlikePost(Long postId) {
        Long userId = 1L;
        log.info("User with Id : " + userId + " unliking the post with Id : " + postId);
        postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post not found with ID: "+postId));
        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(!hasAlreadyLiked) throw new BadRequestException("You cannot unlike the post that you have not liked yet");
        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
    }

}
