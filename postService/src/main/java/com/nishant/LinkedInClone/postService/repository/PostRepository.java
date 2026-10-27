package com.nishant.LinkedInClone.postService.repository;

import com.nishant.LinkedInClone.postService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
