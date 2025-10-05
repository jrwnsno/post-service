package com.jrwnsno.post.controller;

import com.jrwnsno.post.model.*;
import com.jrwnsno.post.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDefault> createPost(@RequestBody @Valid CreatePostRequest post) {
        return postService.createPost(post);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<PostListResponse> getAllPost() {
        return postService.getAllPost();
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDefault> updatePost(@RequestBody @Valid UpdatePostRequest updatePostRequest) {
        return postService.updatePost(updatePostRequest);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ResponseDefault> deletePost(@PathVariable Integer postId) {
        return postService.deletePost(postId);
    }

    @GetMapping("/getUserPost/{userId}")
    public ResponseEntity<PostListResponse> getAllPost(@PathVariable String userId) {
        return postService.getAllPostByUserId(userId);
    }

    @GetMapping("/getUserPost/community/{communityId}")
    public ResponseEntity<PostListResponse> getAllPost(@PathVariable Integer communityId) {
        return postService.getAllPostByCommunityId(communityId);
    }

}
