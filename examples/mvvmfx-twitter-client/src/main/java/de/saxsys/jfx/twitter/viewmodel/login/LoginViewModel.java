package de.saxsys.jfx.twitter.viewmodel.login;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.inject.Inject;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.twitter.model.AuthenticationService;
import de.saxsys.jfx.twitter.model.User;

public class LoginViewModel implements ViewModel {

	@Inject
	private AuthenticationService authService;

	private ObjectProperty<User> authenticatedUser = new SimpleObjectProperty<>();

	public boolean checkLogin(String username, String password) {
		User loggedUser = authService.login(username, password);
		authenticatedUser.set(loggedUser);
		return loggedUser == null ? false : true;
	}

	public ObjectProperty<User> authenticatedUserProperty() {
		return authenticatedUser;
	}

}
