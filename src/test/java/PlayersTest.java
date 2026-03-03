package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Player;
import src.main.java.Players;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class PlayersTest {
    private Player player;
    private Players players ;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        player = new Player("john doe");
        players = new Players();
        players.clearPlayers();
    }

    @Test
    void testAddPlayers() {
        assertEquals(true, players.addPlayer(player));
        assertEquals(false, players.addPlayer(player));
    }

    @Test
    void testSavePlayers() throws IOException {
        for (int i = 0; i < 20; i++) {
            player.incrementCryptogramsCompleted();
        }
        players.addPlayer(player);
        assertTrue(players.savePlayers(player));
    }
}
