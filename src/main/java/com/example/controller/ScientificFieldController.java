package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ScientificField;
import com.example.service.ScientificFieldService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/scientificFields")
public class ScientificFieldController {

	@Autowired
	ScientificFieldService scientificFieldService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ScientificField>> getAll(){
		return new ResponseEntity<List<ScientificField>>(scientificFieldService.findAll(), HttpStatus.OK);
	}
	
}
