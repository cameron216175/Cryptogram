package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.*;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateCryptogramTest {

    // Scenario 1: Generated letter cryptogram has an encrypted phrase
    @Test
    public void Scenario1Letters() {
        LetterCryptogram c = new LetterCryptogram();
        assertNotNull(c.getEncryptedPhrase());
        assertTrue(c.getEncryptedPhrase().length > 0);
    }

    // Scenario 2: Generated number cryptogram has an encrypted phrase
    @Test
    public void Scenario2Numbers() {
        NumberCryptogram c = new NumberCryptogram();
        assertNotNull(c.getEncryptedPhrase());
        assertTrue(c.getEncryptedPhrase().length > 0);
    }

    // Scenario 3: Encrypted phrase length matches original phrase length
    @Test
    public void Scenario3Letters() {
        LetterCryptogram c = new LetterCryptogram();
        assertEquals(c.getPhrase().length(), c.getEncryptedPhrase().length);
    }

    @Test
    public void Scenario3Numbers() {
        NumberCryptogram c = new NumberCryptogram();
        assertEquals(c.getPhrase().length(), c.getEncryptedPhrase().length);
    }

    // Scenario 4: Encrypted phrase is actually different from the original
    @Test
    public void Scenario4Letters() {
        LetterCryptogram c = new LetterCryptogram();
        String plain = c.getPhrase();
        String encrypted = String.join("", c.getEncryptedPhrase()).trim();
        assertNotEquals(plain.trim(), encrypted);
    }

    // Scenario 5: No letter maps to itself (substitution cipher rule)
    @Test
    public void Scenario5NoSelfMapping() {
        LetterCryptogram c = new LetterCryptogram();
        for (int i = 0; i < 26; i++) {
            char plain = (char) ('A' + i);
            char encrypted = c.encrypted_values[i];
            assertNotEquals(plain, encrypted,
                    "Letter " + plain + " should not map to itself");
        }
    }

    // Scenario 6: Invalid input to generateCryptogram returns null
    @Test
    public void Scenario6InvalidInput() {
        Player p = new Player("test");
        Game game = new Game(p, "1");
        Cryptogram result = game.generateCryptogram("invalid");
        assertNull(result);
    }
}