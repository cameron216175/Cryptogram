package src.test.java;
import org.junit.*;
import src.main.java.Game;
import src.main.java.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TrackCorrectGuessesTest {

    @Test
    public void Scenario1() {

        Player testplayer = new Player("player");
        Game game = new Game(testplayer, "1");

        game.enterLetter(game.getCryptogram(), game.getCryptogram().getPhrase().charAt(1), game.getCryptogram().getEncryptedPhrase()[1].substring(0,game.getCryptogram().getEncryptedPhrase()[1].length()-1));

        assertEquals(1, game.getPlayer().getCorrectGuesses());
    }

    @Test
    public void Scenario2() {

        Player testplayer = new Player("player");
        Game game = new Game(testplayer, "1");

        game.enterLetter(game.getCryptogram(), game.getCryptogram().getPhrase().charAt(1), game.getCryptogram().getEncryptedPhrase()[2].substring(0,game.getCryptogram().getEncryptedPhrase()[2].length()-1));

        assertEquals(0, game.getPlayer().getCorrectGuesses());

    }

    // Tests that accuracy is correctly calculated after a mix of correct and incorrect guesses
    @Test
    public void Scenario3() {
        Player testplayer = new Player("player");
        Game game = new Game(testplayer, "1");

        // Make one correct guess
        game.enterLetter(game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(1),
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1));

        // Make one incorrect guess using a different encrypted slot
        game.enterLetter(game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(2),
                game.getCryptogram().getEncryptedPhrase()[3].substring(0, game.getCryptogram().getEncryptedPhrase()[3].length() - 1));

        game.getPlayer().updateAccuracy();

        // 1 correct out of 2 total = 50%
        assertEquals(50.0, game.getPlayer().getAccuracy());
    }
}
