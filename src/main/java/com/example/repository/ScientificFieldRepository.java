package com.example.repository;
//import org.springframework.data.jpa.repository.JpaRepository;
//
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.ScientificField;

public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long>{
	ScientificField findByName(String name);
}
