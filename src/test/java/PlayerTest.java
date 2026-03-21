package src.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Player;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("john doe");
    }

    @Test
    void testUpdateAccuracy() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                player.incrementCorrectGuesses();
            }
            player.incrementTotalGuesses();
        }
        player.updateAccuracy();
        assertEquals(50, player.getAccuracy());
    }

    @Test
    void testUpdateAccuracyNoGuesses() {
        Player freshPlayer = new Player("noguesses");
        // totalGuesses is 0, this should not throw or produce NaN
        freshPlayer.updateAccuracy();
        Assertions.assertFalse(Double.isNaN(freshPlayer.getAccuracy()));
    }

    @Test
    void testIncrementCryptogramsCompleted() {
        for (int i = 0; i < 20; i++) {
                player.incrementCryptogramsCompleted();
        }
        assertEquals(20, player.getNumCryptogramsCompleted());
    }

    @Test
    void testIncrementCryptogramsPlayed() {
        for (int i = 0; i < 20; i++) {
            player.incrementCryptogramsPlayed();
        }
        assertEquals(20, player.getNumCryptogramsPlayed());
    }

    @Test
    void testIncrementCorrectGuesses() {
        for (int i = 0; i < 20; i++) {
            player.incrementCorrectGuesses();
        }
        assertEquals(20, player.getCorrectGuesses());
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
