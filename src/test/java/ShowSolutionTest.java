package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.main.java.Cryptogram;
import src.main.java.Game;
import src.main.java.Player;

public class ShowSolutionTest {

    @Test
    public void test() {
        Player p = new Player("testUser");
        Game g = new Game(p, "1");

        String res = g.getSolution();
        assertEquals(g.getCryptogram().getPhrase(), res);

    }
}
