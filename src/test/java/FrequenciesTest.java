package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class FrequenciesTest {

    // ── Letter cryptogram tests ──────────────────────────────────────────────

    // Scenario 1: getFrequencies returns a non-empty map for a letter cryptogram
    @Test
    public void Scenario1LetterFrequenciesNotEmpty() {
        LetterCryptogram c = new LetterCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        assertNotNull(freq);
        assertFalse(freq.isEmpty(), "Frequency map should not be empty for a letter cryptogram");
    }

    // Scenario 2: Every count in the frequency map is positive (>0)
    @Test
    public void Scenario2LetterFrequencyCountsPositive() {
        LetterCryptogram c = new LetterCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        for (int count : freq.values()) {
            assertTrue(count > 0, "All frequency counts should be greater than zero");
        }
    }

    // Scenario 3: Keys in the letter cryptogram frequency map are single uppercase letters
    @Test
    public void Scenario3LetterFrequencyKeysAreLetters() {
        LetterCryptogram c = new LetterCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        for (String key : freq.keySet()) {
            assertEquals(1, key.length(), "Letter cryptogram keys should be single characters");
            assertTrue(Character.isLetter(key.charAt(0)), "Key should be a letter, got: " + key);
        }
    }

    // Scenario 4: Total frequency count equals the number of non-empty encrypted slots.
    // We count non-empty slots in the encrypted phrase (the same source getFrequencies()
    // uses) rather than non-space chars in the plain phrase, because phrases may contain
    // punctuation (apostrophes, commas) that generates its own encrypted slot.
    @Test
    public void Scenario4LetterTotalCountMatchesEncryptedSlots() {
        LetterCryptogram c = new LetterCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();

        int totalFromFreq = 0;
        for (int count : freq.values()) {
            totalFromFreq += count;
        }

        int nonEmptySlots = 0;
        for (String s : c.getEncryptedPhrase()) {
            if (s != null && !s.trim().isEmpty()) {
                nonEmptySlots++;
            }
        }

        assertEquals(nonEmptySlots, totalFromFreq,
                "Total frequency count should equal number of non-empty encrypted phrase slots");
    }

    // Scenario 5: Frequency map does not contain an entry for spaces
    @Test
    public void Scenario5LetterNoSpaceInFrequencyMap() {
        LetterCryptogram c = new LetterCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        assertFalse(freq.containsKey(" "), "Frequency map should not contain a space entry");
    }

    // Scenario 6: Repeated encrypted letters appear with a count > 1
    // (phrases are 30-40 chars so there will always be repeats)
    @Test
    public void Scenario6LetterSomeFrequenciesGreaterThanOne() {
        LetterCryptogram c = new LetterCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        boolean hasRepeat = freq.values().stream().anyMatch(count -> count > 1);
        assertTrue(hasRepeat, "A phrase of 30-40 characters should have at least one repeated encrypted letter");
    }

    // ── Number cryptogram tests ──────────────────────────────────────────────

    // Scenario 7: getFrequencies returns a non-empty map for a number cryptogram
    @Test
    public void Scenario7NumberFrequenciesNotEmpty() {
        NumberCryptogram c = new NumberCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        assertNotNull(freq);
        assertFalse(freq.isEmpty(), "Frequency map should not be empty for a number cryptogram");
    }

    // Scenario 8: Every count in the number cryptogram frequency map is positive
    @Test
    public void Scenario8NumberFrequencyCountsPositive() {
        NumberCryptogram c = new NumberCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        for (int count : freq.values()) {
            assertTrue(count > 0, "All frequency counts should be greater than zero");
        }
    }

    // Scenario 9: Keys in the number cryptogram frequency map are numeric strings in range 1-26
    @Test
    public void Scenario9NumberFrequencyKeysAreValidNumbers() {
        NumberCryptogram c = new NumberCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        for (String key : freq.keySet()) {
            int num;
            try {
                num = Integer.parseInt(key.trim());
            } catch (NumberFormatException e) {
                fail("Number cryptogram frequency key should be numeric, got: " + key);
                return;
            }
            assertTrue(num >= 1 && num <= 26,
                    "Number cryptogram key should be between 1 and 26, got: " + num);
        }
    }

    // Scenario 10: Total frequency count for number cryptogram equals non-empty encrypted slots
    @Test
    public void Scenario10NumberTotalCountMatchesEncryptedSlots() {
        NumberCryptogram c = new NumberCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();

        int totalFromFreq = 0;
        for (int count : freq.values()) {
            totalFromFreq += count;
        }

        int nonEmptySlots = 0;
        for (String s : c.getEncryptedPhrase()) {
            if (s != null && !s.trim().isEmpty()) {
                nonEmptySlots++;
            }
        }

        assertEquals(nonEmptySlots, totalFromFreq,
                "Total frequency count should equal number of non-empty encrypted phrase slots");
    }

    // Scenario 11: Frequency map does not contain a space or empty entry for number cryptogram
    @Test
    public void Scenario11NumberNoSpaceInFrequencyMap() {
        NumberCryptogram c = new NumberCryptogram();
        HashMap<String, Integer> freq = c.getFrequencies();
        assertFalse(freq.containsKey(" "), "Frequency map should not contain a space entry");
        assertFalse(freq.containsKey(""), "Frequency map should not contain an empty string entry");
    }

    // Scenario 12: viewFrequencies does not throw for a letter game
    @Test
    public void Scenario12ViewFrequenciesDoesNotThrowLetters() {
        Player p = new Player("freqtest");
        Game game = new Game(p, "1");
        assertDoesNotThrow(game::viewFrequencies,
                "viewFrequencies should not throw an exception for a letter cryptogram");
    }

    // Scenario 13: viewFrequencies does not throw for a numbers game
    @Test
    public void Scenario13ViewFrequenciesDoesNotThrowNumbers() {
        Player p = new Player("freqtest");
        Game game = new Game(p, "0");
        assertDoesNotThrow(game::viewFrequencies,
                "viewFrequencies should not throw an exception for a number cryptogram");
    }
}