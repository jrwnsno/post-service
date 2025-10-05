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
public class CommentServiceImpl implements CommentService{

    private final ConcurrentHashMap<Integer, Comments> commentMap = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final Mapper mapper;

    @Override
    public ResponseEntity<ResponseDefault> createComment(@RequestBody @Valid CommentRequest commentRequest){
        ResponseDefault responseDefault = new ResponseDefault();
        int commentId = atomicInteger.getAndIncrement();

        Comments comments = mapper.createComment(commentRequest,commentId);
        commentMap.put(commentId, comments);

        return ResponseEntity.ok(responseDefault.success());
    }

    @Override
    public ResponseEntity<ResponseDefault> deleteComment(@PathVariable Integer commentId){
        ResponseDefault responseDefault = new ResponseDefault();

        if(commentMap.containsKey(commentId)){
            commentMap.remove(commentId);
            return ResponseEntity.ok(responseDefault.success());
        }
        log.info("commentId not found");
        throw new RuntimeException("comment not found");
    }

    @Override
    public ResponseEntity<ResponseDefault> updateComment(@RequestBody @Valid UpdateCommentRequest updateCommentRequest){
        ResponseDefault responseDefault = new ResponseDefault();
        Comments savedComments = commentMap.get(updateCommentRequest.getCommentId());

        if (savedComments != null){
            savedComments.setCommentBody(updateCommentRequest.getCommentBody());
            savedComments.setModifiedDate(new Date());
            commentMap.put(updateCommentRequest.getCommentId(), savedComments);

            return ResponseEntity.ok(responseDefault.success());
        }
        log.info("commentId not found");
        throw new RuntimeException("comment not found");
    }

    @Override
    public List<Comments> getAllCommentsByPostId(Integer postId){
        List<Comments> commentList = new ArrayList<>();

        for (Comments UserComments : commentMap.values()){
            if (UserComments.getPostId().equals(postId)){
                commentList.add(UserComments);
            }
        }

        return commentList;
    }

}
