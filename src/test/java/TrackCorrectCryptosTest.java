package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TrackCorrectCryptosTest {

    private String enterAllCorrectly(Game game) {
        String phrase = game.getCryptogram().getPhrase();
        String[] encrypted = game.getCryptogram().getEncryptedPhrase();
        Set<Character> alreadyEntered = new HashSet<>();
        String result = "Incomplete";

        for (int i = 0; i < phrase.length(); i++) {
            char plainChar = phrase.charAt(i);
            if (!Character.isLetter(plainChar)) continue;

            char upper = Character.toUpperCase(plainChar);
            if (alreadyEntered.contains(upper)) continue;

            String encVal = encrypted[i].substring(0, encrypted[i].length() - 1);
            result = game.enterLetter(game.getCryptogram(), upper, encVal);
            alreadyEntered.add(upper);
        }
        return result;
    }

    // Scenario 1: Completing the cryptogram correctly increments cryptogramsCompleted
    @Test
    public void Scenario1() {
        Player player = new Player("John Smith");
        Game game = new Game(player, "1");

        String result = enterAllCorrectly(game);
        game.checkCompletion(result);

        assertEquals(1, game.getPlayer().getNumCryptogramsCompleted());
    }

    @Test
    public void Scenario2() {
        //Create player and game objects
        Player player = new Player("John Smith");
        Game game = new Game(player, "1");

        String result = "";

        //Enter z and y as first two letter - no cryptogram starts like this so answer will be wrong
        game.enterLetter(game.getCryptogram(), 'z', game.getCryptogram().getEncryptedPhrase()[1].substring(0,game.getCryptogram().getEncryptedPhrase()[1].length()-1));
        game.enterLetter(game.getCryptogram(), 'y', game.getCryptogram().getEncryptedPhrase()[2].substring(0,game.getCryptogram().getEncryptedPhrase()[2].length()-1));


        //Fill in rest of answers correctly
        for(int i = 3; i < game.getCryptogram().getPhrase().length()-1; i++) {
                result = game.enterLetter(game.getCryptogram(), game.getCryptogram().getPhrase().charAt(i), game.getCryptogram().getEncryptedPhrase()[i].substring(0,game.getCryptogram().getEncryptedPhrase()[i].length()-1));
        }

        game.checkCompletion(result);

        //Assert that num of successfull cryptos has not incremenetd
        assertEquals(0, game.getPlayer().getNumCryptogramsCompleted());

    }

    // Tests that completing 0 letters and exiting does not increment cryptograms completed
    @Test
    public void Scenario3() {
        Player player = new Player("John Smith");
        Game game = new Game(player, "1");

        // Don't enter any letters, just check completion with "Incomplete"
        game.checkCompletion("Incomplete");

        assertEquals(0, game.getPlayer().getNumCryptogramsCompleted());
    }

}
