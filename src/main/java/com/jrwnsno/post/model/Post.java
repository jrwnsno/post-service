package com.jrwnsno.post.model;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Post {
    private Integer postId;
    private String userId;
    private Integer communityId;
    private String postTitle;
    private String postBody;
    private Date addedDate;
    private Date modifiedDate;
    private List<Comments> comments;
}
