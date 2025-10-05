package com.jrwnsno.post.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePostRequest {
    @NotNull(message = "postId should not be null")
    private Integer postId;
    private String postTitle;
    @NotBlank(message = "postBody must not Blank or null")
    private String postBody;
}
