package src.test.java;

import org.junit.*;
import src.main.java.Game;
import src.main.java.Player;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * These tests depend on checkGuess() being fixed in Game.java.
 *
 * THE BUG in the original checkGuess():
 *   It checks if the plain letter appears anywhere in the phrase, not whether
 *   the letter is correct for the specific encrypted value that was entered.
 *   e.g. mapping 'A' to an encrypted slot that should be 'B' still counts as
 *   correct if 'A' appears anywhere else in the phrase.
 *
 * THE FIX needed in Game.java — change checkGuess signature to:
 *   public void checkGuess(Cryptogram cryptogram, char letter, String encryptedGuess)
 *   Then check: does phrase[i] == letter at the positions where encrypted[i] == encryptedGuess?
 *   Update the two call sites in enterLetter to pass the encrypted value through.
 *
 * Until that fix is applied these tests will fail on Scenario2 and Scenario3.
 */
public class TrackCorrectGuessesTest {

    // Helper: find first position in the phrase that is a letter
    private int firstLetterPos(Game game) {
        String phrase = game.getCryptogram().getPhrase();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i))) return i;
        }
        return -1;
    }

    // Helper: find a second unique letter position that is different from the first
    private int secondDistinctLetterPos(Game game, int firstPos) {
        String phrase = game.getCryptogram().getPhrase();
        char firstLetter = Character.toUpperCase(phrase.charAt(firstPos));
        for (int i = firstPos + 1; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i)) &&
                    Character.toUpperCase(phrase.charAt(i)) != firstLetter) {
                return i;
            }
        }
        return -1;
    }

    // Helper: find a plain letter that does NOT appear anywhere in the phrase
    private char findLetterAbsentFromPhrase(Game game) {
        Set<Character> phraseLetters = new HashSet<>();
        for (char c : game.getCryptogram().getPhrase().toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) phraseLetters.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!phraseLetters.contains(c)) return c;
        }
        return 0; // all 26 used — extremely unlikely
    }

    // Scenario 1: Entering the correct plain letter for an encrypted value
    // increments correctGuesses by 1.
    @Test
    public void Scenario1CorrectGuessIncrementsCount() {
        Player testplayer = new Player("player");
        Game game = new Game(testplayer, "1");

        int pos = firstLetterPos(game);
        char correctLetter = Character.toUpperCase(game.getCryptogram().getPhrase().charAt(pos));
        String encVal = game.getCryptogram().getEncryptedPhrase()[pos]
                .substring(0, game.getCryptogram().getEncryptedPhrase()[pos].length() - 1);

        game.enterLetter(game.getCryptogram(), correctLetter, encVal);

        assertEquals(1, game.getPlayer().getCorrectGuesses());
    }

    // Scenario 2: Entering the WRONG plain letter for an encrypted value
    // does NOT increment correctGuesses.
    // Uses a letter provably absent from the phrase to guarantee it is wrong.
    @Test
    public void Scenario2IncorrectGuessDoesNotIncrementCount() {
        Player testplayer = new Player("player");
        Game game = new Game(testplayer, "1");

        int pos = firstLetterPos(game);
        String encVal = game.getCryptogram().getEncryptedPhrase()[pos]
                .substring(0, game.getCryptogram().getEncryptedPhrase()[pos].length() - 1);

        // A letter absent from the phrase is definitively wrong for every position
        char wrongLetter = findLetterAbsentFromPhrase(game);
        if (wrongLetter == 0) return; // all 26 letters used — can't run this scenario

        game.enterLetter(game.getCryptogram(), wrongLetter, encVal);

        assertEquals(0, game.getPlayer().getCorrectGuesses(),
                "A wrong guess should not increment correctGuesses");
    }

    // Scenario 3: One correct guess followed by one incorrect guess gives 50% accuracy.
    // Uses a letter absent from the phrase for the wrong guess so it is provably wrong
    // and does not collide with the first letter (avoiding "already guessed" block).
    @Test
    public void Scenario3AccuracyIsHalfAfterOneCorrectOneIncorrect() {
        Player testplayer = new Player("player");
        Game game = new Game(testplayer, "1");

        int pos1 = firstLetterPos(game);
        int pos2 = secondDistinctLetterPos(game, pos1);
        if (pos2 == -1) return; // phrase has only one unique letter — can't run this scenario

        // First guess: correct
        char correctLetter = Character.toUpperCase(game.getCryptogram().getPhrase().charAt(pos1));
        String encVal1 = game.getCryptogram().getEncryptedPhrase()[pos1]
                .substring(0, game.getCryptogram().getEncryptedPhrase()[pos1].length() - 1);
        game.enterLetter(game.getCryptogram(), correctLetter, encVal1);

        // Second guess: wrong — use a letter absent from the whole phrase
        // so it is definitely wrong AND won't collide with the first guess
        String encVal2 = game.getCryptogram().getEncryptedPhrase()[pos2]
                .substring(0, game.getCryptogram().getEncryptedPhrase()[pos2].length() - 1);

        // Build set of already-entered letters to find one that hasn't been used
        Set<Character> used = new HashSet<>();
        used.add(correctLetter);
        char wrongLetter = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            // Letter must not be in the phrase (guarantees it's wrong) and not already entered
            if (!game.getCryptogram().getPhrase().toUpperCase().contains(String.valueOf(c))
                    && !used.contains(c)) {
                wrongLetter = c;
                break;
            }
        }
        if (wrongLetter == 0) return; // no usable wrong letter found

        game.enterLetter(game.getCryptogram(), wrongLetter, encVal2);

        game.getPlayer().updateAccuracy();

        // 1 correct out of 2 total = 50%
        assertEquals(50.0, game.getPlayer().getAccuracy(), 0.001,
                "One correct and one incorrect guess should give 50% accuracy");
    }
}