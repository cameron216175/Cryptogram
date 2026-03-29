package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HintTest {

    // ── Scenario 1: A hint actually places a correct letter ───────────────────

    // After calling getHint(), at least one slot that was "- " should now
    // contain the correct plain letter from the phrase.
    @Test
    public void Scenario1HintPlacesACorrectLetter() {
        Player p = new Player("hinttest1");
        Game game = new Game(p, "1");

        game.getHint();

        String phrase = game.getCryptogram().getPhrase();
        ArrayList<String> mapping = game.getPlayerGameMapping();

        boolean foundCorrectHint = false;
        for (int i = 0; i < phrase.length(); i++) {
            char phraseChar = phrase.charAt(i);
            // Skip spaces — they are never filled by a hint
            if (!Character.isLetter(phraseChar)) continue;

            String mapped = mapping.get(i);
            if (!mapped.equals("- ")) {
                char placedChar = Character.toUpperCase(mapped.charAt(0));
                char expected   = Character.toUpperCase(phraseChar);
                if (placedChar == expected) {
                    foundCorrectHint = true;
                    break;
                }
            }
        }

        assertTrue(foundCorrectHint,
                "After a hint, at least one position should contain the correct plain letter");
    }

    // Scenario 2: Every filled position after a hint matches the correct phrase character.
    // Only checks letter positions — spaces in the phrase map to " " slots which are
    // never touched by getHint().
    @Test
    public void Scenario2HintFillsOnlyCorrectLetters() {
        Player p = new Player("hinttest2");
        Game game = new Game(p, "1");

        game.getHint();

        String phrase = game.getCryptogram().getPhrase();
        ArrayList<String> mapping = game.getPlayerGameMapping();

        for (int i = 0; i < phrase.length(); i++) {
            char phraseChar = phrase.charAt(i);
            // Only check letter positions — non-letter slots are not filled by hints
            if (!Character.isLetter(phraseChar)) continue;

            String mapped = mapping.get(i);
            if (!mapped.equals("- ")) {
                char placedChar = Character.toUpperCase(mapped.charAt(0));
                char expected   = Character.toUpperCase(phraseChar);
                assertEquals(expected, placedChar,
                        "Hint-placed letter at position " + i + " should match phrase character '" + phraseChar + "'");
            }
        }
    }

    // Scenario 3: Hint increments totalGuesses by 1
    @Test
    public void Scenario3HintIncrementsTotalGuesses() {
        Player p = new Player("hinttest3");
        Game game = new Game(p, "1");

        int before = game.getPlayer().getTotalGuesses();
        game.getHint();
        int after = game.getPlayer().getTotalGuesses();

        assertEquals(before + 1, after, "getHint() should increment totalGuesses by 1");
    }

    // Scenario 4: Hint increments correctGuesses by 1
    @Test
    public void Scenario4HintIncrementsCorrectGuesses() {
        Player p = new Player("hinttest4");
        Game game = new Game(p, "1");

        int before = game.getPlayer().getCorrectGuesses();
        game.getHint();
        int after = game.getPlayer().getCorrectGuesses();

        assertEquals(before + 1, after, "getHint() should increment correctGuesses by 1");
    }

    // Scenario 5: Mapping list size does not change after a hint
    @Test
    public void Scenario5MappingSizeUnchangedAfterHint() {
        Player p = new Player("hinttest5");
        Game game = new Game(p, "1");

        int before = game.getPlayerGameMapping().size();
        game.getHint();
        int after = game.getPlayerGameMapping().size();

        assertEquals(before, after, "getHint() should not change the size of playerGameMapping");
    }

    // Scenario 6: getHint works for a numbers cryptogram too
    @Test
    public void Scenario6HintWorksForNumbersCryptogram() {
        Player p = new Player("hinttest6");
        Game game = new Game(p, "0");

        game.getHint();

        String phrase = game.getCryptogram().getPhrase();
        ArrayList<String> mapping = game.getPlayerGameMapping();

        boolean foundCorrectHint = false;
        for (int i = 0; i < phrase.length(); i++) {
            char phraseChar = phrase.charAt(i);
            if (!Character.isLetter(phraseChar)) continue;

            String mapped = mapping.get(i);
            if (!mapped.equals("- ")) {
                char placedChar = Character.toUpperCase(mapped.charAt(0));
                char expected   = Character.toUpperCase(phraseChar);
                if (placedChar == expected) {
                    foundCorrectHint = true;
                    break;
                }
            }
        }

        assertTrue(foundCorrectHint,
                "After a hint on a numbers cryptogram, at least one position should be correct");
    }

    // ── Scenario 7: Hint does NOT reveal the last remaining letter ────────────
    @Test
    public void Scenario7HintDoesNotRevealLastRemainingLetter() {
        Player p = new Player("hinttest7");
        Game game = new Game(p, "1");

        String phrase = game.getCryptogram().getPhrase();
        String[] encrypted = game.getCryptogram().getEncryptedPhrase();

        // Collect unique plain letters only (no spaces/punctuation)
        java.util.LinkedHashSet<Character> uniqueLetters = new java.util.LinkedHashSet<>();
        for (char c : phrase.toCharArray()) {
            if (Character.isLetter(c)) uniqueLetters.add(Character.toUpperCase(c));
        }

        // Need at least 2 unique letters for this test to be meaningful
        if (uniqueLetters.size() <= 1) return;

        // Enter all unique letters except the last one
        java.util.Iterator<Character> iter = uniqueLetters.iterator();
        Character lastLetter = null;
        while (iter.hasNext()) {
            Character letter = iter.next();
            if (!iter.hasNext()) {
                lastLetter = letter;
                break;
            }
            // Find the encrypted value for this plain letter and enter it
            for (int i = 0; i < phrase.length(); i++) {
                if (Character.toUpperCase(phrase.charAt(i)) == letter) {
                    String encVal = encrypted[i].substring(0, encrypted[i].length() - 1);
                    game.enterLetter(game.getCryptogram(), letter, encVal);
                    break;
                }
            }
        }

        // Only one letter remains — hint should refuse to reveal it
        game.getHint();

        final Character finalLastLetter = lastLetter;
        ArrayList<String> mapping = game.getPlayerGameMapping();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toUpperCase(phrase.charAt(i)) == finalLastLetter) {
                assertEquals("- ", mapping.get(i),
                        "The last remaining letter '" + finalLastLetter + "' should not be revealed by a hint");
            }
        }
    }

    // ── Scenario 8: Hint clears a previously incorrect manual guess ───────────
    // We place the wrong letter into every position of one specific plain letter,
    // then keep calling getHint() until it happens to pick that letter.
    // When it does, the wrong guess should be wiped and the correct one placed.
    // This scenario is deterministic in that we verify the final state is always
    // consistent — every filled slot matches the phrase — regardless of which
    // letter the hint picks on each call.
    @Test
    public void Scenario8HintOverwritesIncorrectGuess() {
        Player p = new Player("hinttest8");
        Game game = new Game(p, "1");

        String phrase = game.getCryptogram().getPhrase();
        ArrayList<String> mapping = game.getPlayerGameMapping();

        // Find the first plain letter (not a space)
        char targetLetter = 0;
        for (char c : phrase.toCharArray()) {
            if (Character.isLetter(c)) { targetLetter = Character.toUpperCase(c); break; }
        }
        assertNotEquals(0, targetLetter, "Should find at least one letter in the phrase");

        // Place the wrong letter in every slot belonging to targetLetter
        char wrongLetter = (targetLetter == 'A') ? 'B' : 'A';
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toUpperCase(phrase.charAt(i)) == targetLetter) {
                mapping.set(i, wrongLetter + " ");
            }
        }
        game.setPlayerGameMapping(mapping);

        // Call getHint() repeatedly until it picks targetLetter (random selection).
        // Each time it picks something else, reset the wrong guess so targetLetter
        // remains in the "unguessed" pool for next time.
        // Cap at 200 attempts to keep the test from running forever on a bad RNG run.
        final int MAX_ATTEMPTS = 200;
        boolean hintCorrectedTarget = false;

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            game.getHint();

            // Check if all targetLetter positions are now correct
            ArrayList<String> current = game.getPlayerGameMapping();
            boolean allTargetCorrect = true;
            for (int i = 0; i < phrase.length(); i++) {
                if (Character.toUpperCase(phrase.charAt(i)) == targetLetter) {
                    char placed = Character.toUpperCase(current.get(i).charAt(0));
                    if (placed != targetLetter) {
                        allTargetCorrect = false;
                        break;
                    }
                }
            }

            if (allTargetCorrect) {
                hintCorrectedTarget = true;
                break;
            }

            // Hint picked a different letter — reset wrong guess for next attempt
            for (int i = 0; i < phrase.length(); i++) {
                if (Character.toUpperCase(phrase.charAt(i)) == targetLetter) {
                    current.set(i, wrongLetter + " ");
                }
            }
            game.setPlayerGameMapping(current);
        }

        assertTrue(hintCorrectedTarget,
                "Within " + MAX_ATTEMPTS + " hint calls, the hint should have picked '" + targetLetter +
                        "' and corrected the wrong guess. If this is flaky, check getHint() random selection.");

        // Final check: every filled slot anywhere in the mapping must be correct
        ArrayList<String> finalMapping = game.getPlayerGameMapping();
        for (int i = 0; i < phrase.length(); i++) {
            char phraseChar = phrase.charAt(i);
            if (!Character.isLetter(phraseChar)) continue;
            String mapped = finalMapping.get(i);
            if (!mapped.equals("- ")) {
                char placed  = Character.toUpperCase(mapped.charAt(0));
                char expected = Character.toUpperCase(phraseChar);
                assertEquals(expected, placed,
                        "After hint corrects an incorrect guess, all filled slots should match the phrase");
            }
        }
    }

    // Scenario 9: getHint with all positions already filled does not throw
    @Test
    public void Scenario9HintWhenCompleteDoesNotThrow() {
        Player p = new Player("hinttest9");
        Game game = new Game(p, "1");

        // Fill every letter position with the correct answer directly
        String phrase = game.getCryptogram().getPhrase();
        ArrayList<String> mapping = game.getPlayerGameMapping();
        for (int i = 0; i < phrase.length(); i++) {
            char c = phrase.charAt(i);
            if (Character.isLetter(c)) {
                mapping.set(i, Character.toUpperCase(c) + " ");
            }
        }
        game.setPlayerGameMapping(mapping);

        // Should not throw — should print "No more hints were found"
        assertDoesNotThrow(game::getHint,
                "getHint() should handle the all-complete state gracefully without throwing");
    }
}