package com.example.camunda.javaDelegate;

import com.example.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ValidationRegService implements JavaDelegate {

	@Autowired
	UserRepository userRepository;
	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> registracija = (HashMap<String, Object>) execution.getVariable("registerData");
		System.out.println("\n--->>>Camunda/ValidationRegService");
		if(userRepository.findByEmailAndPassword(registracija.get("email").toString(), registracija.get("password").toString()) != null)
			execution.setVariable("uspesnaValidacija", false);
		else
			execution.setVariable("uspesnaValidacija", true);
	}
	
}
