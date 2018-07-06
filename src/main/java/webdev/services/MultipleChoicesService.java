package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
 
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
	public MultipleChoiceExamQuestion createMultipleChoiceQuestion
	(@PathVariable("examId") int examId,@RequestBody MultipleChoiceExamQuestion newMultipleQuestion) {
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
	
	@GetMapping("/api/exam/{examId}/choice")
	public List<MultipleChoiceExamQuestion> findAllMultipleChoiceQuestionsForExam
	(@PathVariable("examId") int examId) {
		List<MultipleChoiceExamQuestion> newMultipleChoiceQuestion = new ArrayList<MultipleChoiceExamQuestion>();
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if (optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> baseQuestion = exam.getQuestions();
			for (BaseExamQuestion question : baseQuestion) {
				if (question instanceof MultipleChoiceExamQuestion) {
					MultipleChoiceExamQuestion MultipleChociequestion = (MultipleChoiceExamQuestion) question;
					if (!MultipleChociequestion.getTitle().equals("")) {
						newMultipleChoiceQuestion.add(MultipleChociequestion);
					}
				}
			}
			return newMultipleChoiceQuestion;
		}
		return null;
	}

	@DeleteMapping("/api/choice/{id}")
	public void deleteMultipleChoiceQuestionById(@PathVariable("id") int MultipleChoiceId) {
		MultipleChioicesRepo.deleteById(MultipleChoiceId);
	}

	@GetMapping("/api/choice")
	public List<MultipleChoiceExamQuestion> findAllMultipleChoiceQuestions() {
		return (List<MultipleChoiceExamQuestion>) MultipleChioicesRepo.findAll();
	}

	@GetMapping("/api/choice/{Id}")
	public MultipleChoiceExamQuestion findMultipleChoiceQuestionById(@PathVariable("Id") int MultipleChoiceId) {
		Optional<MultipleChoiceExamQuestion> data = MultipleChioicesRepo.findById(MultipleChoiceId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}



	@PutMapping("/api/choice/{Id}")
	public MultipleChoiceExamQuestion updateMultipleChoiceQuestion
	(@PathVariable("Id") int MultipleChoiceId,@RequestBody MultipleChoiceExamQuestion newMultipleChoiceQuestion) {
		MultipleChoiceExamQuestion MultipleChoiceQuestionfind = findMultipleChoiceQuestionById(MultipleChoiceId);
			 
			System.out.println("@@@@@@@@@@ mutltstart");
		
		if (MultipleChoiceQuestionfind != null) {
			MultipleChoiceQuestionfind.setTitle(newMultipleChoiceQuestion.getTitle());

			MultipleChoiceQuestionfind.setDescription(newMultipleChoiceQuestion.getDescription());

//			MultipleChoiceQuestionfind.setExam(newMultipleChoiceQuestion.getExam());

			MultipleChoiceQuestionfind.setId(newMultipleChoiceQuestion.getId());

			MultipleChoiceQuestionfind.setInstructions(newMultipleChoiceQuestion.getInstructions());

			MultipleChoiceQuestionfind.setPoints(newMultipleChoiceQuestion.getPoints());

			MultipleChoiceQuestionfind.setIcon(newMultipleChoiceQuestion.getIcon());
	 

			MultipleChoiceQuestionfind.setType(newMultipleChoiceQuestion.getType());
			

			MultipleChoiceQuestionfind.setCorrectOption(newMultipleChoiceQuestion.getCorrectOption());
			MultipleChoiceQuestionfind.setOptions(newMultipleChoiceQuestion.getOptions());
			
			MultipleChioicesRepo.save(MultipleChoiceQuestionfind);
			System.out.println("@@@@@@@@@@done");
			return MultipleChoiceQuestionfind;

		}
		return null;
	}
}
