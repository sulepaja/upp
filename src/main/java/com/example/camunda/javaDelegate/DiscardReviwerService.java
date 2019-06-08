package com.example.camunda.javaDelegate;

import com.example.model.Comment;
import com.example.model.SciencePaper;
import com.example.service.CommentService;
import com.example.service.SciencePaperService;
import com.example.service.StorageService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class DiscardReviwerService implements JavaDelegate {
	//DiscardSciencePaperService

	@Autowired
	SciencePaperService sciencePaperService;

	@Autowired
	CommentService commentService;

	@Autowired
	StorageService storageService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> sciencePaperData = (HashMap<String, Object>) execution.getVariable("sciencePaperData");
		System.out.println("\n DiscardReviwerService... ");
		String id = sciencePaperData.get("commentId").toString();
		if(!id.equals("0")){
			SciencePaper paper = sciencePaperService.findById(Long.parseLong(id));
			Comment comment  = commentService.findById(Long.parseLong(id));
			this.commentService.delete(comment.getId());


			System.out.println("Slanje mejla za odbijanje");
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo("camundaMailTester@gmail.com");
			mail.setFrom(env.getProperty("spring.mail.username"));
			mail.setSubject("UPP Discard reviwer ");


			String msg = "Discard reviwer";

			mail.setText(msg);
			javaMailSender.send(mail);
		}else{
			System.out.println("Greska. Ne postoji komentar!");
		}


	}

}
