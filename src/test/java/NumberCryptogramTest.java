package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.LetterCryptogram;
import src.main.java.NumberCryptogram;

import static org.junit.jupiter.api.Assertions.*;

class NumberCryptogramTest {

    @Test
    public void testEncrypt() {

        NumberCryptogram numberCryptogram = new NumberCryptogram();

        String[] encrypted = numberCryptogram.getEncryptedPhrase();

        assertNotNull(encrypted);

    }

    @Test
    public void testNumbers() {

        NumberCryptogram numberCryptogram = new NumberCryptogram();

        String[] encrypted = numberCryptogram.getEncryptedPhrase();

        boolean hasnumber = false;

        for(String s : encrypted) {

            s = s.trim();

            if(!s.isEmpty() && Character.isDigit(s.charAt(0))) {

                hasnumber = true;

                break;

            }

        }

        assertTrue(hasnumber);

    }

    @Test
    public void testPlainLetter() {

        NumberCryptogram numberCryptogram = new NumberCryptogram();

        char letter = numberCryptogram.getPlainLetter(1);

        assertTrue(Character.isLetter(letter) || letter == '?');

    }

    @Test
    public void testCypher() {

        NumberCryptogram numberCryptogram = new NumberCryptogram();

        for (int i = 1; i <= 26; i++) {

            char plain = numberCryptogram.getPlainLetter(i);

            assertTrue(Character.isLetter(plain) || plain == '?');

        }

    }


}
