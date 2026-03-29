package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EnterLetterTest {

    private String findAbsentEncryptedValue(Game game, String cryptoType) {
        if (cryptoType.equals("0")) {
            return "99"; // always invalid for number cryptograms
        }
        // Collect all encrypted letter values actually in this cryptogram
        Set<Character> used = new HashSet<>();
        for (String slot : game.getCryptogram().getEncryptedPhrase()) {
            if (slot != null && !slot.trim().isEmpty()) {
                used.add(Character.toUpperCase(slot.trim().charAt(0)));
            }
        }
        // Find a letter not in the cryptogram
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!used.contains(c)) {
                return String.valueOf(c);
            }
        }
        // All 26 letters are used — extremely unlikely for a 30-40 char phrase,
        // but fall back to a multi-char string that can never match a single-char slot
        return "ABSENT";
    }

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

    @Test
    public void Scenario3Letters() {
        Player p = new Player("test");
        Game game = new Game(p, "1");

        String absentValue = findAbsentEncryptedValue(game, "1");
        String result = game.enterLetter(game.getCryptogram(), 'A', absentValue);

        assertEquals("Error", result,
                "Encrypted value '" + absentValue + "' is not in the cryptogram, should return Error");
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