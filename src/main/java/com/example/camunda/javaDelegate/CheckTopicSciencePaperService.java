package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class CheckTopicSciencePaperService implements JavaDelegate {
	//DiscardSciencePaperService

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n provera za Brisanje SERVICE");

		String flag = sciencePaperData.get("discard").toString();
		
		if(flag.equals("yes")) {
			execution.setVariable("discard", true);
			System.out.println("\n Rad je odbijen");
		} else {
			execution.setVariable("discard", false);
			System.out.println("\nRad nije odbijen");
		}
	}

}
