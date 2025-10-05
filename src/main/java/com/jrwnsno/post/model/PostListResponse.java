package com.jrwnsno.post.model;

import lombok.Data;
import java.util.List;

@Data
public class PostListResponse extends ResponseDefault {
    private List<Post> postList;
}
