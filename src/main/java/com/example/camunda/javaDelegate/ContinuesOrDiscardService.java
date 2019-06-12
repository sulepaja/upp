package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class ContinuesOrDiscardService implements JavaDelegate {
	//DiscardSciencePaperService
//EditorFinalDecision
	//ContinuesOrDiscardService
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n ContinuesOrDiscardService");

		String lastDiscard = sciencePaperData.get("lastDiscard").toString();

		if(lastDiscard.equals("yes")) {
			execution.setVariable("lastDiscard", true);
			System.out.println("\n Rad je odbijen");
		} else {
			execution.setVariable("lastDiscard", false);
			System.out.println("\nRad nije odbijen");
		}
	}

}
