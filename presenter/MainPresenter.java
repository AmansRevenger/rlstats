package presenter;

import java.util.concurrent.TimeUnit;

import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.Stat;
import com.ssplugins.rlstats.entities.Stats;

import model.MatchResult;
import model.Model;
import model.PlaylistEnum;
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

		// Input bereinigen
		playername = playername.replaceAll("\t", "");
		playername = playername.replaceAll("\n", "");
		playername = playername.replaceAll(" ", "");

		System.out.println("Checking for " + playername);

		Player player = model.requestPlayerData(playername);

		return player;

	}

	public MatchResult startDaemon(Player cache, int season, PlaylistEnum playlist) {
		// Step 1. Get Data for cache and make a copy to work on

		System.out.println("Starting Daemon for Player " + cache.getDisplayName());

		System.out.println("This resolves to " + playlist);
		System.out.println("Intvalue: " + playlist.getIntValue());

		Player player = cache;

		int playermatches = player.getSeasonInfo(season).getPlaylistInfo(playlist.getIntValue()).getMatchesPlayed();
		int cachematches = cache.getSeasonInfo(season).getPlaylistInfo(playlist.getIntValue()).getMatchesPlayed();

		// Step 2. Check every 10 seconds for a new copy of concurrent Data,
		// check if a new Match was played and if yes, generate data
		//
		// TODO : own Thread class so main UI Thread doesnt freeze

		while (cachematches == playermatches) {
			try {

				System.err.println("Nothing to update, no relevant match played, going to sleep for 10");
				System.out.println("old matches: " + cachematches);
				System.out.println("new matches: " + playermatches);
				TimeUnit.SECONDS.sleep(10);
				player = request(player.getUniqueId());
				playermatches = player.getSeasonInfo(7).getPlaylistInfo(Playlist.RANKED_SOLO_STANDARD)
						.getMatchesPlayed();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return evaluateMatch(cache, player, playlist, season);

	}

	public MatchResult evaluateMatch(Player oldData, Player newData, PlaylistEnum playlist, int season) {
		// TODO Auto-generated method stub

		Stats oldStats = oldData.getStats();
		Stats newStats = newData.getStats();

		// initalize variables for the constructor
		String playerID = newData.getUniqueId();
		int goals = newStats.getStat(Stat.GOALS) - oldStats.getStat(Stat.GOALS);
		int assists = newStats.getStat(Stat.ASSISTS) - oldStats.getStat(Stat.ASSISTS);
		int saves = newStats.getStat(Stat.SAVES) - oldStats.getStat(Stat.SAVES);
		int shots = newStats.getStat(Stat.SHOTS) - oldStats.getStat(Stat.SHOTS);
		boolean mvp = false;
		if ((newStats.getStat(Stat.MVP) - oldStats.getStat(Stat.MVP)) > 1) {
			mvp = true;
		}
		boolean win = false;
		if ((newStats.getStat(Stat.WINS) - oldStats.getStat(Stat.WINS)) > 1) {
			win = true;
		}
		// int movement = 0; // TODO : how do i determine if it was a rank up? compare
		// to last rank. ENUM

		MatchResult result = new MatchResult(playerID, playlist, goals, assists, saves, shots, mvp, win);

		// MatchResult fake = new MatchResult("Scrublord", PlaylistEnum.CHAOS, 5, 2, 3,
		// 7, true, false); for testing

		return result; // change to fake for testing
	}
}
