package com.example.service;

import java.util.List;

import com.example.model.ScientificField;

public interface ScientificFieldService {
	ScientificField findByName(String name);
	List<ScientificField> findAll();
	ScientificField findById(Long id);
}
