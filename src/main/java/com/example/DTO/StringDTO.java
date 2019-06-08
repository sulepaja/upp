package com.example.DTO;

public class StringDTO {
    private String commentValue;

    public StringDTO(String commentValue) {
        this.commentValue = commentValue;
    }

    public StringDTO() {}

    public String getCommentValue() {
        return commentValue;
    }

    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue;
    }
}
