package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Player;
import src.main.java.Players;

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
        players.addPlayer(player);

    }
}
