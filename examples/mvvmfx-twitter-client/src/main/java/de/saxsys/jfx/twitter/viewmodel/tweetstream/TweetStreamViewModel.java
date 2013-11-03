package de.saxsys.jfx.twitter.viewmodel.tweetstream;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;

import javax.inject.Inject;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.ModelToStringMapper;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.SelectableItemList;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.SelectableStringList;
import de.saxsys.jfx.twitter.model.Tweet;
import de.saxsys.jfx.twitter.model.TweetService;
import de.saxsys.jfx.twitter.model.UserService;

public class TweetStreamViewModel implements ViewModel {

	private UserService userService;

	private TweetService tweetService;

	private SelectableItemList<Tweet> displayedTweets;

	@Inject
	public TweetStreamViewModel(UserService userService,
			TweetService tweetService) {
		this.userService = userService;
		this.tweetService = tweetService;
		displayedTweets = new SelectableItemList<Tweet>(
				FXCollections.<Tweet> observableArrayList(),
				new ModelToStringMapper<Tweet>() {
					@Override
					public String toString(Tweet tweet) {
						return Integer.toString(tweet.getId());
					}
				});
	}

	public void setUserId(Integer userId) {
		ListProperty<Tweet> tweets;
		if (userId != null) {
			tweets = tweetService.tweetsForUserProperty(userService
					.getUserForId(userId));
		} else {
			tweets = tweetService.tweetsProperty();
		}
		displayedTweets.itemListProperty().set(tweets);
	}

	public SelectableStringList displayedTweetsProperty() {
		return displayedTweets;
	}

}
