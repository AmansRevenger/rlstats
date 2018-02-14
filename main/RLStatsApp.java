package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import presenter.MainPresenter;
import view.MainView;


public class RLStatsApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Model model = new Model();
		MainPresenter mainPresenter = new MainPresenter(model);
		MainView mainview = new MainView(mainPresenter);
		mainPresenter.setView(mainview);

		Scene scene = new Scene(mainview);
		primaryStage.setTitle("Rocket League Statistics Beta 0.0.1");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}