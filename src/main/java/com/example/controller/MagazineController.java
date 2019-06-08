package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.KoncentratorData;
import com.example.model.CurrentUser;
import com.example.model.Magazine;
import com.example.model.User;
import com.example.service.MagazineService;
import com.example.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/magazine")
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	@Autowired
	private UserService userService;
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	TaskService taskService;


	@Autowired
	FormService formService;


	public static ProcessInstance processInstance;

	@RequestMapping(
            value = "/getAllMagazines",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<List<Magazine>> getAllMagazines(){

		List<Magazine> magazines = magazineService.findAll();

		return new ResponseEntity<List<Magazine>>(magazines, HttpStatus.OK);
	}

	@RequestMapping(
			value="/createKoncentratorData",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<KoncentratorData> createKoncentratorData(@RequestBody double price){

		KoncentratorData newData = new KoncentratorData(price,"2111111212121", "1");

		return new ResponseEntity<KoncentratorData>(newData, HttpStatus.OK);
	}

	@RequestMapping(
			value="/allowUser",
			method = RequestMethod.POST	,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> allowUser(@ModelAttribute("currentUser") CurrentUser currentUser, @RequestBody String magId){
		 processInstance = runtimeService.startProcessInstanceByKey("pay"); // iz camunda modela
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		System.out.println("\n--->>>MagazineController/allow");

		Magazine mag = magazineService.findById(Long.parseLong(magId));
		User loggedUser = currentUser.getUser();

		mag.getAllowedUsers().add(loggedUser);
		loggedUser.getAllowedMagazines().add(mag);

		magazineService.save(mag);
		userService.register(loggedUser);
		String type = "Allowed";
		HashMap<String, Object> magazineData = new HashMap<String, Object>();
		magazineData.put("type",type );

		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "magazineData", magazineData);
		formService.submitTaskForm(task.getId(), magazineData);



		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(
			value="/allowUser2",
			method = RequestMethod.POST	,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> allowUser2(@ModelAttribute("currentUser") CurrentUser currentUser, @RequestBody String magName){
		 processInstance = runtimeService.startProcessInstanceByKey("pay"); // iz camunda modela
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		System.out.println("\n--->>>MagazineController/allow2");

		Magazine mag = magazineService.findByName(magName);
		User loggedUser = currentUser.getUser();

		mag.getAllowedUsers().add(loggedUser);
		loggedUser.getAllowedMagazines().add(mag);

		magazineService.save(mag);
		userService.register(loggedUser);

		String type = "Allowed";
		HashMap<String, Object> magazineData = new HashMap<String, Object>();
		magazineData.put("type",type );

		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "magazineData", magazineData);
		formService.submitTaskForm(task.getId(), magazineData);

		return new ResponseEntity<>(HttpStatus.OK);
	}


}
