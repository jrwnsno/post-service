package com.jrwnsno.post.controller;

import com.jrwnsno.post.model.CommentRequest;
import com.jrwnsno.post.model.ResponseDefault;
import com.jrwnsno.post.model.UpdateCommentRequest;
import com.jrwnsno.post.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDefault> createComment(@RequestBody @Valid CommentRequest commentRequest){
        return commentService.createComment(commentRequest);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ResponseDefault> deleteComment(@PathVariable Integer commentId) {
        return commentService.deleteComment(commentId);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDefault> updateComment(@RequestBody @Valid UpdateCommentRequest updateCommentRequest){
        return commentService.updateComment(updateCommentRequest);
    }

}
