package webdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksService {
	@Autowired
	FillInTheBlanksExamQuestionRepository FillInBlackRepo;
	@Autowired
	ExamRepository examRepository;
	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion createFillInBlanksQuestion(@PathVariable("examId") int examId,@RequestBody FillInTheBlanksExamQuestion newFillBlankQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		System.out.println("yes I am in FILL outer*******");
		if(data.isPresent()) {
			Exam exam = data.get();
			newFillBlankQuestion.setExam(exam);
        System.out.println("yes I am in create EXan*******");
			return FillInBlackRepo.save(newFillBlankQuestion);
		}
		return null;	 
	}
}
