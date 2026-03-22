package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import static org.junit.jupiter.api.Assertions.*;

public class UndoLetterTest {

    // Scenario 1: Undoing a guessed letter resets it to "-"
    @Test
    public void Scenario1() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        // Enter a letter first
        game.enterLetter(
                game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(1),
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1)
        );

        // Now undo it using the encrypted value
        String encryptedVal = game.getCryptogram().getEncryptedPhrase()[1];
        game.undoLetter(game.getCryptogram(), encryptedVal);

        // Slot should be reset
        assertEquals("- ", game.getPlayerGameMapping().get(1));
    }

    // Scenario 2: Undoing when nothing has been guessed does nothing harmful
    @Test
    public void Scenario2() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        // No guess made, undo should not crash
        String encryptedVal = game.getCryptogram().getEncryptedPhrase()[1];
        game.undoLetter(game.getCryptogram(), encryptedVal);

        // Slot should still be default
        assertEquals("- ", game.getPlayerGameMapping().get(1));
    }

    // Scenario 3: Undoing a letter that was never guessed does nothing
    @Test
    public void Scenario3() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        // Enter one letter
        game.enterLetter(
                game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(1),
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1)
        );

        // Try to undo a completely different slot that was never guessed
        game.undoLetter(game.getCryptogram(), game.getCryptogram().getEncryptedPhrase()[2]);

        // The original guess should be unaffected
        assertNotEquals("- ", game.getPlayerGameMapping().get(1));
    }

    // Scenario 4: Same basic undo works for numbers cryptogram
    @Test
    public void Scenario4Numbers() {
        Player p = new Player("test");
        Game game = new Game(p, "0");

        game.enterLetter(
                game.getCryptogram(),
                game.getCryptogram().getPhrase().charAt(1),
                game.getCryptogram().getEncryptedPhrase()[1].substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1)
        );

        String encryptedVal = game.getCryptogram().getEncryptedPhrase()[1];
        game.undoLetter(game.getCryptogram(), encryptedVal);

        assertEquals("- ", game.getPlayerGameMapping().get(1));
    }
}