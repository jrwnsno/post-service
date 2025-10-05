package com.jrwnsno.post.util;

import com.jrwnsno.post.model.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Mapper {

    public Post createPost(CreatePostRequest createPostRequest, Integer postId) {
        Post post = new Post();
        post.setPostId(postId);
        post.setCommunityId(createPostRequest.getCommunityId());
        post.setPostTitle(createPostRequest.getPostTitle());
        post.setPostBody(createPostRequest.getPostBody());
        post.setUserId(createPostRequest.getUserId());
        post.setAddedDate(new Date());
        post.setModifiedDate(new Date());

        return post;
    }

    public Comments createComment(CommentRequest commentRequest, Integer commentId){
        Comments comments = new Comments();
        comments.setCommentId(commentId);
        comments.setUserId(commentRequest.getUserId());
        comments.setPostId(commentRequest.getPostId());
        comments.setCommentBody(commentRequest.getCommentBody());
        comments.setAddedDate(new Date());
        comments.setModifiedDate(new Date());

        return comments;
    }

}
