package com.jrwnsno.post.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comments {
    private Integer commentId;
    private Integer postId;
    private String userId;
    private String commentBody;
    private Date addedDate;
    private Date modifiedDate;
}
