package com.example.camunda.javaDelegate;

import com.example.DTO.RegisterUserResponse;
import com.example.model.User;
import com.example.service.UserService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.HashMap;
import java.util.Optional;

@Service
@Transactional
public class RegisterService implements JavaDelegate {

	@Autowired
	IdentityService identityService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")

		HashMap<String, Object> podaci = (HashMap<String, Object>) execution.getVariable("registerData");
		System.out.println("\n--->>>Camunda/RegisterService");
		User data = new User();
		data.setEmail(podaci.get("email").toString());
		data.setPassword(podaci.get("password").toString());
		data.setUsername(podaci.get("username").toString());
		data.setFirstname(podaci.get("firstname").toString());
		data.setLastname(podaci.get("lastname").toString());
		data.setCity(podaci.get("city").toString());
		data.setCountry(podaci.get("country").toString());
		data.setRole("USER");

        Optional<User> testUsername = userService.findByUsername(data.getUsername());
        Optional<User> testEmail = userService.findByEmail(data.getEmail());


		if(testUsername.isPresent()){
			RegisterUserResponse response = new RegisterUserResponse(userService.findByUsername(data.getUsername()).get(), "username");
			//return new ResponseEntity<RegisterUserResponse>(response, HttpStatus.OK);
		}
		else if(testEmail.isPresent()){
			RegisterUserResponse response = new RegisterUserResponse(userService.findByEmail(data.getEmail()).get(), "email");
			//return new ResponseEntity<RegisterUserResponse>(response, HttpStatus.OK);
		}


		String password = new BCryptPasswordEncoder().encode(data.getPassword());

		User newUser = new User(data.getUsername(), data.getFirstname(), data.getLastname(), data.getCity(), data.getCountry(), data.getEmail(), password, "USER");

		this.userService.register(newUser);
		RegisterUserResponse response = new RegisterUserResponse(newUser,"success");
		//return new ResponseEntity<RegisterUserResponse>(response, HttpStatus.OK);


//
//		User camundaUser = identityService.newUser(k.getEmail());
//		camundaUser.setEmail(k.getEmail());
//		camundaUser.setPassword(k.getLozinka());
//		camundaUser.setFirstName(k.getIme());
//		camundaUser.setLastName(k.getPrezime());
//
//		if(korisnikService.save(k) != null)
//			identityService.saveUser(camundaUser);
//	}
	}
}
