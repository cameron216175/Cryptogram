package src.main.java;

import java.lang.String;


public class LetterCryptogram extends Cryptogram {

    private String encryptedPhrase;

    //To be done
    public LetterCryptogram(String file) {}
    //To be done

    public LetterCryptogram() {

        super();

        Cryptogram();

    }

    private void Cryptogram() {

        StringBuilder newPhrase = new StringBuilder();

        for (int i = 0; i < originalPhrase.length(); i++) {

            char letter = originalPhrase.charAt(i);

            if (Character.isLowerCase(letter)) {

                int index = letter - 'a';

                newPhrase.append(cryptogramAlphabet.charAt(index));

            } else if (Character.isUpperCase(letter)) {

                int index = letter - 'A';

                char mapped = cryptogramAlphabet.charAt(index);

                newPhrase.append(Character.toUpperCase(mapped));

            } else {

                newPhrase.append(letter);

            }

        }

        encryptedPhrase = newPhrase.toString();

    }

    public String getEncryptedPhrase() {

        return encryptedPhrase;

    }

    public char getPlainLetter(char cryptoLetter) {

        if (Character.isLowerCase(cryptoLetter)) {

            int index = cryptogramAlphabet.indexOf(cryptoLetter);

            return (char) ('a' + index);

        }

        if (Character.isUpperCase(cryptoLetter)) {

            int index = cryptogramAlphabet.indexOf(Character.toLowerCase(cryptoLetter));

            return Character.toUpperCase((char) ('a' + index));

        }

        return cryptoLetter;

    }
}
