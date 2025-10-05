package com.jrwnsno.post.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePostRequest {
    private String userId;
    private Integer communityId;
    private String postTitle;
    @NotBlank(message = "postBody must not Blank or null")
    private String postBody;
}
