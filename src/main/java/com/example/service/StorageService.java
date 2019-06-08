package com.example.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface StorageService {
	void store (MultipartFile file);
	Resource loadFile(String filename);
	void deleteAll();
	void init();
	void delete(String filename);
}
