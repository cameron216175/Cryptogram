package src.main.java;

import java.util.Random;
import java.io.Serializable;

public class LetterCryptogram extends Cryptogram implements Serializable {

    protected char[] encrypted_values = new char[26];

    public LetterCryptogram() {

        phrase = Cryptogram.newGram().toUpperCase();

        encrypted_phrase = new String[phrase.length()];

        createEncryptedValues();

        encryptPhrase();

    }

    private void encryptPhrase() {

        for (int i = 0; i < phrase.length(); i++) {

            char ch = phrase.charAt(i);

            if (Character.isLetter(ch)) {

                ch = Character.toLowerCase(ch);

                int idx = ch - 'a';

                char encrypted_number = encrypted_values[idx];

                encrypted_phrase[i] = encrypted_number + " ";

            } else {

                encrypted_phrase[i] = "  ";

            }

        }

    }

    public void createEncryptedValues() {

        Random rand = new Random();

        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        int shift = rand.nextInt(25) + 1;

        for (int i = 0; i < 26; i++) {

            encrypted_values[i] = alphabet[(i + shift) % 26];

        }

    }

    public char getPlainLetter(char encryptedLetter) {

        encryptedLetter = Character.toUpperCase(encryptedLetter);

        for (int i = 0; i < 26; i++) {

            if (encrypted_values[i] == encryptedLetter) {

                return (char) ('A' + i);

            }

        }

        return '?';

    }

}