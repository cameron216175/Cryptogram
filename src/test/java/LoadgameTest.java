package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import java.io.ByteArrayInputStream;
import java.io.File;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoadgameTest {

    @Test
    public void LoadGameTest1() {
        // Clean up before test
        new File("savegame_test.ser").delete();

        Player p = new Player("test");
        Game game = new Game(p, "1");
        game.saveGame();

        Game loadedGame = Game.loadGame(p);
        assertNotNull(loadedGame);

        // Clean up after test
        new File("savegame_test.ser").delete();

    }

    @Test
    public void LoadGameTest0() {

        // Clean up before test
        new File("savegame_test.ser").delete();

        Player p = new Player("test");
        Game game = new Game(p, "0");
        game.saveGame();

        Game loadedGame = Game.loadGame(p);
        assertNotNull(loadedGame);

        // Clean up after test
        new File("savegame_test.ser").delete();

    }

    @Test
    public void LoadGameNoFileTest() {
        Player p = new Player("nonexistent_save_user");

        // Make sure no save file exists for this player
        File savefile = new File("savegame_nonexistent_save_user.ser");
        if (savefile.exists()) savefile.delete();

        Game result = Game.loadGame(p);

        assertNull(result);
    }

}
