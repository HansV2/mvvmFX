package de.saxsys.jfx.twitter.view.tweet;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.twitter.viewmodel.tweet.TweetViewModel;

public class TweetView extends View<TweetViewModel> {

	@FXML
	// fx:id="passwordLabel"
	private Label tweetLabel; // Value injected by FXMLLoader

	@FXML
	// fx:id="userNameLabel"
	private Label userNameLabel; // Value injected by FXMLLoader

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		tweetLabel.textProperty().bind(getViewModel().tweetProperty());
		userNameLabel.textProperty().bind(getViewModel().userNameProperty());
	}

}
