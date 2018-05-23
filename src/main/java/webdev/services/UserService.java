package webdev.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController

public class UserService {
	@Autowired
	UserRepository repository;

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) throws Exception {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			User user = data.get();
			if (set(user, newUser)) {
				repository.save(user);
				return user;
			}
			return null;
		}
		else {
			throw new Exception("cant updateuser");
		}
		

	}

	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session) throws Exception {
		User currentUser = (User) session.getAttribute("user");

		if (currentUser != null) {
			Optional<User> data = repository.findUserByUsername(currentUser.getUsername());
			if (data.isPresent()) {
				User onlineuser = data.get();
				if (set(onlineuser, user)) {
					repository.save(onlineuser);
					return user;
				}
				return null;
			}
			else {
				throw new Exception("cant updateuser");
			}
			
		}else {
			throw new Exception("cant updateprofile");
		}
		
	}

	public boolean set(User CurrentUser, User updateedUser) {
		boolean change = false;
		if (CurrentUser.getUsername() != updateedUser.getUsername()) {
			CurrentUser.setUsername(updateedUser.getUsername());
			change = true;
		}
		if (CurrentUser.getRole() != updateedUser.getRole()) {
			CurrentUser.setRole(updateedUser.getRole());
			change = true;
		}
		if (CurrentUser.getPhone() != updateedUser.getPhone()) {
			CurrentUser.setPhone(updateedUser.getPhone());
			change = true;
		}
		if (CurrentUser.getEmail() != updateedUser.getEmail()) {
			CurrentUser.setEmail(updateedUser.getEmail());
			change = true;
		}
		if (CurrentUser.getDateOfBirth() != updateedUser.getDateOfBirth()) {
			CurrentUser.setDateOfBirth(updateedUser.getDateOfBirth());
			change = true;
		}
		return change;
	}

	@PostMapping("/api/register")

	public User register(@RequestBody User user, HttpSession session) throws Exception {

		User newUser = findUserByUsername(user.getUsername());

		if (newUser != null) {

			throw new Exception("cant register");

		} else {

			createUser(user);
			session.setAttribute("user", user);

			return (User) session.getAttribute("user");
		}

	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}

	@GetMapping("/api/user/{username}")
	public User findUserByUsername(@PathVariable("username") String userName) {
		Optional<User> data = repository.findUserByUsername(userName);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}

	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session) throws Exception {

		List<User> currentUser = (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
		if (currentUser.get(0) == null) {
			throw new Exception("cant Login");
		}
		session.setAttribute("user", user);
		return (User) session.getAttribute("user");

	}

	@PostMapping("/api/logout")
	public User logout(HttpSession session) {
		session.invalidate();
       return null;
	}

	@GetMapping("/api/user")
	public List<User> findAllUser() {
		return (List<User>) repository.findAll();
	}
}
