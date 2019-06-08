package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Magazine;

public interface MagazineService {
	List<Magazine> findAll();
	Magazine  findById(Long id);
	Magazine save(Magazine mag);
	Magazine findByName(String name);
	Magazine findByEditorId(Long id);
}
