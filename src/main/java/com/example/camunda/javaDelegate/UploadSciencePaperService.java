package com.example.camunda.javaDelegate;

import com.example.model.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UploadSciencePaperService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n ..Camunda/UploadSciencePaperService");
		ArrayList<String> users = new ArrayList<String>();
		users.add("user1");
		users.add("user2");
		if(sciencePaperData.get("authorId").equals("") || sciencePaperData.get("magazineId").equals("") ||
				sciencePaperData.get("name").equals("") || sciencePaperData.get("keywords").equals("") ||
				sciencePaperData.get("abbstract").equals("")
				/* || sciencePaperData.get("coauthor").equals(""))*/
				)
		{
			execution.setVariable("validation", false);
		} else {
			execution.setVariable("validation", true);
			execution.setVariable("reviewers", users);
		}
	}


}
