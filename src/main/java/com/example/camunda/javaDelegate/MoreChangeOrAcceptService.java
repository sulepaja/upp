package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class MoreChangeOrAcceptService implements JavaDelegate {
	//DiscardSciencePaperService
//EditorFinalDecision
	//MoreChangeOrAcceptService
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n MoreChangeOrAcceptService");

		String flag = sciencePaperData.get("moreChange").toString();
		
		if(flag.equals("yes")) {
			execution.setVariable("moreChange", true);
			System.out.println("\n Rad  jos dorada");
		} else {
			execution.setVariable("moreChange", false); // zavrsava proces
			System.out.println("\nRad  ne treba vise dorada");
		}
	}

}
