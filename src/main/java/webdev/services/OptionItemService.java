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
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.models.OptionItem;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;
import webdev.repositories.OptionItemRepositry;
import webdev.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OptionItemService {
	
	@Autowired
	MultipleChoicesQuestionRepository  MultipleChioicesRepo;
	
	@Autowired
	OptionItemRepositry OptionItemRepo;
	
	@PostMapping("/api/choice/{choiceId}/option")
	public OptionItem createOptionforMultipleChoiceQuestion
	(@PathVariable("choiceId") int choiceId,@RequestBody OptionItem newOptionItem) {
		Optional<MultipleChoiceExamQuestion> data = MultipleChioicesRepo.findById(choiceId);
		System.out.println("yes I am in Mutil outer*******");
		if(data.isPresent()) {
			MultipleChoiceExamQuestion question = data.get();
			newOptionItem.setMultiplequestion(question);
        System.out.println("yes I am in create EXan*******");
			return OptionItemRepo.save(newOptionItem);
		}
		return null;	 
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	@GetMapping("/api/choice/{choiceId}/option")
	public List<OptionItem> findAllOptionItemForMultipleChoice
	(@PathVariable("choiceId") int choiceId) { 
		Optional<MultipleChoiceExamQuestion> optionalMultiple = MultipleChioicesRepo.findById(choiceId);
		if (optionalMultiple.isPresent()) {
			MultipleChoiceExamQuestion MultipleChoice = optionalMultiple.get();
			List<OptionItem> OptionItem = MultipleChoice.getOptions();
			return OptionItem;
		}
		return null;
	}
	

	
	@DeleteMapping("/api/option/{id}")
	public void deleteOptionItemById(@PathVariable("id") int OptionId) {
		OptionItemRepo.deleteById(OptionId);
	}

	@GetMapping("/api/option")
	public List<OptionItem> findAllOptionItem() {
		return (List<OptionItem>) OptionItemRepo.findAll();
	}

	@GetMapping("/api/option/{Id}")
	public OptionItem findOptionItemById(@PathVariable("Id") int OptionId) {
		Optional<OptionItem> data = OptionItemRepo.findById(OptionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
	@PutMapping("/api/option/{Id}")
	public OptionItem updateOptionItem
	(@PathVariable("Id") int OptionId,@RequestBody OptionItem newOptionItem) {
		OptionItem OptionItemfind = findOptionItemById(OptionId);
			 	
		if (OptionItemfind != null) {
			OptionItemfind.setName(newOptionItem.getName());		
			OptionItemfind.setMultiplequestion(newOptionItem.getMultiplequestion());
						
			OptionItemRepo.save(OptionItemfind);
			System.out.println("@@@@@@@@@@done");
			return OptionItemfind;

		}
		return null;
	}
	
}
