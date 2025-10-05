package com.jrwnsno.post.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCommentRequest {
    @NotNull(message = "commentId must not be null")
    private Integer commentId;
    @NotBlank(message = "commentBody must not be blank or null")
    private String commentBody;
}
