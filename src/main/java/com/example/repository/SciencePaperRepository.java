package com.example.repository;

import com.example.model.SciencePaper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SciencePaperRepository extends JpaRepository<SciencePaper, Long> {
    SciencePaper findByName(String name);
    List<SciencePaper> findByApproved(Boolean approved);
}
