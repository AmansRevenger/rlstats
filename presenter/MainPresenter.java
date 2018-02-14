package presenter;

import java.util.concurrent.TimeUnit;

import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.Stat;

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

		// Input bereinigen
		playername = playername.replaceAll("\t", "");
		playername = playername.replaceAll("\n", "");
		playername = playername.replaceAll(" ", "");

		System.out.println("Checking for " + playername);

		Player player = model.requestPlayerData(playername);


		return player;

	}

	public void startDaemon(Player cache) {
		// Step 1. Get Data for cache and make a copy to work on
		
		System.out.println("Starting Daemon for Player " + cache.getDisplayName());
		
		mainview.setResult(cache);
		
		Player player = cache;
		int cachegoals = cache.getStats().getStat(Stat.GOALS);
		int playergoals = player.getStats().getStat(Stat.GOALS);
		int cachematches = cache.getSeasonInfo(7).getPlaylistInfo(Playlist.RANKED_SOLO_STANDARD).getMatchesPlayed();
		System.out.println("cachematches: " + cachematches);
		int playermatches = player.getSeasonInfo(7).getPlaylistInfo(Playlist.RANKED_SOLO_STANDARD).getMatchesPlayed();
		System.out.println("playermatches: " + playermatches);
		
		// Step 2. Check every 10 seconds for a new copy of concurrent Data
		
		while (cachematches == playermatches) 
		{
			try {
				System.err.println("Nothing to update, no relevant match played, going to sleep for 10");
				
				System.out.println("cached matches: " + cachematches);
				System.out.println("cached Goals: " + cachegoals);
				System.out.println("current matches: " + playermatches);
				System.out.println("current Goals: " + playergoals);
				
				
				TimeUnit.SECONDS.sleep(10);
				player = request(player.getUniqueId());
				playermatches = player.getSeasonInfo(7).getPlaylistInfo(Playlist.RANKED_SOLO_STANDARD)
						.getMatchesPlayed();
				playergoals = player.getStats().getStat(Stat.GOALS);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.err.println("Change Detected");
		System.out.println();
		int goals = player.getStats().getStat(Stat.GOALS) - cache.getStats().getStat(Stat.GOALS);
		System.out.println("Last game the player scored " + goals + " goals!");
		mainview.setResult(player);

	}
}
