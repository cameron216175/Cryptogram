package src.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.java.Player;
import src.main.java.Players;

import java.io.IOException;

import static org.junit.Assert.*;

public class LoadDetailsTest {

    // Scenario 1: Returning player's stats are preserved after reloading from file
    @Test
    public void Scenario1() throws IOException {
        Players players = new Players();
        Player player = new Player("loadtest_user");
        players.removePlayer(player);

        player.incrementCryptogramsCompleted();
        player.incrementCryptogramsPlayed();
        player.incrementTotalGuesses();

        players.addPlayer(player);
        players.savePlayers();

        // Simulate a new session by creating a fresh Players object
        Players reloaded = new Players();
        Player loaded = reloaded.getPlayer("loadtest_user");

        Assertions.assertNotNull(loaded);
        Assertions.assertEquals(1, loaded.getNumCryptogramsCompleted());
        Assertions.assertEquals(1, loaded.getNumCryptogramsPlayed());
        Assertions.assertEquals(1, loaded.getTotalGuesses());

        // Cleanup
        players.removePlayer(player);
        players.savePlayers();
    }

    // Scenario 2: A new player who doesn't exist yet gets created fresh with zeroed stats
    @Test
    public void Scenario2() {
        Players players = new Players();
        Player newPlayer = new Player("brand_new_user");
        players.removePlayer(newPlayer);

        // Should not exist yet
        Assertions.assertNull(players.findPlayer(newPlayer));

        players.addPlayer(newPlayer);
        Player retrieved = players.getPlayer("brand_new_user");

        Assertions.assertNotNull(retrieved);
        Assertions.assertEquals(0, retrieved.getNumCryptogramsCompleted());
        Assertions.assertEquals(0, retrieved.getNumCryptogramsPlayed());
        Assertions.assertEquals(0, retrieved.getTotalGuesses());
        Assertions.assertEquals(0.0, retrieved.getAccuracy());

        // Cleanup
        players.removePlayer(newPlayer);
    }

    // Scenario 3: A returning player's username is correctly preserved after reload
    @Test
    public void Scenario3() throws IOException {
        Players players = new Players();
        Player player = new Player("returning_user");
        players.removePlayer(player);
        players.addPlayer(player);
        players.savePlayers();

        Players reloaded = new Players();
        Player loaded = reloaded.getPlayer("returning_user");

        Assertions.assertNotNull(loaded);
        Assertions.assertEquals("returning_user", loaded.getUsername());

        // Cleanup
        players.removePlayer(player);
        players.savePlayers();
    }
}
