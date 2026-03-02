package src.main.java;

import java.util.Arrays;
import java.util.Random;

public class NumberCryptogram extends Cryptogram {

    //Initialise int array to store encrypted numbers
    protected int[] encrypted_values = new int[26];

    //If loading saved cryptogram
    public NumberCryptogram(String file) {}

    //If creating new cryptogram
    public NumberCryptogram() {
        //Initialise variables
        int pos = 0;
        int x = 0;


        //Fill int array with encrypted numbers
        createEncryptedValues(encrypted_values);
        String[] encrypted_phrase = getEncryptedPhrase();

        //Loop through entire phrase
        for(int i = 0; i < phrase.length(); i++) {
            //Take next character from the phrase
            char ch = phrase.charAt(i);

            //If the character is a letter
            if(Character.isLetter(ch)) {

                //Set character to lowercase
                ch = Character.toLowerCase(ch);

                //Traverse alphabet and add each encrypted number to index of array
                int idx = ch - 'a';
                int encrypted_number = encrypted_values[idx];
                encrypted_phrase[x] = encrypted_number + " ";
            }

            //If space was found instead of a letter
            else{
                encrypted_phrase[x] = "  ";
            }
            x++;
        }

    }

    public void createEncryptedValues(int[] encryptedValues){
        Random rand = new Random();

        //Initialise values array
        int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

        //Initialise shift with a random number 1-26
        int shift = rand.nextInt(26);
        shift %= 26;

        //Create int array of encrypted values
        for (int i = 0; i < 26; i++) {
            int value = (values[i] - 1 + shift) % 26 + 1;
            encrypted_values[i] = value;
        }
    }

    public void getPlainLetter(int cryptoValue) {}
}
