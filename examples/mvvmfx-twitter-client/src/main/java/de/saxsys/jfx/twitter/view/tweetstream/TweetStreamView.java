package de.saxsys.jfx.twitter.view.tweetstream;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javax.inject.Inject;

import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.base.view.util.viewlist.ViewListCellFactory;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;
import de.saxsys.jfx.twitter.view.tweet.TweetView;
import de.saxsys.jfx.twitter.viewmodel.tweet.TweetViewModel;
import de.saxsys.jfx.twitter.viewmodel.tweetstream.TweetStreamViewModel;

public class TweetStreamView extends View<TweetStreamViewModel> {

	@Inject
	private ViewLoader viewLoader;

	@FXML
	private ListView<Integer> tweetsListView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tweetsListView.setCellFactory(new ViewListCellFactory<Integer>() {
			@Override
			public ViewTuple<? extends ViewModel> map(Integer element) {
				ViewTuple<TweetViewModel> loadViewTuple = viewLoader
						.loadViewTuple(TweetView.class);
				loadViewTuple.getCodeBehind().getViewModel()
						.setTweetId(element);
				return loadViewTuple;
			}
		});
		tweetsListView.itemsProperty().bind(
				getViewModel().displayedTweetsProperty());
	}

}
