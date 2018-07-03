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
	public FillInTheBlanksExamQuestion createFillInBlanksQuestion
	(@PathVariable("examId") int examId,@RequestBody FillInTheBlanksExamQuestion newFillBlankQuestion) {
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
	
	@GetMapping("/api/exam/{examId}/blanks")
	public List<FillInTheBlanksExamQuestion> findAllFillInTheBlanksQuestionsForExam
	(@PathVariable("examId") int examId) {
		List<FillInTheBlanksExamQuestion> newFillInTheBlanksquestion = new ArrayList<FillInTheBlanksExamQuestion>();
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if (optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> baseQuestion = exam.getQuestions();
			for (BaseExamQuestion question : baseQuestion) {
				if (question instanceof FillInTheBlanksExamQuestion) {
					FillInTheBlanksExamQuestion FillInTheBlankquestion = (FillInTheBlanksExamQuestion) question;
					if (!FillInTheBlankquestion.getTitle().equals("")) {
						newFillInTheBlanksquestion.add(FillInTheBlankquestion);
					}
				}
			}
			return newFillInTheBlanksquestion;
		}
		return null;
	}

	@DeleteMapping("/api/blanks/{id}")
	public void deleteFillInTheBlanksQuestionById(@PathVariable("id") int FillblankId) {
		FillInBlackRepo.deleteById(FillblankId);
	}

	@GetMapping("/api/blanks")
	public List<FillInTheBlanksExamQuestion> findAllFillInTheBlanksQuestions() {
		return (List<FillInTheBlanksExamQuestion>) FillInBlackRepo.findAll();
	}

	@GetMapping("/api/blanks/{Id}")
	public FillInTheBlanksExamQuestion findFillInTheBlanksQuestionById(@PathVariable("Id") int FillblankId) {
		Optional<FillInTheBlanksExamQuestion> data = FillInBlackRepo.findById(FillblankId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}



	@PutMapping("/api/blanks/{Id}")
	public FillInTheBlanksExamQuestion updateFillInTheBlanksQuestion(@PathVariable("Id") int FillblankId,
			@RequestBody FillInTheBlanksExamQuestion newFillInTheBlanksQuestion) {
		FillInTheBlanksExamQuestion FillBlankQuestionfind = findFillInTheBlanksQuestionById(FillblankId);
		if (FillBlankQuestionfind != null) {
			FillBlankQuestionfind.setTitle(newFillInTheBlanksQuestion.getTitle());

			FillBlankQuestionfind.setDescription(newFillInTheBlanksQuestion.getDescription());

//			FillBlankQuestionfind.setExam(newFillInTheBlanksQuestion.getExam());

			FillBlankQuestionfind.setId(newFillInTheBlanksQuestion.getId());

			FillBlankQuestionfind.setInstructions(newFillInTheBlanksQuestion.getInstructions());

			FillBlankQuestionfind.setPoints(newFillInTheBlanksQuestion.getPoints());

			FillBlankQuestionfind.setSubtitle(newFillInTheBlanksQuestion.getSubtitle());

			FillBlankQuestionfind.setType(newFillInTheBlanksQuestion.getType());
			

			FillBlankQuestionfind.setVariables(newFillInTheBlanksQuestion.getVariables());
			
			
			FillInBlackRepo.save(FillBlankQuestionfind);
			
			return FillBlankQuestionfind;

		}
		return null;
	}
}
