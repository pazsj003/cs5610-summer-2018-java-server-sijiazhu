package webdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoicesService {
	@Autowired
	MultipleChoicesQuestionRepository  MultipleChioicesRepo;
	
	@Autowired
	ExamRepository examRepository;
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createMultipleChoiceQuestion(@PathVariable("examId") int examId,@RequestBody MultipleChoiceExamQuestion newMultipleQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		System.out.println("yes I am in Mutil outer*******");
		if(data.isPresent()) {
			Exam exam = data.get();
			newMultipleQuestion.setExam(exam);
        System.out.println("yes I am in create EXan*******");
			return MultipleChioicesRepo.save(newMultipleQuestion);
		}
		return null;	 
	}
}
