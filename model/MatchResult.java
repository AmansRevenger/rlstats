package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MatchResult {

	private PlaylistEnum playlistenum;
	private String playerID;
	private int goal, assist, saves, shots;
	private boolean mvp, win;
	// private int movement;
	private SimpleStringProperty idProperty;
	private SimpleIntegerProperty goalProperty, assistProperty, savesProperty, shotsProperty;
	private SimpleBooleanProperty mvpProperty, winProperty;

	public MatchResult(String playerID, PlaylistEnum playlistenum, int goal, int assist, int saves, int shots,
			boolean mvp, boolean win) {
		super();
		this.playerID = playerID;
		this.playlistenum = playlistenum;
		this.goal = goal;
		this.assist = assist;
		this.saves = saves;
		this.shots = shots;
		this.mvp = mvp;
		this.win = win;

		idProperty = new SimpleStringProperty(playerID);
		goalProperty = new SimpleIntegerProperty(goal);
		assistProperty = new SimpleIntegerProperty(assist);
		savesProperty = new SimpleIntegerProperty(saves);
		shotsProperty = new SimpleIntegerProperty(shots);
		mvpProperty = new SimpleBooleanProperty(mvp);
		winProperty = new SimpleBooleanProperty(win);

		// this.movement = movement;
	}

	public PlaylistEnum getPlaylistenum() {
		return playlistenum;
	}

	public String getPlayerID() {
		return playerID;
	}

	public int getGoal() {
		return goal;
	}

	public int getAssist() {
		return assist;
	}

	public int getSaves() {
		return saves;
	}

	public int getShots() {
		return shots;
	}

	public boolean isMvp() {
		return mvp;
	}

	public boolean isWin() {
		return win;
	}

	public SimpleStringProperty getIdProperty() {
		return idProperty;
	}

	public SimpleIntegerProperty getGoalProperty() {
		return goalProperty;
	}

	public SimpleIntegerProperty getAssistProperty() {
		return assistProperty;
	}

	public SimpleIntegerProperty getSavesProperty() {
		return savesProperty;
	}

	public SimpleIntegerProperty getShotsProperty() {
		return shotsProperty;
	}

	public SimpleBooleanProperty getMvpProperty() {
		return mvpProperty;
	}

	public SimpleBooleanProperty getWinProperty() {
		return winProperty;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
