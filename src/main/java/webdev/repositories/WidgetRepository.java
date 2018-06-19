package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.User;
import webdev.models.Widget;

public interface WidgetRepository
extends CrudRepository<Widget, Integer>{
}
