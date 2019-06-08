package com.example.controller;
import com.example.DTO.StringDTO;
import com.example.model.SciencePaper;
import com.example.model.*;
import com.example.service.*;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.serviceImpl.StorageServiceImpl.rootLocation;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/paper")
public class SciencePaperController {
	
	@Autowired
	SciencePaperDownloadService sciencePaperDownloadService;

	@Autowired
	SciencePaperService sciencePaperService;

	@Autowired
	StorageService storageService;

	@Autowired
	MagazineService magazineService;

	@Autowired
	IdentityService identityService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	FormService formService;

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;

	@Autowired
	ScientificFieldService scientificFieldService;

	public static ProcessInstance processInstance;

	List<String> files = new ArrayList<String>();

	public HashMap<String, Object> sciencePaperData = new HashMap<String, Object>();

	@PostMapping("/postUpload/{id}")
	public ResponseEntity<String> handleFileUpload(@ModelAttribute("currentUser") CurrentUser currentUser, @ModelAttribute SciencePaper sciencePaper, @PathVariable Long id) {
		System.out.println("\n paper con");

		processInstance = runtimeService.startProcessInstanceByKey("rad"); // pokrecemo glavni proces
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);


			Magazine magazine = magazineService.findById(id);
			User user = currentUser.getUser();
			SciencePaper newSciencePaper = new SciencePaper(sciencePaper.getName(), sciencePaper.getKeywords(), sciencePaper.getAbbstract(), sciencePaper.getScentificField(), sciencePaper.getTextPDF());
		if(user != null && magazine != null) {
			newSciencePaper.setScienceMagazine(magazine);
			newSciencePaper.setNameMagazine(newSciencePaper.getScienceMagazine().getName());
			newSciencePaper.setNameScientifiField(newSciencePaper.getScienceMagazine().getScientificField().getName());
			System.out.println("\n\t\tnaucna oblast kojoj pripada rad: " + newSciencePaper.getScienceMagazine().getScientificField().getName());


			newSciencePaper.setAuthor(user);

			newSciencePaper.setCoAuthor(new ArrayList<User>());
			newSciencePaper.setScentificField(newSciencePaper.getScienceMagazine().getScientificField());
			newSciencePaper.setLocationOnDrive(sciencePaper.getTextPDF()[0].getOriginalFilename());
            newSciencePaper.setEditor(newSciencePaper.getScienceMagazine().getEditor());
			// for camunda
			sciencePaperData.put("authorId", user.getId());
			sciencePaperData.put("magazineId", newSciencePaper.getScienceMagazine().getId());
			sciencePaperData.put("name", newSciencePaper.getName());
			sciencePaperData.put("keywords", newSciencePaper.getKeywords());
			sciencePaperData.put("abbstract", newSciencePaper.getAbbstract());
			//sciencePaperData.put("coauthor", newSciencePaper.getCoAuthor());
            sciencePaperData.put("editorId", newSciencePaper.getScienceMagazine().getEditor().getId());
			sciencePaperData.put("discard", "no");
			sciencePaperData.put("goodFormatted", "yes");
			sciencePaperData.put("updated", "no");
			sciencePaperData.put("addMoreReviewer","yes");//dokle god ne stisne dugme potvrdi na dijalogu za dodavanje reviewra dotle ce ici u krug
			sciencePaperData.put("commentId","0");
            //

			sciencePaperService.save(newSciencePaper);
			storageService.store(sciencePaper.getTextPDF()[0]);

			System.out.println("\n\nLocation ::" + rootLocation);

			//for camunda
			sciencePaperData.put("sciencePaperId", newSciencePaper.getId());
			String processInstanceId = task.getProcessInstanceId();
			runtimeService.setVariable(processInstanceId, "sciencePaperData", sciencePaperData);
			formService.submitTaskForm(task.getId(), sciencePaperData);
			return ResponseEntity.status(HttpStatus.OK).body("Success!");
		}else{
			System.out.println("Error User or magazine is null !!!");
			return ResponseEntity.status(HttpStatus.OK).body("Success!");
		}
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

