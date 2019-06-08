package com.example.service;

import com.example.model.Comment;
import com.example.model.SciencePaper;

public interface CommentService {

    Comment save (Comment comment);
    void delete(Long id);
    Comment findById(Long id);
}
