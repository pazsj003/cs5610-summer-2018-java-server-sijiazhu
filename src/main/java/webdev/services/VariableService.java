package webdev.services;

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

import webdev.models.FillInTheBlanksExamQuestion;
import webdev.models.Lesson;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.models.OptionItem;
import webdev.models.Topic;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.models.Variable;
import webdev.repositories.CourseRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;
import webdev.repositories.OptionItemRepositry;
import webdev.repositories.TopicRepository;
import webdev.repositories.TrueFalseQuestionRepository;
import webdev.repositories.VariableRepository;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class VariableService {
	
	@Autowired
	FillInTheBlanksExamQuestionRepository FillInBlackRepo;
	
	@Autowired
	VariableRepository OptionItemRepo;
	
	
	
	@PostMapping("/api/blanks/{blanksId}/variable")
	public Variable createVariableforFillBlank
	(@PathVariable("blanksId") int blanksId,@RequestBody Variable newVariableItem) {
		Optional<FillInTheBlanksExamQuestion> data = FillInBlackRepo.findById(blanksId);
		System.out.println("yes I am in Mutil outer*******");
		if(data.isPresent()) {
			FillInTheBlanksExamQuestion question = data.get();
			newVariableItem.setFillquestion(question);
        System.out.println("yes I am in create EXan*******");
			return OptionItemRepo.save(newVariableItem);
		}
		return null;	 
	}
	

	
	
	
	@GetMapping("/api/blanks/{blanksId}/variable")
	public List<Variable> findAllVariableItemForFillBlank
	(@PathVariable("blanksId") int blanksId) { 
		Optional<FillInTheBlanksExamQuestion> optionalMultiple = FillInBlackRepo.findById(blanksId);
		if (optionalMultiple.isPresent()) {
			FillInTheBlanksExamQuestion FillBlank = optionalMultiple.get();
			List<Variable> OptionItem = FillBlank.getVariable();
			return OptionItem;
		}
		return null;
	}

	
	
	@DeleteMapping("/api/variable/{id}")
	public void deleteVariableItemById(@PathVariable("id") int OptionId) {
		OptionItemRepo.deleteById(OptionId);
	}

	@GetMapping("/api/variable")
	public List<Variable> findAllVariableItem() {
		return (List<Variable>) OptionItemRepo.findAll();
	}

	@GetMapping("/api/variable/{Id}")
	public Variable findVariableItemById(@PathVariable("Id") int OptionId) {
		Optional<Variable> data = OptionItemRepo.findById(OptionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
	@PutMapping("/api/variable/{Id}")
	public Variable updateVariableItem
	(@PathVariable("Id") int OptionId,@RequestBody Variable newOptionItem) {
		Variable OptionItemfind = findVariableItemById(OptionId);
			 
		
		if (OptionItemfind != null) {
			OptionItemfind.setName(newOptionItem.getName());
			 
			OptionItemfind.setFillquestion(newOptionItem.getFillquestion());
			 
			OptionItemRepo.save(OptionItemfind);
			System.out.println("@@@@@@@@@@done");
			return OptionItemfind;

		}
		return null;
	}
}
