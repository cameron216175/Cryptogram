package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Cryptogram;
import src.main.java.Game;
import src.main.java.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackCorrectGuessesTest {

    @Test
    void Scenraio1(){
        Player player = new Player("testName");

        assertEquals(0, player.getCorrectGuesses());
        assertEquals(0, player.getTotalGuesses());

        Game game = new Game(player, "1");

        Cryptogram cryptogram = game.generateCryptogram("1");

        game.enterLetter(cryptogram, cryptogram.getPhrase().charAt(0), cryptogram.getEncryptedPhrase()[0]);

        assertEquals(1, player.getCorrectGuesses());
        assertEquals(1, player.getTotalGuesses());

    }

}
