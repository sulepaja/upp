package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.SciencePaperDownload;
import com.example.repository.SciencePaperDownloadRepository;
import com.example.service.SciencePaperDownloadService;

@Service
public class SciencePaperDownloadServiceImpl implements SciencePaperDownloadService{
	@Autowired
	SciencePaperDownloadRepository repository;

	@Override
	public SciencePaperDownload save(SciencePaperDownload paper) {
		return this.repository.save(paper);
	}

	@Override
	public SciencePaperDownload findByName(String name) {
		return this.repository.findByName(name);
	}
}
