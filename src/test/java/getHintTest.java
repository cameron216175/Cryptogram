package src.test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;

public class getHintTest {

    @Test
    void test() {
        Player p = new Player("tester");
        Game g = new Game(p, "1");

        int before = g.getPlayerGameMapping().size();

        g.getHint();

        int after = g.getPlayerGameMapping().size();

        assertEquals(before, after);
    }

}
