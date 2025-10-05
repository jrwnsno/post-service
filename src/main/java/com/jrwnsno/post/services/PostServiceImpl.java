package com.jrwnsno.post.services;

import com.jrwnsno.post.model.*;
import com.jrwnsno.post.util.Mapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ConcurrentHashMap<Integer,Post> postByPostId = new ConcurrentHashMap<>();
    private final AtomicInteger postIdGenerator = new AtomicInteger(1);
    private final Mapper mapper;
    private final CommentService commentService;

    @Override
    public ResponseEntity<ResponseDefault> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
        ResponseDefault response = new ResponseDefault();
        int postId = postIdGenerator.getAndIncrement();
        Post createPost = mapper.createPost(createPostRequest, postId);

        postByPostId.put(postId,createPost);

        return ResponseEntity.ok(response.success());
    }

    @Override
    public ResponseEntity<ResponseDefault> updatePost(@RequestBody @Valid UpdatePostRequest updatePostRequest){
        ResponseDefault responseDefault = new ResponseDefault();
        Post savedPost = postByPostId.get(updatePostRequest.getPostId());

        if(savedPost != null){
            savedPost.setPostTitle(updatePostRequest.getPostTitle());
            savedPost.setPostBody(updatePostRequest.getPostBody());
            savedPost.setModifiedDate(new Date());
            postByPostId.put(updatePostRequest.getPostId(), savedPost);

            return  ResponseEntity.ok(responseDefault.success());
        }
        log.info("Failed to Update Post");
        throw new RuntimeException("Failed to Update Post");
    }

    @Override
    public ResponseEntity<ResponseDefault> deletePost(@PathVariable Integer postId){
        ResponseDefault responseDefault = new ResponseDefault();
        if (postByPostId.containsKey(postId)){
            postByPostId.remove(postId);
            return ResponseEntity.ok(responseDefault.success());
        }
        log.error("Cannot find postId");
        throw new RuntimeException("Failed to Delete Post");
    }

    @Override
    public ResponseEntity<PostListResponse> getAllPost() {
        PostListResponse postListResponse = new PostListResponse();
        if (postByPostId.isEmpty()){
            postListResponse.setPostList(new ArrayList<>());
            return ResponseEntity.ok(postListResponse);
        }
        List<Post> postList = new ArrayList<>();
        for (Post post : postByPostId.values()){
            List<Comments> listComment = commentService.getAllCommentsByPostId(post.getPostId());
            List<Comments> newCommentList = new ArrayList<>();
            if (listComment != null) {

                for (Comments comments : listComment){
                    if (comments.getPostId().equals(post.getPostId())){
                        newCommentList.add(comments);
                    }
                }

                post.setComments(newCommentList);
                postList.add(post);
            }
        }
        postListResponse.setPostList(postList);
        postListResponse.success();

        return ResponseEntity.ok(postListResponse);
    }

    @Override
    public ResponseEntity<PostListResponse> getAllPostByUserId(@PathVariable String userId){
        PostListResponse postListResponse = new PostListResponse();
        List<Post> postList = new ArrayList<>();

        if (postByPostId.isEmpty()){
            postListResponse.setPostList(new ArrayList<>());
            return ResponseEntity.ok(postListResponse);
        }

        for (Post posts : postByPostId.values()) {
            List<Comments> listComment = commentService.getAllCommentsByPostId(posts.getPostId());
            List<Comments> newCommentList = new ArrayList<>();
            if (posts.getUserId().equals(userId)){
                for (Comments comments : listComment){
                    if (comments.getPostId().equals(posts.getPostId())){
                        newCommentList.add(comments);
                    }
                }
                posts.setComments(newCommentList);
                postList.add(posts);
            }
        }

        postListResponse.setPostList(postList);
        postListResponse.success();

        return ResponseEntity.ok(postListResponse);
    }

    @Override
    public ResponseEntity<PostListResponse> getAllPostByCommunityId(@PathVariable Integer communityId){
        PostListResponse postListResponse = new PostListResponse();
        List<Post> postList = new ArrayList<>();

        if (postByPostId.isEmpty()){
            postListResponse.setPostList(new ArrayList<>());
            return ResponseEntity.ok(postListResponse);
        }

        for (Post posts : postByPostId.values()) {
            List<Comments> listComment = commentService.getAllCommentsByPostId(posts.getPostId());
            List<Comments> newCommentList = new ArrayList<>();
            if (posts.getCommunityId().equals(communityId)){
                for (Comments comments : listComment) {
                    if (comments.getPostId().equals(posts.getPostId())) {
                        newCommentList.add(comments);
                    }
                }
                posts.setComments(newCommentList);
                postList.add(posts);
            }
        }
        postListResponse.setPostList(postList);
        postListResponse.success();

        return ResponseEntity.ok(postListResponse);
    }

}
