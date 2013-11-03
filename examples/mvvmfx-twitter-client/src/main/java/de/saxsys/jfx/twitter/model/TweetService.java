package de.saxsys.jfx.twitter.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TweetService {

	private ListProperty<Tweet> tweets = new SimpleListProperty<>(
			FXCollections.<Tweet> observableArrayList());

	@Inject
	public TweetService(UserService userService) {
		tweets.add(new Tweet("LOL1", userService.getUsers().get(0), new Date()));
		tweets.add(new Tweet("LOL2", userService.getUsers().get(1), new Date()));
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public ListProperty<Tweet> tweetsProperty() {
		return tweets;
	}

	public List<Tweet> getTweetsForUser(User user) {
		List<Tweet> userTweets = new ArrayList<>();
		for (Tweet tweet : tweets) {
			if (tweet.getUser().equals(user)) {
				userTweets.add(tweet);
			}
		}
		return userTweets;
	}

	public ListProperty<Tweet> tweetsForUserProperty(User user) {
		ListProperty<Tweet> userTweets = new SimpleListProperty<>(
				FXCollections.<Tweet> observableArrayList());
		for (Tweet tweet : tweets) {
			if (tweet.getUser().equals(user)) {
				userTweets.add(tweet);
			}
		}
		return userTweets;
	}

	public Tweet getTweetForId(int element) {
		for (Tweet tweet : tweets) {
			if (tweet.getId() == element) {
				return tweet;
			}
		}
		return null;
	}
}
