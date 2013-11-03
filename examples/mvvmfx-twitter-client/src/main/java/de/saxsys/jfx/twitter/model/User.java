package de.saxsys.jfx.twitter.model;

import java.util.List;
import java.util.Random;

public class User {

	private int id;
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

	/**
	 * Gets the technical id.
	 * 
	 * @return technical id
	 */
	public int getId() {
		if (id == 0) {
			id = new Random().nextInt();
		}
		return id;
	}

}
