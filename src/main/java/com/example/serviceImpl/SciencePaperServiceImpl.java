package com.example.serviceImpl;


import com.example.model.SciencePaper;
import com.example.repository.SciencePaperRepository;
import com.example.service.SciencePaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SciencePaperServiceImpl implements SciencePaperService{

    @Autowired
    private SciencePaperRepository sciencePaperRepository;


    @Override
    public List<SciencePaper> findAll() {
        return this.sciencePaperRepository.findAll();
    }

    @Override
    public SciencePaper findByName(String name) {
        return this.sciencePaperRepository.findByName(name);
    }

    @Override
    public List<SciencePaper> save(List<SciencePaper> sciencePapers) {
        return sciencePaperRepository.saveAll(sciencePapers);
    }
    @Override
    public SciencePaper save(SciencePaper sciencePaper) {
        return sciencePaperRepository.save(sciencePaper);
    }

    @Override
    public List<SciencePaper> findByApproved(Boolean approved) {
        return sciencePaperRepository.findByApproved(approved);
    }

    @Override
    public void delete(Long id) {
        this.sciencePaperRepository.deleteById(id);
    }

    @Override
    public SciencePaper findById(Long id) {
        return this.sciencePaperRepository.findById(id).get();
    }
}