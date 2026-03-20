package src.main.java;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TrackCorrectCryptosTest {

    @Test
    public void Scenario1() {
        //Create player and game objects
        Player player = new Player("John Smith");
        Game game = new Game(player, "1");

        String result = "";

        //Enter correct letters for all encrypted letters
        for(int i = 1; i < game.getCryptogram().getPhrase().length()-1; i++) {
            result = game.enterLetter(game.getCryptogram(), game.getCryptogram().getPhrase().charAt(i), game.getCryptogram().getEncryptedPhrase()[i].substring(0,game.getCryptogram().getEncryptedPhrase()[i].length()-1));
        }

        //Print success message if answer is correct
        game.checkCompletion(result);

        //Assert that num of successfull cryptos has incremenetd
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

}
