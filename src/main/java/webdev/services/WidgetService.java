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

import webdev.models.Lesson;
import webdev.models.Module;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",maxAge=10000)

public class WidgetService {
 
	@Autowired
	WidgetRepository repository;
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> optionalTopic = topicRepository.findById(topicId);
		if(optionalTopic.isPresent()) {
			Topic topic = optionalTopic.get();
			return topic.getWidgets();
		}
		return null;
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) repository.findAll();
	}

	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
		repository.deleteAll();
		for (Widget widget : widgets) {
			repository.save(widget);
		}
	}
	@GetMapping("/api/widget/{Id}")
	public Widget findWidgetById(@PathVariable("Id") int widgetId) {
		Optional<Widget> data = repository.findById(widgetId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	@PostMapping("/api/topic/{topicId}/widget")
	public void saveWidgets(@PathVariable("topicId") int topicId, @RequestBody Widget[] newWidgets) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgets=findAllWidgetsForTopic(topicId);
			for(Widget widget : widgets) {
				deleteWidegtId(widget.getId());
			}
			for (Widget widget : newWidgets) {
				widget.setTopic(topic);
				repository.save(widget);
			}
		} 
	}
	@PutMapping("/api/widget/{Id}")
	public Widget updateWidget(@PathVariable("Id") int widgetId,@RequestBody Widget newWidget) {
		Widget widgetfind=findWidgetById(widgetId);
		if(widgetfind!=null) {
			widgetfind.setClassName(newWidget.getClassName());
			widgetfind.setHeight(newWidget.getHeight());
			widgetfind.setHrefLink(newWidget.getHrefLink());
			widgetfind.setListItems(newWidget.getListItems());
			widgetfind.setListType(newWidget.getListType());
			widgetfind.setName(newWidget.getName());
			widgetfind.setorderList(newWidget.getorderList());
			widgetfind.setSize(newWidget.getSize());
			widgetfind.setSrc(newWidget.getSrc());
			widgetfind.setStyle(newWidget.getStyle());
			widgetfind.setText(newWidget.getText());
			widgetfind.setTopic(newWidget.getTopic());
			widgetfind.setWidgetType(newWidget.getWidgetType());
			widgetfind.setWidth(newWidget.getWidth());
			
			return widgetfind;
			
		}
		return null;
	}
	
	@DeleteMapping("/api/widget/{id}")
	public void deleteWidegtId(@PathVariable("id")int widgetId) {
		repository.deleteById(widgetId);
	}
}
