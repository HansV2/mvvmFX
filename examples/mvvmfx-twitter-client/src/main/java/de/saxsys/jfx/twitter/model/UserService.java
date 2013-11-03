package de.saxsys.jfx.twitter.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class UserService {

	private List<User> users = new ArrayList<>();

	public UserService() {
		users.add(new User("username", "password"));
		users.add(new User("sialcasa", "password"));
	}

	public List<User> getUsers() {
		return users;
	}
}
