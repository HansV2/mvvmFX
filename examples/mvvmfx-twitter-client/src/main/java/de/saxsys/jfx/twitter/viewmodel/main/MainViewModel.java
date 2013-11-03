package de.saxsys.jfx.twitter.viewmodel.main;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.twitter.model.User;
import de.saxsys.jfx.twitter.viewmodel.login.LoginViewModel;

public class MainViewModel implements ViewModel {

	public enum ApplicationState {
		LOGIN, STREAM, USERTWEETS, USERSTATS
	}

	private ObjectProperty<ApplicationState> applicationState = new SimpleObjectProperty<>();

	public ObjectProperty<ApplicationState> applicationStateProperty() {
		return applicationState;
	}

	public void setLoginViewModeL(LoginViewModel viewModel) {
		viewModel.authenticatedUserProperty().addListener(
				new ChangeListener<User>() {
					@Override
					public void changed(ObservableValue<? extends User> bean,
							User oldUser, User newUser) {
						if (newUser != null) {
							applicationState.set(ApplicationState.STREAM);
						}
					}
				});
	}
}
