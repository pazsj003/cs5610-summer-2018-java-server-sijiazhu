package webdev.services;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
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
			} else {
				throw new Exception("dont updateuser anything");
			}

		} else {
			throw new Exception("cant updateuser");
		}

	}

	@GetMapping("/api/profile")
	public User profile(HttpSession session) throws Exception {
		User currentUser = (User) session.getAttribute("user");

		return currentUser;

	}

	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session) throws Exception {
		User currentUser = (User) session.getAttribute("user");

		if (currentUser != null) {
			ArrayList<User> data = (ArrayList<User>) findUserByUsername(currentUser.getUsername());
			if (data != null) {
				for (User selectuser : data) {
					if (selectuser != null) {
						if (set(selectuser, user)) {
							repository.save(selectuser);
							return selectuser;
						}
						return null;
					}
				}
			} else {
				throw new Exception("cant updateuser");
			}

		}
		throw new Exception("current profile is invalid");

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
		if (CurrentUser.getFirstName() != updateedUser.getFirstName()) {
			CurrentUser.setFirstName(updateedUser.getFirstName());
			change = true;
		}
		if (CurrentUser.getLastName() != updateedUser.getLastName()) {
			CurrentUser.setLastName(updateedUser.getLastName());
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

		ArrayList<User> newUser = (ArrayList<User>) findUserByUsername(user.getUsername());
		for (User finduser : newUser) {
			if (finduser != null) {
				throw new Exception("same username found");
			}
		}
		createUser(user);
		session.setAttribute("user", user);

		return (User) session.getAttribute("user");

	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}

	@GetMapping("/api/user/")
	public Iterable<User> findUserByUsername(@RequestParam(name = "username", required = false) String username) {

		if (username != null) {
			return repository.findUserByUsername(username);
		}
		return repository.findAll();

	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) throws Exception {
		if (user.getUsername() != "" && user.getPassword() != "" && user.getFirstName() != ""
				&& user.getLastName() != "") {
			return repository.save(user);
		}
		throw new Exception("User dont have enough information to create account");
	}

	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session) throws Exception {

		ArrayList<User> currentUser = (ArrayList<User>) findUserByCredentials(user.getUsername(), user.getPassword());
		if (currentUser != null) {
			for (User selectuser : currentUser) {
				if (selectuser != null) {
					session.setAttribute("user", selectuser);
					return (User) session.getAttribute("user");
				}

			}
		}
		throw new Exception("cant Login");
	}

	public Iterable<User> findUserByCredentials(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) throws Exception {
		if (username != null && password != null) {
			return repository.findUserByCredentials(username, password);
		} else if (username != null) {
			throw new Exception("cant Login");
		}
		return repository.findAll();
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