	@RequestMapping(
			value = "/download/{paperName}",
			method = RequestMethod.GET            
	)
	public ResponseEntity<Resource>downloadPaper(@PathVariable String paperName){
		//SciencePaperDownload paper = sciencePaperService.findByName(paperName);

//		String extension = paper.getMimetype().split("/")[1];
//		System.out.println("EKSTENZIJA JE :::::::::::::" + extension);

		Resource resource = storageService.loadFile(paperName);
		
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + paperName +".pdf" + "" + "\"")
			.body(resource);
				
	}
	
	@RequestMapping(
			value="/getUnapproved",
			method = RequestMethod.GET
	)
	public ResponseEntity<List<SciencePaper>> getAllUnApprovedSciencePapers(@ModelAttribute("currentUser") CurrentUser currentUser){
		//Magazine mag = magazineService.findByEditorId(currentUser.getId());
		List<SciencePaper> allPapers = sciencePaperService.findAll();
		ArrayList<SciencePaper> unApprovedPapers = new ArrayList<SciencePaper>();

		for(SciencePaper paper: allPapers){
			System.out.println("SCIENCE PAPER IS APPROVED? " + paper.getApproved());
			if(!paper.getApproved()) {
				unApprovedPapers.add(paper);
			}
		}
		return new ResponseEntity<List<SciencePaper>>(unApprovedPapers, HttpStatus.OK);
	}

	@RequestMapping(
			value="/delete/{id}",
			method = RequestMethod.DELETE
	)// rad tematski odbijen
	public ResponseEntity deleteSciencePaper(@PathVariable String id){
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		System.out.println("\n Brisanje KONTROLER");

		//SciencePaper paper = sciencePaperService.findById(Long.parseLong(id));
		//this.sciencePaperService.delete(paper.getId());
		sciencePaperData.replace("sciencePaperId", id);
		sciencePaperData.replace("discard", "yes"); // kada se napravi dugme da je rad prosao prvu proveru bacamo ga na kontroler sa drugom
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "sciencePaperData", sciencePaperData);
		formService.submitTaskForm(task.getId(), sciencePaperData);

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(
			value="/addComment/{paperId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Boolean>addComment(@PathVariable String paperId, @RequestBody StringDTO comment, @ModelAttribute("currentUser") CurrentUser currentUser) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

		SciencePaper sciencePaper = sciencePaperService.findById(Long.parseLong(paperId));
		User user = currentUser.getUser();
		Comment newComment = new Comment(user, sciencePaper, comment.getCommentValue());

		System.out.println("\n badFormatted controller");
		sciencePaper.getComments().add(newComment);

		sciencePaper.setStatus("wrong_formatting");

		commentService.save(newComment);
		sciencePaperService.save(sciencePaper);

		System.out.println("\n badFormatted controller");

		sciencePaperData.replace("goodFormatted", "no");
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "sciencePaperData", sciencePaperData);
		formService.submitTaskForm(task.getId(), sciencePaperData);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}


	@RequestMapping(
			value="/updatedSciencePaper/{paperId}",
			method = RequestMethod.POST
	)
	public ResponseEntity<String> updatedSciencePaper(@ModelAttribute("currentUser") CurrentUser currentUser, @RequestParam("file") MultipartFile file, @PathVariable String paperId){
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

		SciencePaper sciencePaper = sciencePaperService.findById(Long.parseLong(paperId));

		String message = "";
		try {
			storageService.delete(sciencePaper.getLocationOnDrive());
			storageService.store(file);
			sciencePaper.setLocationOnDrive(file.getOriginalFilename());
			sciencePaper.setStatus("editor");
			sciencePaperService.save(sciencePaper);

			System.out.println("\n updatedSciencePaper controller");
			sciencePaperData.replace("updated", "yes");
			String processInstanceId = task.getProcessInstanceId();
			runtimeService.setVariable(processInstanceId, "sciencePaperData", sciencePaperData);
			formService.submitTaskForm(task.getId(), sciencePaperData);

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
    //Kada pritisne glavni urednik prihvati rad
	@RequestMapping(
			value="/approve-by-main-editor/{paperId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<SciencePaper> approvedSciencePaper(@PathVariable String paperId){
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

		SciencePaper sciencePaper = sciencePaperService.findById(Long.parseLong(paperId));
		sciencePaper.setStatus("sec_editor_rev");

//		System.out.println("PRE PETLJE");
//		for(ScientificField field: userService.findByEmail("seditor1@editor.com").get().getListScientificField()){
//			System.out.println("NAUCNO POLJE");
//			System.out.println(field.getName());
//		}
		ScientificField field = sciencePaper.getScentificField();

		int rnd = new Random().nextInt(field.getSecEditor().size());
		sciencePaper.setEditor(field.getSecEditor().get(rnd));

		sciencePaperService.save(sciencePaper);

		sciencePaperData.replace("sciencePaperId", sciencePaper.getId());
		System.out.println("\n approvedSciencePaper controller");
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "sciencePaperData", sciencePaperData);
		formService.submitTaskForm(task.getId(), sciencePaperData);

		return new ResponseEntity<SciencePaper>(sciencePaper, HttpStatus.OK);
	}

//kada zavrsi dodavanje recenzenata potvrdjuje
    @RequestMapping(
            value="/confirmAddReviwer",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<SciencePaper>> confirmAddReviwer(@ModelAttribute("currentUser") CurrentUser currentUser){
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

        //TODO


        System.out.println("\n updatedSciencePaper controller");
        sciencePaperData.replace("addMoreReviewer", "no");//uci ce u kontroler tek kada se pritisne dugme a proces ConfirmAddReviwerSciencePaperService ce se pokretati svaki put posle dodavanja
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, "sciencePaperData", sciencePaperData);
        formService.submitTaskForm(task.getId(), sciencePaperData);

        return new ResponseEntity<List<SciencePaper>>( HttpStatus.OK);
    }




}
