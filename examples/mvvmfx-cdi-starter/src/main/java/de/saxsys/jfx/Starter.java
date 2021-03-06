package de.saxsys.jfx;

import de.saxsys.jfx.exampleapplication.view.maincontainer.MainContainerView;
import de.saxsys.jfx.exampleapplication.viewmodel.maincontainer.MainContainerViewModel;
import de.saxsys.jfx.mvvm.cdi.MvvmfxCdiApplication;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

/**
 * The application entry point.
 * 
 * @author manuel.mauky
 *
 */
public class Starter extends MvvmfxCdiApplication{
	
	public static void main(String...args){
        launch(args);
	}

    @Inject
    private ViewLoader viewLoader;

    @Override
    public void start(Stage stage){
        final ViewTuple<MainContainerViewModel> tuple = viewLoader
                .loadViewTuple(MainContainerView.class);

        Parent view = tuple.getView();

        final Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    }
}
