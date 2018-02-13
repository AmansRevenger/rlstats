package view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import presenter.MainPresenter;

import com.ssplugins.rlstats.entities.Player;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@SuppressWarnings("restriction")
public class MainView extends GridPane {
    private MainPresenter mainPresenter;
    private Pane content;
    Button checkPlayerbtn, saveBtn;
    TextArea playername;
    Label label, result;
    
    public MainView(MainPresenter mainpresenter) {
        super();
        this.mainPresenter = mainpresenter;
        initView();
    }
    
    private void initView() {
        
        this.setMinHeight(300);
        this.setMinWidth(400);
        
        label = new Label("Enter SteamID");
        label.setId("label");
        this.add(label, 0, 0);
        
        result = new Label("");
        label.setId("result");
        this.add(result, 1, 1);
        
        playername = new TextArea("");
        playername.setMinWidth(200);
        playername.setMaxWidth(200);
        playername.setMaxHeight(50);
        playername.setId("playername");
        this.add(playername, 0, 1);
        
        checkPlayerbtn = new Button("Check Data");
        checkPlayerbtn.setOnAction(e -> {
            checkPlayer();
        });
        this.add(checkPlayerbtn, 0, 2);
        
        saveBtn = new Button("Save Data");
        checkPlayerbtn.setOnAction(e -> {
            checkPlayer();
        });
        this.add(saveBtn, 1, 2);
        
        // content = new Pane();
        // content.setPadding(new Insets(20));
        // this.add(content, 1, 0);
        
    }
    
    public void setResult(String text) {
        result.setText(text);
    }
    
    private void checkPlayer() {
        String inputString = playername.getText();
        if (inputString.isEmpty()) {
            setResult("Leerer String ist nicht erlaubt!");
        }
        
        else {
            // Input bereinigen
            inputString = inputString.replaceAll("\t", "");
            inputString = inputString.replaceAll("\n", "");
            inputString = inputString.replaceAll(" ", "");
            
            System.out.println("Checking for " + inputString);
            
            try {Player temp = mainPresenter.request(inputString);
            setResult(temp.getDisplayName());}
            catch (NullPointerException e) {}
            
            ;
        }
    }
    
}
