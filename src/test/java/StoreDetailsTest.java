package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Player;
import src.main.java.Players;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class StoreDetailsTest {

    @Test
    public void Scenario1() throws IOException {
        //Create player and players objects
        Players players = new Players();
        Player player = new Player("John Doe");

        //Add player to list of players
        players.addPlayer(player);

        //Assert that player is successfully written to file
        assertTrue(players.savePlayers());

    }

}
