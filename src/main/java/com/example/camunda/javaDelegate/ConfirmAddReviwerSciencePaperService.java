package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class ConfirmAddReviwerSciencePaperService implements JavaDelegate {
	//DiscardSciencePaperService

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n ConfirmAddReviwerSciencePaperService");

		String flag = sciencePaperData.get("addMoreReviewer").toString();
		
		if(flag.equals("yes")) {
			execution.setVariable("addMoreReviewer", true);
			System.out.println("\n dodaj jos recenzenata");
		} else {
			execution.setVariable("addMoreReviewer", false);
			System.out.println("\nzavrseno dodavanje recenzenata");
		}
	}

}
