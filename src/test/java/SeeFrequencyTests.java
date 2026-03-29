package src.test.java;
import org.junit.jupiter.api.Test;
import src.main.java.Game;
import src.main.java.Player;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SeeFrequencyTests {
    @Test
    void testNotNullorEmpty() {
        Player p = new Player("test");
        Game game = new Game(p, "1");
        HashMap<String, Integer> freq = game.getCryptogram().getFrequencies();
        assertNotNull(freq);
        assertFalse(freq.isEmpty());
    }

    @Test
    void testFrequencies(){
        Player p = new Player("test");
        Game game = new Game(p, "1");
        HashMap<String, Integer> freq = game.getCryptogram().getFrequencies();
        for (int count : freq.values()){
            assertTrue(count > 0);
        }
    }
    @Test
    void testTotal100(){
        Player p = new Player("test");
        Game game = new Game(p, "1");
        HashMap<String, Integer> freq = game.getCryptogram().getFrequencies();
        int total100 = 0;
        for (int count : freq.values()){
            total100 += count;
        }
        double sum = 0;
        for (int count : freq.values()){
            sum += count * 100.0/total100;
        }
        assertEquals(100.0, sum, 0.01);
    }
@Test
    void testNumCryptograms(){
        Player p = new Player("test");
        Game game = new Game(p, "0");
        HashMap<String, Integer> freq = game.getCryptogram().getFrequencies();
        assertNotNull(freq);
        assertFalse(freq.isEmpty());
    }

  @Test
    void testLetterCount(){
        Player p = new Player("test");
        Game game = new Game(p, "1");

        String phrase = game.getCryptogram().getPhrase();
        int count = 0;
        for (char c : phrase.toCharArray()){
            if (Character.isLetter(c)) count++;
        }
        HashMap<String, Integer> freq = game.getCryptogram().getFrequencies();
        int sum = 0;
        for (int c : freq.values()){
            sum += c;
        }
        assertEquals (count, sum);
    }

}
