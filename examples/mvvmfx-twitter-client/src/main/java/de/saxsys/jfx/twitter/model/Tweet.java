package de.saxsys.jfx.twitter.model;

import java.util.Date;
import java.util.Random;

public class Tweet {

	private int id;
	private String tweet;
	private User user;
	private Date date;

	public Tweet(String tweet, User user, Date date) {
		this.tweet = tweet;
		this.user = user;
		this.date = date;
	}

	public String getTweet() {
		return tweet;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
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
