package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Magazine;
import com.example.repository.MagazineRepository;
import com.example.service.MagazineService;

@Service
public class MagazineServiceImpl implements MagazineService{

	@Autowired
	private MagazineRepository magazineRepository;

	@Override
	public List<Magazine> findAll() {
		return this.magazineRepository.findAll();
	}

	@Override
	public Magazine  findById(Long id) {
		return this.magazineRepository.getOne(id);
	}

	@Override
	public Magazine save(Magazine mag) {
		return this.magazineRepository.save(mag);
	}

	@Override
	public Magazine findByName(String name) {
		return this.magazineRepository.findByName(name);
	}

	@Override
	public Magazine findByEditorId(Long id) { return this.magazineRepository.findByEditorId(id); }

}
