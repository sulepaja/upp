package com.example.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.ScientificField;
import com.example.repository.ScientificFieldRepository;
import com.example.service.ScientificFieldService;

@Service
public class ScientificFieldServiceImpl implements ScientificFieldService{

	@Autowired
	private ScientificFieldRepository sfRepository;
	
	@Override
	public ScientificField findByName(String name) {
		return this.sfRepository.findByName(name);
	}

	@Override
	public List<ScientificField> findAll() {
		return this.sfRepository.findAll();
	}

	@Override
	public ScientificField findById(Long id) {return this.sfRepository.findById(id).get(); }

}
