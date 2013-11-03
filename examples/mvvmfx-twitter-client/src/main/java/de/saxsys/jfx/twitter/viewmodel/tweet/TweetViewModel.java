package de.saxsys.jfx.twitter.viewmodel.tweet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Inject;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.twitter.model.TweetService;

public class TweetViewModel implements ViewModel {

	@Inject
	private TweetService tweetService;

	private StringProperty userName = new SimpleStringProperty();
	private StringProperty tweet = new SimpleStringProperty();;

	public void setTweetId(int element) {
		tweetService.getTweetForId(element);
		userName.set(tweetService.getTweetForId(element).getUser()
				.getUserName());
		tweet.set(tweetService.getTweetForId(element).getTweet());
	}

	public StringProperty userNameProperty() {
		return userName;
	}

	public StringProperty tweetProperty() {
		return userName;
	}
}
