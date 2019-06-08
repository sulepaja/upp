package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class FormattedSciencePaperService implements JavaDelegate {


	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n formatted delegate...");

		String flag = sciencePaperData.get("goodFormatted").toString();

		if(flag.equals("yes")) {
			execution.setVariable("goodFormatted", true);
			System.out.println("\n Rad je je dobro formatiran");
		} else {
			execution.setVariable("goodFormatted", false);
			System.out.println("\nRad nije lose formatiran");
		}
	}

}
