package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.main.java.LetterCryptogram;
import src.main.java.NumberCryptogram;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LetterCryptogramTest {

    @Test
    void testEncrypt() {

        LetterCryptogram letterCryptogram = new LetterCryptogram();

        String[] encrypted = letterCryptogram.getEncryptedPhrase();

        assertNotNull(encrypted);

        assertTrue(encrypted.length > 0);


    }

    @Test
    void testLength() {

        LetterCryptogram letterCryptogram = new LetterCryptogram();

        String phrase = letterCryptogram.getPhrase();

        String[] encrypted = letterCryptogram.getEncryptedPhrase();

        assertEquals(phrase.length(), encrypted.length);


    }

    @Test
    void testPlainletter() {

        LetterCryptogram letterCryptogram = new LetterCryptogram();

        char letter = letterCryptogram.getPlainLetter('A');

        assertTrue(Character.isLetter(letter) || letter == '?');

    }

    @Test
    void testPhraseMatch() {

        LetterCryptogram letterCryptogram = new LetterCryptogram();

        String phrase = letterCryptogram.getPhrase();

        String encrypted = String.join("", letterCryptogram.getEncryptedPhrase());

        assertNotEquals(phrase, encrypted);

    }

    @Test
    void testCypher() {

        LetterCryptogram letterCryptogram = new LetterCryptogram();

        for (char encrypted = 'A'; encrypted<'Z'; encrypted++) {

            char plain = letterCryptogram.getPlainLetter(encrypted);

            if (plain != '?') {

                assertTrue(Character.isLetter(plain));

            }

        }

    }

}
