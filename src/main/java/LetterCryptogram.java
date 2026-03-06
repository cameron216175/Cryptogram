package src.main.java;

import java.lang.String;
import java.lang.Character;
import java.util.Arrays;
import java.util.Random;


public class LetterCryptogram extends Cryptogram {

    //Initialise string to store encrypted letters
    protected char[] encrypted_alphabet = new char[26];

    //If loading a saved cryptogram
    public LetterCryptogram(String file) {}

    //If creating a new cryptogram
    public LetterCryptogram() {

        //Initialise variables
        int x = 0;

        //Fill string with encrypted letters
        createEncryptedAlphabet(encrypted_alphabet);

        String[] encrypted_phrase = getEncryptedPhrase();

        //Loop through entire phrase
        for(int i = 0; i < phrase.length(); i++) {
            //Take a character from the phrase
            char ch = phrase.charAt(i);

            //If the character is a letter
            if(Character.isLetter(ch)) {

                //set lowercase
                ch = Character.toLowerCase(ch);

                //Compare character from phrase at with every letter in the alphabet
                int idx = ch - 'a';
                char encrypted_letter = encrypted_alphabet[idx];
                encrypted_phrase[x] = encrypted_letter + " ";
            }

            //If character is not a letter, then append it to the end of the encrypted phrase
            else{
                encrypted_phrase[x] = "  ";
            }
            x++;

        }
    }

    public void createEncryptedAlphabet(char[] cryptogram_alphabet){
        Random rand = new Random();

        //Initialise alphabet string
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        //Initialise shift was a random number 1-26
        int shift = rand.nextInt(26);
        shift = shift % 26;

        int idx = 0;
        for (char c : alphabet.toCharArray()) {

            //Encryption if letter is upper case
            if (Character.isUpperCase(c)) {
                char encrypted = (char) ((c - 'A' + shift) % 26 + 'A');
                cryptogram_alphabet[idx] = encrypted;
            }

            //Encryption if letter is lower case
            else if(Character.isLowerCase(c)){
                char encrypted = (char) ((c - 'a' + shift) % 26 + 'a');
                cryptogram_alphabet[idx] = encrypted;
            }
            idx++;
        }
    }

    public void getPlainLetter(char cryptoLetter) {}


    public char[] getCryptogram_alphabet(){
        return encrypted_alphabet;
    }
}
