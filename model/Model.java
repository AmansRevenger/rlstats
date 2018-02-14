package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ssplugins.rlstats.RLStats;
import com.ssplugins.rlstats.RLStatsAPI;
import com.ssplugins.rlstats.entities.Platform;
import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.PlaylistInfo;

public class Model {

	private static String API_KEY = "";
	private static RLStatsAPI api;
	private static String error;
	private static int errorCode;

	public Model() throws InterruptedException, ExecutionException, FileNotFoundException {

		String filename = "conf/apikey";

		Scanner scanner = new Scanner(new File(filename));
		API_KEY = scanner.useDelimiter("\\Z").next();
		scanner.close();
		System.out.println("creating model with apikey " + API_KEY);

		api = RLStats.getAPI(API_KEY);

		api.shutdownThreads();

	}

	public RLStatsAPI getAPI() {
		System.out.println("calling new request");
		api = RLStats.getAPI(API_KEY);
		return api;
	}

	public Player requestPlayerData(String steamID) {
		getAPI();
		Future<Player> future = api.getPlayer(steamID, Platform.STEAM);

		try {
			Player player = future.get();
			return player;
		} catch (InterruptedException | ExecutionException | IllegalStateException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = e.getMessage();
			System.out.println("Error incoming " + error);

		} finally {
			System.out.println("shutting down api in Model");
			api.shutdownThreads();
		}
		return null;
	}

	public String getErrorCode() {
		return error;

	}

}
