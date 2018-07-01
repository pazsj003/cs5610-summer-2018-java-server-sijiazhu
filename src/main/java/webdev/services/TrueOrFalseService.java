package webdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueOrFalseService {
	@Autowired
	TrueFalseQuestionRepository TrueFlaseRepo;
	
	@Autowired
	ExamRepository examRepository;
	@PostMapping("/api/exam/{examId}/tryefalse")
	public TrueOrFalseExamQuestion createTrueOrFalseQuestion(@PathVariable("examId") int examId,@RequestBody TrueOrFalseExamQuestion newTrueFlaseQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		System.out.println("yes I am in Mutil outer*******");
		if(data.isPresent()) {
			Exam exam = data.get();
			newTrueFlaseQuestion.setExam(exam);
        System.out.println("yes I am in create EXan*******");
			return TrueFlaseRepo.save(newTrueFlaseQuestion);
		}
		return null;	 
	}
}
