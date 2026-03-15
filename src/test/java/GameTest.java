package src.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Scanner;

import src.main.java.*;

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
    public void GameCreationTest() {

        String input = "exit\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Player p = new Player("test");

        Game game = new Game(p, "1");

        assertTrue(true);

    }

    @Test
    public void GenerateCryptogramTest() {

        String input = "exit\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Player p = new Player("test");

        Game game = new Game(p, "1");

        Cryptogram c = game.generateCryptogram("1");

        assertNotNull(c);

    }

    @Test
    public void FileCreationTest() {
        String input = "exit\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Player p = new Player("test");

        Game game = new Game(p, "1");

        game.saveGame();

        File savefile = new File("savegame_test.ser");

        assertTrue(savefile.exists());

        savefile.delete();

        assertFalse(savefile.exists());

    }

    @Test
    public void LoadGameTest() {
        String input = "exit\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Player p = new Player("test");

        Game game = new Game(p, "1");

        game.saveGame();

        Game loadedGame = Game.loadGame(p);

        assertNotNull(loadedGame);

    }



}