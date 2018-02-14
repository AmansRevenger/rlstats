package view;

import java.util.List;

import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.Stat;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.MatchResult;
import model.PlaylistEnum;
import presenter.MainPresenter;

public class MainView extends GridPane {

	private MainPresenter mainPresenter;
	private VBox inputs, buttons;
	private GridPane results;
	private Button checkPlayerbtn, saveBtn, daemonBtn;
	private TextArea playernameTextArea;
	private ComboBox<Integer> seasonComboBox;
	private ComboBox<PlaylistEnum> playlistComboBox;
	// season and playlist change to load from config --> enum ? easier to maintain

	private TableView<MatchResult> resultTable = new TableView<MatchResult>();
	private TableColumn<MatchResult, String> nameCol = new TableColumn<>("Spieler");
	// private TableColumn<MatchResult, String> playlistCol = new
	// TableColumn<MatchResult, String>("Playlist");
	private TableColumn<MatchResult, Number> goalCol = new TableColumn<MatchResult, Number>("Goals");
	private TableColumn<MatchResult, Number> assistCol = new TableColumn<MatchResult, Number>("Assists");
	private TableColumn<MatchResult, Number> savesCol = new TableColumn<MatchResult, Number>("Saves");
	private TableColumn<MatchResult, Number> shotsCol = new TableColumn<MatchResult, Number>("Shots");
	private TableColumn<MatchResult, Boolean> winCol = new TableColumn<MatchResult, Boolean>("Win");
	private TableColumn<MatchResult, Boolean> mvpCOl = new TableColumn<MatchResult, Boolean>("MVP");

	private Label SteamIDLabel, seasonLabel, playlistLabel, resultLabel;

	public MainView(MainPresenter mainpresenter) {
		super();
		this.mainPresenter = mainpresenter;
		initView();
	}

	private void initView() {

		this.setMinHeight(300);
		this.setMinWidth(600);
		this.setPadding(new Insets(15));

		buttons = new VBox(10);
		buttons.setPadding(new Insets(10, 0, 0, 0));
		inputs = new VBox(10);
		results = new GridPane();
		results.setPadding(new Insets(5));

		resultTable.setId("resultTable");
		resultTable.setMinWidth(500);

		resultLabel = new Label("Last Match results");
		resultLabel.setId("resultLabel");
		results.add(resultLabel, 0, 0);

		// fill inputs Vbox with content
		initInputs();
		initButtons();
		initResultsTable();

		this.add(inputs, 0, 0);
		this.add(buttons, 0, 1);
		this.add(results, 1, 0);

		results.add(resultTable, 0, 1);

	}

	private void initResultsTable() {
		// TODO Auto-generated method stub
		nameCol.setId("nameCol");
		nameCol.setPrefWidth(300);
		nameCol.setCellValueFactory(item -> item.getValue().getIdProperty());
		resultTable.getColumns().add(nameCol);

		// playlistCol.setId("playlistCol");
		// playlistCol.setPrefWidth(100);
		// playlistCol.setCellValueFactory(item -> item.getValue().getPlaylistenum());
		// resultTable.getColumns().add(nameCol);

		goalCol.setId("goalCol");
		goalCol.setPrefWidth(60);
		goalCol.setCellValueFactory(item -> item.getValue().getGoalProperty());
		resultTable.getColumns().add(goalCol);

		assistCol.setId("assistCol");
		assistCol.setPrefWidth(60);
		assistCol.setCellValueFactory(item -> item.getValue().getAssistProperty());
		resultTable.getColumns().add(assistCol);

		savesCol.setId("savesCol");
		savesCol.setPrefWidth(60);
		savesCol.setCellValueFactory(item -> item.getValue().getSavesProperty());
		resultTable.getColumns().add(savesCol);

		shotsCol.setId("shotsCol");
		shotsCol.setPrefWidth(60);
		shotsCol.setCellValueFactory(item -> item.getValue().getShotsProperty());
		resultTable.getColumns().add(shotsCol);
	}

	private void initButtons() {
		// TODO Auto-generated method stub
		checkPlayerbtn = new Button("Check Data");
		checkPlayerbtn.setOnAction(e -> {
			validateInput();
			String inputString = playernameTextArea.getText();
			checkPlayer(inputString);
		});
		buttons.getChildren().add(checkPlayerbtn);

		saveBtn = new Button("Save to Database");
		saveBtn.setOnAction(e -> {
			// TODO
			validateInput();
		});
		buttons.getChildren().add(saveBtn);

		daemonBtn = new Button("Start Daemon");
		daemonBtn.setOnAction(e -> {
			validateInput();
			String inputString = playernameTextArea.getText();
			startDaemon(checkPlayer(inputString));
		});
		buttons.getChildren().add(daemonBtn);

	}

	private void validateInput() {
		// TODO Auto-generated method stub

	}

	private void initInputs() {
		// TODO Auto-generated method stub
		SteamIDLabel = new Label("Enter SteamID");
		SteamIDLabel.setId("label");
		inputs.getChildren().add(SteamIDLabel);

		playernameTextArea = new TextArea("amansrevenger");
		playernameTextArea.setMinWidth(200);
		playernameTextArea.setMaxWidth(200);
		playernameTextArea.setMaxHeight(50);
		playernameTextArea.setId("playername");
		inputs.getChildren().add(playernameTextArea);

		seasonLabel = new Label("Select a season");
		seasonLabel.setId("seasonLabel");
		inputs.getChildren().add(seasonLabel);

		seasonComboBox = new ComboBox<Integer>();
		seasonComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7); // Amount of Seasons
		seasonComboBox.getSelectionModel().select(seasonComboBox.getItems().size() - 1);
		inputs.getChildren().add(seasonComboBox);

		playlistLabel = new Label("Select a playlist");
		playlistLabel.setId("playlistLabel");
		inputs.getChildren().add(playlistLabel);

		playlistComboBox = new ComboBox<PlaylistEnum>();
		playlistComboBox.getItems().addAll(PlaylistEnum.values());
		playlistComboBox.getSelectionModel().select(PlaylistEnum.RANKED_SOLO_STANDARD);
		inputs.getChildren().add(playlistComboBox);
	}

	private void startDaemon(Player player) {

		if (player == null) {
			System.err.println("player is null");
			return;
		}

		PlaylistEnum playlist = playlistComboBox.getSelectionModel().getSelectedItem();
		int season = seasonComboBox.getSelectionModel().getSelectedItem();
		setResult(mainPresenter.startDaemon(player, season, playlist));

	}

	public void setResult(MatchResult result) {

		resultTable.getItems().add(result);

	}

	public Player checkPlayer(String inputString) {

		Player result = null;

		try {
			result = mainPresenter.request(inputString);

		} catch (NullPointerException e) {
		}
		return result;
	}
}
