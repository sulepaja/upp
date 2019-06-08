package com.example.camunda.javaDelegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class MagazineTypeService implements JavaDelegate {
	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> magazineData = (HashMap<String, Object>) execution.getVariable("magazineData");
		System.out.println("\nMagazineTypeService ");
		//magazineData.put("type", "Allowed");
			if((magazineData.get("type").toString()).equals("Allowed")) {
				execution.setVariable("validation", true);
				System.out.println("\n\t\tTip casopisa .\n");
			} else {
				execution.setVariable("validation", false);
				System.out.println("\n\t\tTip casopisa nije \n");
			}

	}

}
