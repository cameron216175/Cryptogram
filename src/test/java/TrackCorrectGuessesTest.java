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

        // Find a letter that definitely isn't at position 1 in the phrase
        char plainLetter = game.getCryptogram().getPhrase().charAt(1);

        // Use 'Z' as the guess - very unlikely to be correct, but we need
        // to use an encrypted value that exists in the cryptogram
        // Get the encrypted value at position 1 but map it to the WRONG plain letter
        String encryptedAtPos1 = game.getCryptogram().getEncryptedPhrase()[1]
                .substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1);

        // Find a plain letter that is definitely NOT at position 1
        char wrongLetter = (plainLetter == 'A') ? 'B' : 'A';

        game.enterLetter(game.getCryptogram(), wrongLetter, encryptedAtPos1);

        assertEquals(0, game.getPlayer().getCorrectGuesses());
    }

    @Test
    public void Scenario3() {
        Player testplayer = new Player("player");
        Game game = new Game(testplayer, "1");

        // Correct guess - right plain letter for the encrypted value at position 1
        char correctLetter = game.getCryptogram().getPhrase().charAt(1);
        String encryptedAtPos1 = game.getCryptogram().getEncryptedPhrase()[1]
                .substring(0, game.getCryptogram().getEncryptedPhrase()[1].length() - 1);

        game.enterLetter(game.getCryptogram(), correctLetter, encryptedAtPos1);

        // Incorrect guess - wrong plain letter for encrypted value at position 2
        char correctLetterAtPos2 = game.getCryptogram().getPhrase().charAt(2);
        String encryptedAtPos2 = game.getCryptogram().getEncryptedPhrase()[2]
                .substring(0, game.getCryptogram().getEncryptedPhrase()[2].length() - 1);
        char wrongLetter = (correctLetterAtPos2 == 'A') ? 'B' : 'A';

        game.enterLetter(game.getCryptogram(), wrongLetter, encryptedAtPos2);

        game.getPlayer().updateAccuracy();

        // 1 correct out of 2 total = 50%
        assertEquals(50.0, game.getPlayer().getAccuracy());
    }
}
