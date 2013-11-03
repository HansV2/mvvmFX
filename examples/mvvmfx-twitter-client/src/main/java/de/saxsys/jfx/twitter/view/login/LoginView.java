package de.saxsys.jfx.twitter.view.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.twitter.viewmodel.login.LoginViewModel;

public class LoginView extends View<LoginViewModel> {

	/**
	 * Sample Skeleton for "LoginView.fxml" Controller Class You can copy and
	 * paste this code into your favorite IDE
	 **/

	@FXML
	// fx:id="loginFailedLabel"
	private Label loginFailedLabel; // Value injected by FXMLLoader

	@FXML
	// fx:id="passwordField"
	private PasswordField passwordField; // Value injected by FXMLLoader

	@FXML
	// fx:id="usernameField"
	private TextField usernameField; // Value injected by FXMLLoader

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		loginFailedLabel.setOpacity(0.0);
	}

	@FXML
	// Handler for Button[Button[id=null, styleClass=button]] onAction
	public void loginButtonPressed(ActionEvent event) {
		boolean authenticated = getViewModel().checkLogin(
				usernameField.getText(), passwordField.getText());
		if (!authenticated && loginFailedLabel.getOpacity() == 0.0) {
			fadeLoginFailedLabel();
		}
	}

	private void fadeLoginFailedLabel() {
		FadeTransitionBuilder.create().duration(Duration.seconds(1.0))
				.cycleCount(2).autoReverse(true).toValue(1.0)
				.node(loginFailedLabel).build().play();
	}

}
