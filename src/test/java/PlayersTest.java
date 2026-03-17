package src.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Player;
import src.main.java.Players;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

class PlayersTest {
    private Player player;
    private Players players ;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        player = new Player("conor");
        players = new Players();
    }

    @Test
    void testAddPlayers() {
        players.removePlayer(player);
        Assertions.assertTrue(players.addPlayer(player));
        Assertions.assertFalse(players.addPlayer(player));
    }

    @Test
    void testSavePlayers() throws IOException {
        players.removePlayer(player);
        for (int i = 0; i < 20; i++) {
            player.incrementCryptogramsCompleted();
        }
        players.addPlayer(player);
        Assertions.assertTrue(players.savePlayers());
    }

    @Test
    void testRemovePlayer() {
        players.addPlayer(player);
        Assertions.assertTrue(players.removePlayer(player));
        Assertions.assertFalse(players.removePlayer(player));
    }

    @Test
    void testFindPlayer() {
        players.addPlayer(player);
        Assertions.assertEquals("conor", players.findPlayer(player));
        players.removePlayer(player);
        Assertions.assertNull(players.findPlayer(player));
    }

    @Test
    void testUpdatePlayer() {
        players.addPlayer(player);
        Assertions.assertTrue(players.updatePlayer(player));
        players.removePlayer(player);
        Assertions.assertFalse(players.updatePlayer(player));
    }

    @Test
    void testReadPlayers() throws IOException {
        Player player2 = new Player("testinguser");
        players.addPlayer(player2);
        players.savePlayers();
        Players readPlayers = new Players();
        Assertions.assertEquals("testinguser", readPlayers.findPlayer(player2));
        players.removePlayer(player2);
        players.savePlayers();
    }

    @Test
    void testGetPlayer() {
        players.addPlayer(player);
        Assertions.assertEquals(player.getUsername(), players.getPlayer("conor").getUsername());
    }

    @Test
    void testGetAllPlayersAccuracies() {
        Player player2 = new Player("test", 3, 3, 4, 5.5489, 4);
        players.removePlayer(player2);
        players.addPlayer(player2);
        Assertions.assertEquals(5.5489, players.getAllPlayersAccuracies().getLast());

    }

    @Test
    void testGetAllPlayersCryptogramsPlayed() {
        Player player2 = new Player("test", 3, 3, 4, 5.5489, 5);
        players.removePlayer(player2);
        players.addPlayer(player2);
        Assertions.assertEquals(4, players.getAllPlayersCryptogramsPlayed().getLast());

    }

    @Test
    void testGetAllPlayersCompletedCryptos() {
        Player player2 = new Player("test", 3, 3, 4, 5.5489, 4);
        players.removePlayer(player2);
        players.addPlayer(player2);
        Assertions.assertEquals(3, players.getAllPlayersCompletedCryptos().getLast());

    }
}
