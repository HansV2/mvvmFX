package de.saxsys.jfx.twitter.model;

import java.util.List;

public class User {

	private String userName;
	private String password;

	public User(String userName, String password, List<Tweet> tweets) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
