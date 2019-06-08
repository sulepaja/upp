package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class UpdatedSciencePaperService implements JavaDelegate {


	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n update sp delegate...");

		String flag = sciencePaperData.get("updated").toString();

		if(flag.equals("yes")) {
			execution.setVariable("updated", true);
			System.out.println("\n Rad je azuriran");
		} else {
			execution.setVariable("updated", false);
			System.out.println("\nRad nije azuriran");
		}
	}

}
