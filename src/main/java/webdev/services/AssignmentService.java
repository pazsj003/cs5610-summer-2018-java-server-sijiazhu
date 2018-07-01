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

import webdev.models.Assignment;

import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*",maxAge=3600)
public class AssignmentService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Assignment> findAllAssignmentForTopic(@PathVariable("topicId") int topicId) {
		List<Assignment> newAssignment=new ArrayList<Assignment>();
		Optional<Topic> optionalTopic = topicRepository.findById(topicId);
		if(optionalTopic.isPresent()) {
			Topic topic = optionalTopic.get();
			List<Widget>widgets= topic.getWidgets();
			for(Widget widget:widgets) {
				if(widget instanceof Assignment) {
					Assignment assignment  = (Assignment)widget;
					if(!assignment.getTitle().equals("")) {
						newAssignment.add(assignment);
					}
				}
			}
			return newAssignment;
		}
		return null;
	}
	

	
	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment createAssignment(@PathVariable("topicId") int topicId,@RequestBody Assignment newAssignment) {
		Optional<Topic> data = topicRepository.findById(topicId);
		 
		if(data.isPresent()) {
			Topic topic = data.get();
			newAssignment.setTopic(topic);
        
			return assignmentRepository.save(newAssignment);
		}
		return null;	 
	}
	
	@DeleteMapping("/api/assignment/{id}")
	public void deleteAssignmentId(@PathVariable("id")int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	@GetMapping("/api/Assignment/{Id}")
	public Assignment findAssignmentById(@PathVariable("Id") int assignmentId) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
//	@PostMapping("/api/assignment/{assignmentId}/")
//	public void saveAssignment(@PathVariable("topicId") int topicId, @RequestBody List<Assignment> newAssignments) {
//		Optional<Topic> data = topicRepository.findById(topicId);
//		if(data.isPresent()) {
//			Topic topic = data.get();
//			List<Assignment> assignments=findAllAssignmentForTopic(topicId);
//			for(Assignment assign : assignments) {
//				deleteAssignmentId(assign.getId());
//			}
//			for (Assignment assign : newAssignments) {
//				assign.setTopic(topic);
//			System.out.println("assignID is ***"+assign.getId()+"assigns text ******"+assign.getText());
//			assignmentRepository.save(assign);
//			}
//		} 
//	}
	
	@PutMapping("/api/assignment/{Id}")
	public Assignment updateAssignment(@PathVariable("Id") int assignmentId,@RequestBody Assignment newAssign) {
		Assignment assignmentfind=findAssignmentById(assignmentId);
		if(assignmentfind!=null) {
			assignmentfind.setClassName(newAssign.getClassName());
			assignmentfind.setHeight(newAssign.getHeight());
			assignmentfind.setHrefLink(newAssign.getHrefLink());
			assignmentfind.setListItems(newAssign.getListItems());
			assignmentfind.setListType(newAssign.getListType());
			assignmentfind.setName(newAssign.getName());
			assignmentfind.setorderList(newAssign.getorderList());
			assignmentfind.setSize(newAssign.getSize());
			assignmentfind.setSrc(newAssign.getSrc());
			assignmentfind.setStyle(newAssign.getStyle());
			assignmentfind.setText(newAssign.getText());
			assignmentfind.setTopic(newAssign.getTopic());
			assignmentfind.setWidgetType(newAssign.getWidgetType());
			assignmentfind.setWidth(newAssign.getWidth());
			
			assignmentfind.setTitle(newAssign.getTitle());
			
			assignmentfind.setTopic(newAssign.getTopic());
			
			assignmentfind.setDescription(	newAssign.getDescription());
		
			
			
			return assignmentfind;
			
		}
		return null;
	}
	
	
	
	
	
}
