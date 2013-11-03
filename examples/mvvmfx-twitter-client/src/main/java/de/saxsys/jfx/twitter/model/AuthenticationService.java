package de.saxsys.jfx.twitter.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthenticationService {

	@Inject
	UserService userService;

	private User authenticatedUser;

	public AuthenticationService() {
		// TODO Auto-generated constructor stub
	}

	public User login(String username, String password) {
		List<User> users = userService.getUsers();
		for (User user : users) {
			if (user.getUserName().equals(username)
					&& user.getPassword().equals(password)) {
				authenticatedUser = user;
				return user;
			}
		}
		return null;
	}

	public User getAuthenticatedUser() {
		return authenticatedUser;
	}
}
