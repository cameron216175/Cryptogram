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
}
