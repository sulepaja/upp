package com.example.service;

import com.example.model.SciencePaper;

import java.util.List;

public interface SciencePaperService {
    List<SciencePaper> findAll();
    SciencePaper findByName(String name);
    List<SciencePaper> save(List<SciencePaper> sciencePapers);
    SciencePaper save(SciencePaper sciencePaper);
    List<SciencePaper> findByApproved(Boolean approved);
    void delete(Long id);
    SciencePaper findById(Long id);
}
