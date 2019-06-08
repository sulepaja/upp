package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.SciencePaperDownload;

public interface SciencePaperDownloadRepository extends JpaRepository<SciencePaperDownload, Long>{
	SciencePaperDownload findByName(String name);
}
