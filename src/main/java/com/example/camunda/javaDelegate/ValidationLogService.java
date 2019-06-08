package com.example.camunda.javaDelegate;

import com.example.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class ValidationLogService implements JavaDelegate {

	@Autowired
	UserRepository userRepository;
	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> loginData = (HashMap<String, Object>) execution.getVariable("loginData");
		System.out.println("\n--->>>Camunda/ValidationLogService");
		if(loginData == null){
			execution.setVariable("uspesnaValidacija", false);
		}else {
			execution.setVariable("uspesnaValidacija", true);
		}

	}
	
}
