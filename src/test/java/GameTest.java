package src.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.*;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @BeforeEach
    void setUp() {
        // Setup code here
    }

    @Test
    void testExample() {
        // Test code here
    }

    @Test
    void generateNumberCryptogramTest() {
            System.out.println("Creating numbers cryptogram");
            NumberCryptogram number_cryptogram = new NumberCryptogram();
            System.out.print("The cryptogram is: " + String.valueOf(number_cryptogram.getEncryptedPhrase()));
    }

    @Test
    void generateLetterCryptogramTest() {
        System.out.println("Creating letters cryptogram");
        LetterCryptogram letter_cryptogram = new LetterCryptogram();
        System.out.print("The cryptogram is: " + String.valueOf(letter_cryptogram.getEncryptedPhrase()));
    }

    @Test
    void testSaveLoad() {

        Player p = new Player("test", 1, 1, 1, 0, 1);
        Game game = new Game(p);

        LetterCryptogram lc = new LetterCryptogram();

        game.saveGame(lc);

        Cryptogram loaded =  game.loadGame();

        assertNotNull(loaded);

    }

    @Test
    void testLoadMatch() {

        Player p = new Player("test", 1, 1, 1, 0, 1);
        Game game = new Game(p);

        LetterCryptogram lc = new LetterCryptogram();

        String original = lc.getPhrase();

        game.saveGame(lc);

        Cryptogram loaded =  game.loadGame();

        assertEquals(original,lc.getPhrase());

    }
}
