package com.example.camunda.javaDelegate;

import com.example.model.SciencePaper;
import com.example.service.SciencePaperService;
import com.example.service.StorageService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
//MailTimeReviwer
@Service
@Transactional
public class DOIService implements JavaDelegate {
	//DiscardSciencePaperService

	@Autowired
	SciencePaperService sciencePaperService;

	@Autowired
	StorageService storageService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n  DOI is set...");


	}

}
