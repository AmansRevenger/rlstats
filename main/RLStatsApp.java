package main;

import com.ssplugins.rlstats.RLStats;
import com.ssplugins.rlstats.RLStatsAPI;
import com.ssplugins.rlstats.entities.Platform;
import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.PlaylistInfo;
import com.ssplugins.rlstats.entities.Stat;
import com.ssplugins.rlstats.entities.Stats;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import presenter.MainPresenter;
import view.MainView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
@SuppressWarnings("restriction")
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
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}