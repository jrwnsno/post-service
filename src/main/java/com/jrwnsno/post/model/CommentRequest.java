package com.jrwnsno.post.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {
    @NotNull(message = "postId is required")
    private Integer postId;
    private String userId;
    private String commentBody;
}
