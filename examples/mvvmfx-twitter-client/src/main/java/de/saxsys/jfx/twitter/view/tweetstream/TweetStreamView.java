package de.saxsys.jfx.twitter.view.tweetstream;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	private ListView<String> tweetsListView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Factory for create View for every element in tweetslist
		tweetsListView.setCellFactory(new ViewListCellFactory<String>() {
			@Override
			public ViewTuple<? extends ViewModel> map(String element) {
				ViewTuple<TweetViewModel> loadViewTuple = viewLoader
						.loadViewTuple(TweetView.class);
				loadViewTuple.getCodeBehind().getViewModel()
						.setTweetId(Integer.parseInt(element));
				return loadViewTuple;
			}
		});
		// Bind the lists
		tweetsListView.itemsProperty().bind(
				getViewModel().displayedTweetsProperty().stringListProperty());

		// Enable the selection mechanism
		tweetsListView.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> bean,
							Number old, Number selection) {
						tweetsListView.getSelectionModel().select(
								selection.intValue());
					}
				});
	}

}
