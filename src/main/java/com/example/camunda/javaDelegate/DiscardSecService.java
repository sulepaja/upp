package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class DiscardSecService implements JavaDelegate {
	//DiscardSciencePaperService
//EditorFinalDecision
	//ContinuesOrDiscardService
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n DiscardSecService");

		String flag = sciencePaperData.get("secDiscard").toString();
		
		if(flag.equals("yes")) {
			execution.setVariable("secDiscard", true);
			System.out.println("\n Rad odbijen");
		} else {
			execution.setVariable("secDiscard", false);
			System.out.println("\nRad nije odbijen");
		}
	}

}
