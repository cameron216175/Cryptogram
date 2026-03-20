package src.test.java;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SaveGameTest {

    @Test
    public void FileCreationTestLetters() {

        String input = "exit\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Player p = new Player("test");

        Game game = new Game(p, "1");

        game.saveGame();

        File savefile = new File("savegame_test.ser");

        assertTrue(savefile.exists());

        savefile.delete();

    }

    @Test
    public void FileDeleteTestLetters() {

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
    public void FileCreationTestNumbers() {

        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Player p = new Player("test");
        Game game = new Game(p, "0");

        game.saveGame();
        File savefile = new File("savegame_test.ser");
        assertTrue(savefile.exists());
        savefile.delete();

    }

    @Test
    public void FileDeleteTestNumbers() {

        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Player p = new Player("test");

        Game game = new Game(p, "0");

        game.saveGame();
        File savefile = new File("savegame_test.ser");

        assertTrue(savefile.exists());

        savefile.delete();
        assertFalse(savefile.exists());

    }

    @Test
    public void StatePersistenceTestLetters() {

        String input = "exit\n";

        Player p = new Player("test");
        Game game = new Game(p, "1");
        ArrayList<String> pgm = game.getPlayerGameMapping();
        pgm.set(0, "A ");
        game.saveGame();
        Game loadedgame = Game.loadGame(p);
        assertTrue(loadedgame.getPlayerGameMapping().get(0).equals("A "));

    }

    @Test
    public void StatePersistenceTest2Letters() {

        Player p = new Player("test");
        Game game = new Game(p, "1");

        game.saveGame();

        Game loaded = Game.loadGame(p);

        assertEquals("letters", loaded.getCryptoType());

        new File("savegame_test.ser").delete();

    }

    @Test
    public void StatePersistenceTestNumbers() {

        Player p = new Player("test");
        Game game = new Game(p, "0");

        game.saveGame();

        Game loaded = Game.loadGame(p);

        assertEquals("numbers", loaded.getCryptoType());

        new File("savegame_test.ser").delete();

    }

    @Test
    public void PersistenceStatsTestLetters() {

        Player p = new Player("test");
        p.incrementCryptogramsCompleted();

        Game game = new Game(p, "1");
        game.saveGame();

        Game loaded = Game.loadGame(p);

        assertEquals(p.getNumCryptogramsCompleted(), loaded.getPlayer().getNumCryptogramsCompleted());

        new File("savegame_test.ser").delete();

    }

    @Test
    public void PersistenceStatsTestNumbers() {

        Player p = new Player("test");
        p.incrementCryptogramsCompleted();

        Game game = new Game(p, "0");
        game.saveGame();

        Game loaded = Game.loadGame(p);

        assertEquals(p.getNumCryptogramsCompleted(), loaded.getPlayer().getNumCryptogramsCompleted());

        new File("savegame_test.ser").delete();

    }

    @Test
    public void PersistenceStatsTestLetters2() {

        Player p = new Player("test");
        Game game = new Game(p, "1");

        p.incrementCryptogramsCompleted();
        game.saveGame();

        p.incrementCryptogramsCompleted();

        game = Game.loadGame(p);

        assertEquals(1, game.getPlayer().getNumCryptogramsCompleted());

        new File("savegame_test.ser").delete();

    }

    @Test
    public void PersistenceStatsTestNumbers2() {

        Player p = new Player("test");
        Game game = new Game(p, "0");

        p.incrementCryptogramsCompleted();
        game.saveGame();

        p.incrementCryptogramsCompleted();

        game = Game.loadGame(p);

        assertEquals(1, game.getPlayer().getNumCryptogramsCompleted());

        new File("savegame_test.ser").delete();

    }


}