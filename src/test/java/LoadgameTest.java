package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoadgameTest {

    @Test
    public void LoadGameTest1() {

        String input = "exit\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Player p = new Player("test");

        Game game = new Game(p, "1");

        game.saveGame();

        Game loadedGame = Game.loadGame(p);

        assertNotNull(loadedGame);

    }

    @Test
    public void LoadGameTest0() {

        String input = "exit\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Player p = new Player("test");

        Game game = new Game(p, "0");

        game.saveGame();

        Game loadedGame = Game.loadGame(p);

        assertNotNull(loadedGame);

    }

}
