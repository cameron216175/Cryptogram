package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import static org.junit.jupiter.api.Assertions.*;

public class EnterLetterTest {

    // Scenario 1: Valid letter entry returns Incomplete (not yet finished)
    @Test
    public void Scenario1() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        String result = game.enterLetter(
                game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(1),
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1)
        );

        // One letter entered, cryptogram not complete
        assertEquals("Incomplete", result);
    }

    // Scenario 2: Entering a non-letter character returns Error
    @Test
    public void Scenario2() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        String result = game.enterLetter(
                game.getCryptogram(),
                '1',
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1)
        );

        assertEquals("Error", result);
    }

    // Scenario 3: Entering a letter that doesn't exist in the cryptogram returns Error
    @Test
    public void Scenario3Letters() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        // "ZZZZZ" is very unlikely to be an encrypted value
        String result = game.enterLetter(game.getCryptogram(), 'A', "ZZZZZ");

        assertEquals("Error", result);
    }

    @Test
    public void Scenario3Numbers() {
        Player p = new Player("test");
        Game game = new Game(p, "0");

        // 99 will never be a valid encrypted number (only 1-26 used)
        String result = game.enterLetter(game.getCryptogram(), 'A', "99");

        assertEquals("Error", result);
    }

    // Scenario 4: Total guesses increments after a valid entry
    @Test
    public void Scenario4() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        game.enterLetter(
                game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(1),
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1)
        );

        assertEquals(1, game.getPlayer().getTotalGuesses());
    }

    // Scenario 5: Same test but for numbers cryptogram
    @Test
    public void Scenario5Numbers() {
        Player p = new Player("test");
        Game game = new Game(p, "0");

        game.enterLetter(
                game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(1),
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1)
        );

        assertEquals(1, game.getPlayer().getTotalGuesses());
    }
}