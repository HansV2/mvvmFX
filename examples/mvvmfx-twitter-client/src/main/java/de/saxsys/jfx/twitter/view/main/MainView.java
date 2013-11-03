package de.saxsys.jfx.twitter.view.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.StackPane;

import javax.inject.Inject;

import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;
import de.saxsys.jfx.twitter.view.login.LoginView;
import de.saxsys.jfx.twitter.view.tweetstream.TweetStreamView;
import de.saxsys.jfx.twitter.viewmodel.login.LoginViewModel;
import de.saxsys.jfx.twitter.viewmodel.main.MainViewModel;
import de.saxsys.jfx.twitter.viewmodel.main.MainViewModel.ApplicationState;
import de.saxsys.jfx.twitter.viewmodel.tweetstream.TweetStreamViewModel;

public class MainView extends View<MainViewModel> {

	@Inject
	private ViewLoader viewLoader;

	@FXML
	private MenuBar menuBar;

	@FXML
	private ToolBar toolBar;

	@FXML
	// fx:id="contentStackPane"
	private StackPane contentStackPane; // Value injected by FXMLLoader

	@FXML
	// Handler for MenuItem[javafx.scene.control.MenuItem@22131fde] onAction
	public void aboutButtonPressed(ActionEvent event) {
		// handle the event here
	}

	@FXML
	// Handler for MenuItem[javafx.scene.control.MenuItem@1ce7fbb5] onAction
	public void applicationClosedButtonPressed(ActionEvent event) {
		// handle the event here
	}

	@FXML
	// Handler for MenuItem[javafx.scene.control.MenuItem@2f823052] onAction
	public void profileButtonPressed(ActionEvent event) {
		getViewModel().applicationStateProperty().set(
				ApplicationState.USERTWEETS);
	}

	@FXML
	// Handler for MenuItem[javafx.scene.control.MenuItem@608db0fd] onAction
	public void statisticsButtonPressed(ActionEvent event) {

	}

	@FXML
	// Handler for MenuItem[javafx.scene.control.MenuItem@608db0fd] onAction
	public void streamButtonPressed(ActionEvent event) {
		getViewModel().applicationStateProperty().set(ApplicationState.STREAM);
	}

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		// Disable bars when login
		BooleanBinding disableBinding = getViewModel()
				.applicationStateProperty().isEqualTo(ApplicationState.LOGIN);
		menuBar.disableProperty().bind(disableBinding);
		toolBar.disableProperty().bind(disableBinding);

		// Application Window
		getViewModel().applicationStateProperty().addListener(
				new ChangeListener<ApplicationState>() {
					@Override
					public void changed(
							ObservableValue<? extends ApplicationState> bean,
							ApplicationState oldState, ApplicationState newState) {
						checkApplicationState(newState);
					}
				});
	}

	private void checkApplicationState(ApplicationState newState) {
		contentStackPane.getChildren().clear();
		switch (newState) {
		case LOGIN:
			navigateToLoginView();
			break;
		case STREAM:
			navigateToStream();
			break;
		case USERSTATS:
			navigateToStats();
			break;
		case USERTWEETS:
			navigateToUserTweets();
			break;
		default:
			break;
		}
	}

	private void navigateToUserTweets() {
		// TODO Auto-generated method stub

	}

	private void navigateToStats() {
		// TODO Auto-generated method stub

	}

	private void navigateToStream() {
		ViewTuple<TweetStreamViewModel> loadViewTuple = viewLoader
				.loadViewTuple(TweetStreamView.class);
		contentStackPane.getChildren().add(loadViewTuple.getView());
	}

	private void navigateToLoginView() {
		ViewTuple<LoginViewModel> loadViewTuple = viewLoader
				.loadViewTuple(LoginView.class);
		contentStackPane.getChildren().add(loadViewTuple.getView());
		getViewModel().setLoginViewModeL(
				loadViewTuple.getCodeBehind().getViewModel());
	}
}
