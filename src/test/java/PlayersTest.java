package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Player;
import src.main.java.Players;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class PlayersTest {
    private Player player;
    private Players players ;

    @BeforeEach
    void setUp() {
        player = new Player("john doe");
        players = new Players();
    }

    @Test
    void testAddPlayers() {
        assertEquals(true, players.addPlayer(player));
    }

    @Test
    void testSavePlayers() throws IOException {
        player.incrementCryptogramsCompleted();
        assertTrue(players.savePlayers(player));
    }
}
