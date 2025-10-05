package com.jrwnsno.post.model;

import lombok.Data;

@Data
public class ResponseDefault {
    private String responseCode;
    private String responseMessage;

    public ResponseDefault success() {
        this.responseCode = "00";
        this.responseMessage = "success";
        return this;
    }
}
