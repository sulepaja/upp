package com.example.serviceImpl;

import com.example.model.Comment;
import com.example.model.SciencePaper;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    public Comment findById(Long id) {
        return this.commentRepository.findById(id).get();
    }

}
