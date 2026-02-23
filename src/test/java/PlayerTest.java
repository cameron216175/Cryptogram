package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Player;


import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("john doe");
    }

    @Test
    void testGetAccuracy() {
        assertEquals(0.0, player.getAccuracy());
    }

    @Test
    void testGetUsername() {
        assertEquals("john doe", player.getUsername());
    }

    @Test
    void testGetTotalGuesses() {
        assertEquals(0, player.getTotalGuesses());
    }

    @Test
    void testGetNumCryptogramsCompleted() {
        assertEquals(0, player.getNumCryptogramsCompleted());
    }

    @Test
    void testGetNumCryptoGramsPlayed() {
        assertEquals(0, player.getNumCryptogramsPlayed());
    }
}
