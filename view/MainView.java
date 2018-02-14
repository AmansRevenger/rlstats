package view;

import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.Stat;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import presenter.MainPresenter;

public class MainView extends GridPane {
	private MainPresenter mainPresenter;
	private Pane content;
	Button checkPlayerbtn, saveBtn, daemonBtn;
	TextArea playername;
	Label label, label2, resultName, resultMatches, resultGoals;

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

		label2 = new Label("Last Match results");
		label2.setId("label2");
		this.add(label2, 1, 0);

		resultName = new Label("");
		resultName.setId("resultName");
		this.add(resultName, 1, 1);

		resultMatches = new Label("");
		resultMatches.setId("resultMatches");
		this.add(resultMatches, 1, 2);

		resultGoals = new Label("");
		resultGoals.setId("resultGoals");
		this.add(resultGoals, 1, 3);

		playername = new TextArea("");
		playername.setMinWidth(200);
		playername.setMaxWidth(200);
		playername.setMaxHeight(50);
		playername.setId("playername");
		this.add(playername, 0, 1);

		checkPlayerbtn = new Button("Check Data");
		checkPlayerbtn.setOnAction(e -> {
			String inputString = playername.getText();
			checkPlayer(inputString);
		});
		this.add(checkPlayerbtn, 0, 2);

		saveBtn = new Button("Save Data");
		checkPlayerbtn.setOnAction(e -> {
			String inputString = playername.getText();
			checkPlayer(inputString);
		});
		this.add(saveBtn, 0, 3);

		daemonBtn = new Button("Start Daemon");
		daemonBtn.setOnAction(e -> {
			String inputString = playername.getText();
			startDaemon(checkPlayer(inputString));
		});
		this.add(daemonBtn, 0, 4);

		// content = new Pane();
		// content.setPadding(new Insets(20));
		// this.add(content, 1, 0);

	}

	private void startDaemon(Player player) {
		mainPresenter.startDaemon(player);

	}

	public void setResult(Player player) {

		// right now labels only for feedback, later a nice ListView probably
		String playername = player.getDisplayName();
		int playergoals = player.getStats().getStat(Stat.GOALS);
		int playermatches = player.getSeasonInfo(7).getPlaylistInfo(Playlist.RANKED_SOLO_STANDARD).getMatchesPlayed();

		resultName.setText(playername);
		resultGoals.setText("Overall Goals: " + playergoals);
		resultMatches.setText("matches in Ranked Solo Standard in Season 7 : " + playermatches);

	}

	public Player checkPlayer(String inputString) {

		try {
			Player temp = mainPresenter.request(inputString);
			setResult(temp);
			return temp;
		} catch (NullPointerException e) {
		}
		return null;
	}
}
