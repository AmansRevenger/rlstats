package presenter;

import com.ssplugins.rlstats.entities.Player;

import model.Model;
import view.MainView;

public class MainPresenter {
    private MainView mainview;
    private Model model;
    
    public MainPresenter(Model model) {
        this.model = model;
    }
    
    public void setView(MainView mainview) {
        this.mainview = mainview;
    }
    
    public Player request(String playername) {
        Player player = model.requestPlayerData(playername);
        System.out.println("Request has been clicked");
        System.out.println();
        if (player == null) {
            mainview.setResult(model.getErrorCode());
        }
        return player;
        
    }
}
