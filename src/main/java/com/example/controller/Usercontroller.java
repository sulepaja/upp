package com.example.controller;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.RegisterUserResponse;
import com.example.model.CurrentUser;
import com.example.model.User;
import com.example.service.UserService;
import com.example.test.Hashing;
//import com.example.test.Hashing;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class Usercontroller {

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
            value = "/login",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<User> login(@ModelAttribute("currentUser") CurrentUser currentUser) {
        processInstance = runtimeService.startProcessInstanceByKey("login"); // iz camunda modela

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
        System.out.println("\n--->>>UserController/login");

//		System.out.println("Entered into login controller.");				
//		User user = this.userService.findByEmail(email);
//		
//
//		String hashCheck = Hashing.hash(password);			// provera
//		System.out.println(hashCheck);
//		
//		if(user == null || !user.getPassword().equals(Hashing.hash(password))){
//			return new ResponseEntity<>((User) null, HttpStatus.OK);
//		}
//		
//		return new ResponseEntity<User>(user, HttpStatus.OK);
		System.out.println("treba da vrati:" +currentUser);

        HashMap<String, Object> podaci = new HashMap<String, Object>();
        podaci.put("currentUser", currentUser.getUsername());


        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, "loginData", podaci);
        formService.submitTaskForm(task.getId(), podaci);


        if(currentUser == null){
			return null;
		}


        return new ResponseEntity<User>(currentUser.getUser(), HttpStatus.OK);
	}
	// metodu rasclaniti na proces registracije pozivanje camunde i nakon provere preko kamunde aktivirati korisnika
	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
    public ResponseEntity<?> register(@RequestBody User data){
        processInstance = runtimeService.startProcessInstanceByKey("register"); // iz camunda modela
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
        System.out.println("\n--->>>UserController/register");

//        Optional<User> testUsername = userService.findByUsername(data.getUsername());
//        Optional<User> testEmail = userService.findByEmail(data.getEmail());
//
//
//		if(testUsername.isPresent()){
//			RegisterUserResponse response = new RegisterUserResponse(userService.findByUsername(data.getUsername()).get(), "username");
//			return new ResponseEntity<RegisterUserResponse>(response, HttpStatus.OK);
//		}
//		else if(testEmail.isPresent()){
//			RegisterUserResponse response = new RegisterUserResponse(userService.findByEmail(data.getEmail()).get(), "email");
//			return new ResponseEntity<RegisterUserResponse>(response, HttpStatus.OK);
//		}
//
//
//		String password = new BCryptPasswordEncoder().encode(data.getPassword());
//
//		User newUser = new User(data.getUsername(), data.getFirstname(), data.getLastname(), data.getCity(), data.getCountry(), data.getEmail(), password, "USER");
//
//		this.userService.register(newUser);
//		RegisterUserResponse response = new RegisterUserResponse(newUser,"success");
//		return new ResponseEntity<RegisterUserResponse>(response, HttpStatus.OK);

        HashMap<String, Object> podaci = new HashMap<String, Object>();
        podaci.put("email", data.getEmail());
        podaci.put("password", data.getPassword());
        podaci.put("username", data.getUsername());
        podaci.put("firstname", data.getFirstname());
        podaci.put("lastname", data.getLastname());
        podaci.put("country", data.getCountry());
        podaci.put("city", data.getCity());

        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, "registerData", podaci);
        formService.submitTaskForm(task.getId(), podaci);

        User newUser = new User(data.getUsername(), data.getFirstname(), data.getLastname(), data.getCity(), data.getCountry(), data.getEmail(), data.getPassword(), "USER");
    	RegisterUserResponse response = new RegisterUserResponse(newUser,"success");
    	return new ResponseEntity<RegisterUserResponse>(response, HttpStatus.OK);
    }


}
