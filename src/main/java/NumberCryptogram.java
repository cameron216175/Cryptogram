package src.main.java;

import java.util.Random;

public class NumberCryptogram extends Cryptogram {

    protected int[] encrypted_values = new int[26];

    public NumberCryptogram(String file) {}

    public NumberCryptogram() {

        phrase = Cryptogram.newGram().toUpperCase();

        encrypted_phrase = new String[phrase.length()];

        createEncryptedValues(encrypted_values);

        encryptPhrase();

    }

    private void encryptPhrase() {

        for (int i = 0; i < phrase.length(); i++) {

            char ch = phrase.charAt(i);

            if (Character.isLetter(ch)) {

                ch = Character.toLowerCase(ch);

                int idx = ch - 'a';

                int encrypted_number = encrypted_values[idx];

                encrypted_phrase[i] = encrypted_number + " ";

            } else {

                encrypted_phrase[i] = "  ";

            }

        }

    }

    public void createEncryptedValues(int[] encrypted_values) {

        Random rand = new Random();

        int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

        int shift = rand.nextInt(26);

        for (int i = 0; i < 26; i++) {

            int value = (values[i] - 1 + shift) % 26 + 1;

            encrypted_values[i] = value;

        }

    }

    public char getPlainLetter(int cryptoValue) {

        for (int i = 0; i < 26; i++) {

            if (encrypted_values[i] == cryptoValue) {

                return (char) ('A' + i);

            }

        }

        return '?';

    }

}