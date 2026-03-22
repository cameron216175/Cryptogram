package src.test.java;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TrackCryptoPlayedTest {

    @BeforeEach
    public void setUp() {
        new File("savegame_John Doe.ser").delete();
    }

    @Test
    public void Scenario1() {
        //Create player and game object
        Player player = new Player("John Doe");
        Game game = new Game(player, "1");

        //Assert number of cryptos played has incremented when new crypto was created
        assertEquals(1, game.getPlayer().getNumCryptogramsPlayed());
    }

    @Test
    public void Scenario2() {
        //Create player and game object (num of cryptos should be 1)
        Player player = new Player("John Doe");
        Game game = new Game(player, "1");

        //Save the game
        game.saveGame();

        //Load the game (num of cryptos should still be 1)
        game = Game.loadGame(player);

        //Assert number of cryptos played did not increment when game was loaded
        assertEquals(1, game.getPlayer().getNumCryptogramsPlayed());

    }



}
