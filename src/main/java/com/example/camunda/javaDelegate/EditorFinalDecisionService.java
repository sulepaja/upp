package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class EditorFinalDecisionService implements JavaDelegate {
	//DiscardSciencePaperService
//EditorFinalDecision
	//ContinuesOrDiscardService
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n CheckTopicSciencePaperService");

		String flag = sciencePaperData.get("finalDecision").toString();
		
		if(flag.equals("yes")) {
			execution.setVariable("finalDecision", true);
			System.out.println("\n Rad je prihvacen");
		} else {
			execution.setVariable("finalDecision", false);
			System.out.println("\nRad nije prihvacen");
		}
	}

}
