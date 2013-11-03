package de.saxsys.jfx.twitter.viewmodel.tweetstream;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

import javax.inject.Inject;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.twitter.model.AuthenticationService;
import de.saxsys.jfx.twitter.model.Tweet;
import de.saxsys.jfx.twitter.model.TweetService;
import de.saxsys.jfx.twitter.model.User;

public class TweetStreamViewModel implements ViewModel {

	private AuthenticationService authService;

	private TweetService tweetService;

	private ListProperty<Integer> displayedTweets = new SimpleListProperty<>(
			FXCollections.<Integer> observableArrayList());

	@Inject
	public TweetStreamViewModel(AuthenticationService authService,
			TweetService tweetService) {
		this.authService = authService;
		this.tweetService = tweetService;
		initTweetList();
	}

	private void initTweetList() {
		ListProperty<Tweet> tweetsForUserProperty = tweetService
				.tweetsForUserProperty(authService.getAuthenticatedUser());
		mapLists();
		tweetsForUserProperty.addListener(new ListChangeListener<Tweet>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Tweet> event) {
				while (event.next()) {
					mapLists();
				}
			}
		});
	}

	private void mapLists() {
		displayedTweets.clear();
		User authenticatedUser = authService.getAuthenticatedUser();
		ListProperty<Tweet> tweetsForUserProperty = tweetService
				.tweetsForUserProperty(authenticatedUser);

		for (Tweet tweet : tweetsForUserProperty) {
			displayedTweets.add(tweet.getId());
		}
	}

	public ListProperty<Integer> displayedTweetsProperty() {
		return displayedTweets;
	}

}
