package src.main.java;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TrackCryptoPlayedTest {

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
