package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class BigOrSmallService implements JavaDelegate {
	//DiscardSciencePaperService
//EditorFinalDecision
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n CheckTopicSciencePaperService");

		String bigChange = sciencePaperData.get("bigChange").toString();
		String smallChange = sciencePaperData.get("smallChange").toString();

		if(bigChange.equals("yes")) {
			execution.setVariable("bigChange", true);
			System.out.println("\n Rad je za veliku izmenu");
		} else {
			execution.setVariable("bigChange", false);
			System.out.println("\nRad je za  malu izmenu");
		}
	}

}
