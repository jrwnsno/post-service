package com.jrwnsno.post.services;

import com.jrwnsno.post.model.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentService {
    ResponseEntity<ResponseDefault> createComment(@RequestBody @Valid CommentRequest commentRequest);

    ResponseEntity<ResponseDefault> deleteComment(@PathVariable Integer commentId);

    ResponseEntity<ResponseDefault> updateComment(@RequestBody @Valid UpdateCommentRequest updateCommentRequest);

    List<Comments> getAllCommentsByPostId(Integer postId);
}
