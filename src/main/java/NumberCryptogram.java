package src.main.java;

import java.util.Arrays;
import java.util.Random;

public class NumberCryptogram extends Cryptogram {

    //Initialise int array to store encrypted numbers
    private final int[] encrypted_values = new int [26];

    //If loading saved cryptogram
    public NumberCryptogram(String file) {}

    //If creating new cryptogram
    public NumberCryptogram() {
        //Initialise variables
        int pos = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        //Fill int array with encrypted numbers
        createEncryptedValues(encrypted_values);

        //Loop through entire phrase
        for(int i = 0; i < phrase.length(); i++) {
            //Take a character from the phrase
            char ch = phrase.charAt(i);

            //If the character is a letter
            if(Character.isLetter(ch)) {

                //If the character is uppercase then set it to lower case
                if(Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                }

                //Compare character from phrase at with every letter in the alphabet
                for(int j = 0; j < alphabet.length(); j++) {

                    //When a match is found
                    if(ch == alphabet.charAt(j)) {
                        pos = j;

                        //Set letter to the value in the cryptogram alphabet at the corresponding position
                        int encrypted_number = encrypted_values[pos];

                        //Append encrypted letter to end of the encrypted phrase
                        getEncryptedPhrase().append(encrypted_number).append(" ");
                    }
                }

            }

            //If character wasn't a letter (i.e. a space), append multiple spaces to the end of the encrypted phrase to show the end of a word
            else{
                getEncryptedPhrase().append("    ");
            }
        }

    }

    public void createEncryptedValues(int[] encryptedValues){
        Random rand = new Random();

        //Initialise variables
        int shift = rand.nextInt(100);
        int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

        shift = shift % 26;

        for (int i = 0; i < encryptedValues.length; i++) {
            int n = values[i];
            int encrypted = (int) ((n - 1 + shift) % 26 + 1);

            encryptedValues[i] = encrypted;
        }
    }

    public void getPlainLetter(int cryptoValue) {}
}
