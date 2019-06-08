package com.example.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Magazine;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MagazineRepository extends JpaRepository<Magazine, Long>{

  //Magazine findById(Long id); //PROVERI DA LI JE ISPRAVNO
    //<Magazine> findById(Long id);
    Magazine findByName(String name);
    Magazine findByEditorId(Long id);
}
