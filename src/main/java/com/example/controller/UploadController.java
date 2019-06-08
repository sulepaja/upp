package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.model.CurrentUser;
import com.example.model.Magazine;
import com.example.model.SciencePaper;
import com.example.model.User;
import com.example.service.MagazineService;
import com.example.service.SciencePaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.service.StorageService;

import static com.example.serviceImpl.StorageServiceImpl.rootLocation;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	StorageService storageService;

	@Autowired
	MagazineService magazineService;

	@Autowired
	SciencePaperService sciencePaperService;
	
	List<String> files = new ArrayList<String>();

	@PostMapping("/post/{id}")
	public ResponseEntity<String> handleFileUpload(@ModelAttribute("currentUser") CurrentUser currentUser, @ModelAttribute SciencePaper sciencePaper, @PathVariable Long id) {
		System.out.println("\n upload con");

		Magazine magazine = magazineService.findById(id);
		User user = currentUser.getUser();
		SciencePaper newSciencePaper = new SciencePaper(sciencePaper.getName(), sciencePaper.getKeywords(), sciencePaper.getAbbstract(), sciencePaper.getScentificField(), sciencePaper.getTextPDF());

		newSciencePaper.setScienceMagazine(magazine);
		newSciencePaper.setNameMagazine(newSciencePaper.getScienceMagazine().getName());
		newSciencePaper.setNameScientifiField(newSciencePaper.getScienceMagazine().getScientificField().getName());
		System.out.println("\n\t\tnaucna oblast kojoj pripada rad: " + newSciencePaper.getScienceMagazine().getScientificField().getName());


		newSciencePaper.setAuthor(user);

		newSciencePaper.setCoAuthor(new ArrayList<User>());
		newSciencePaper.setScentificField(newSciencePaper.getScienceMagazine().getScientificField());
		newSciencePaper.setLocationOnDrive(sciencePaper.getTextPDF()[0].getOriginalFilename());

		sciencePaperService.save(newSciencePaper);
		storageService.store(sciencePaper.getTextPDF()[0]);

		System.out.println("\n\nLOCATION::::::::::" + rootLocation);

		return ResponseEntity.status(HttpStatus.OK).body("Success!");
	}

	@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(fileNames);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
}
