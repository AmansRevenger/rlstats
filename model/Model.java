package model;

import com.ssplugins.rlstats.RLStats;
import com.ssplugins.rlstats.RLStatsAPI;
import com.ssplugins.rlstats.entities.Platform;
import com.ssplugins.rlstats.entities.Player;
import com.ssplugins.rlstats.entities.Playlist;
import com.ssplugins.rlstats.entities.PlaylistInfo;
import com.ssplugins.rlstats.entities.Stat;
import com.ssplugins.rlstats.entities.Stats;

import view.MainView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Model {
    
    private final static String API_key = "B7IRDPL4OUPTVI9O39XI3R4QZ402ZZVF"; // Hardcoded because that's mine. 
    private static RLStatsAPI api;
    private static String error;
    
    public Model() throws InterruptedException, ExecutionException {

        // Only here so I dont get Nullpointers
        api = RLStats.getAPI(API_key);
       
        api.shutdownThreads();
       
    }
    
    public RLStatsAPI getAPI() {
        System.out.println("calling new request");
        api = RLStats.getAPI(API_key);
        return api;
    }
    
    public Player requestPlayerData(String steamID) {
        getAPI();
        
        Future<Player> future = api.getPlayer(steamID, Platform.STEAM);
        
        // ... Do something to wait until API request is finished.
        
        try {
            Player player = future.get();
            if (player == null) {
                error = "404 not found";
                return null;
            }
            return player;
        } catch (InterruptedException | ExecutionException | IllegalStateException | NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            error = e.getMessage();
            System.out.println("Error incoming " + error);
            
        } finally {
            System.out.println("shutting down api");
            api.shutdownThreads();
        }
        api.shutdownThreads();
        System.out.println("shutting down api");
        return null;
    }
    
    public boolean changed(Player player) {
        PlaylistInfo info = player.getSeasonInfo(7).getPlaylistInfo(Playlist.RANKED_SOLO_STANDARD);
        int matchesold = info.getMatchesPlayed();
        return true;
        
    }
    
    public String getErrorCode() {
        return error;
        
    }
    
}
