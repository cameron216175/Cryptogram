package src.main.java;

public class NumberCryptogram extends Cryptogram {

    private String encryptedPhrase;

    //To do
    public NumberCryptogram(String file) {}


    public NumberCryptogram() {

        super();

        Cryptogram();

    }

    private void Cryptogram() {

        StringBuilder newPhrase = new StringBuilder();

        for (int i = 0; i < originalPhrase.length(); i++) {

            char letter = originalPhrase.charAt(i);

            if (Character.isLetter(letter)) {

                char lowercase = Character.toLowerCase(letter);

                int Index = lowercase - 'a';

                char mappedLetter = cryptogramAlphabet.charAt(Index);

                int numericValue = (mappedLetter - 'a') + 1;

                newPhrase.append(numericValue);

                newPhrase.append(" ");

            } else {

                newPhrase.append(letter);

            }

        }

        encryptedPhrase = newPhrase.toString().trim();

    }

    public String getEncryptedPhrase() {

        return encryptedPhrase;

    }

    public char getPlainNumLetter(int cryptoValue) {

        if (cryptoValue < 1 || cryptoValue > 26) {

            throw new IllegalArgumentException("Invalid Value: Out of alphabetic range.");

        }

        char mappedLetter = (char) ('a' + (cryptoValue - 1));

        int plainIndex = cryptogramAlphabet.indexOf(mappedLetter);

        if (plainIndex == -1) {

            throw new IllegalArgumentException("Invalid Value: Out of alphabetic range.");

        }

        return (char) ('a' + plainIndex);

    }
}
