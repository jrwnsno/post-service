package com.jrwnsno.post.services;

import com.jrwnsno.post.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PostService {
    ResponseEntity<ResponseDefault> createPost(@RequestBody CreatePostRequest createPostRequest);

    ResponseEntity<ResponseDefault> updatePost(@RequestBody UpdatePostRequest updatePostRequest);

    ResponseEntity<ResponseDefault> deletePost(@PathVariable Integer postId);

    ResponseEntity<PostListResponse> getAllPost();

    ResponseEntity<PostListResponse> getAllPostByUserId(@PathVariable String userId);

    ResponseEntity<PostListResponse> getAllPostByCommunityId(@PathVariable Integer communityId);
}
