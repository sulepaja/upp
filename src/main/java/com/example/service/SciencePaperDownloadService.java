package com.example.service;

import com.example.model.SciencePaperDownload;

public interface SciencePaperDownloadService {
	SciencePaperDownload save(SciencePaperDownload paper);
	SciencePaperDownload findByName(String name); 
}
