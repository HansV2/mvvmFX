package de.saxsys.jfx.twitter;

import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.inject.Inject;

import com.google.inject.Module;

import de.saxsys.jfx.mvvm.di.guice.MvvmGuiceApplication;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;
import de.saxsys.jfx.twitter.view.main.MainView;
import de.saxsys.jfx.twitter.viewmodel.main.MainViewModel;
import de.saxsys.jfx.twitter.viewmodel.main.MainViewModel.ApplicationState;

public class Starter extends MvvmGuiceApplication {

	@Inject
	private ViewLoader viewLoader;

	@Override
	public void initGuiceModules(List<Module> modules) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.initStyle(StageStyle.UTILITY);
		final ViewTuple<MainViewModel> loadViewTuple = viewLoader
				.loadViewTuple(MainView.class);
		Scene scene = new Scene(loadViewTuple.getView());
		stage.setScene(scene);
		stage.show();
		loadViewTuple.getCodeBehind().getViewModel().applicationStateProperty()
				.set(ApplicationState.LOGIN);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
