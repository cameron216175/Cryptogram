package src.main.java;

import java.lang.String;
import java.lang.Character;


public class LetterCryptogram extends Cryptogram {

    // Initialise both alphabets
    protected String cryptogramAlphabet = "zyxwvutsrqponmlkjihgfedcba";
    protected String normalAlphabet =     "abcdefghijklmnopqrstuvwxyz";

    //If loading a saved cryptogram
    public LetterCryptogram(String file) {}

    //If creating a new cryptogram
    public LetterCryptogram() {

        //Initialise phrase to be encrypted
        String phrase = "This is a cryptogram.";

        //Declare string to store encrypted phrase
        StringBuilder new_phrase = new StringBuilder();

        //Initialise variables
        int pos = 0;
        boolean uppercase = false;

        //Loop through entire phrase
        for(int i = 0; i < phrase.length(); i++) {

            //Take a character from the phrase
            char ch = phrase.charAt(i);

            //If the character is a letter
            if(Character.isLetter(ch)) {
                //If the character is uppercase, then keep track and set it to lowercase
                if(Character.isUpperCase(ch)) {
                    uppercase = true;
                    ch = Character.toLowerCase(ch);
                }
                //Keep track is character is lowercase
                else{
                    uppercase = false;
                }

                //Compare character from phrase at with every letter in the alphabet
                for(int j = 0; j < normalAlphabet.length(); j++) {

                    //When a match is found
                    if(ch == normalAlphabet.charAt(j)) {
                        pos = j;

                        //Set letter to the value in the cryptogram alphabet at the corresponding position
                        char letter = cryptogramAlphabet.charAt(pos);

                        //Set letter to uppercase if it previously was
                        if(uppercase) {
                            letter = Character.toUpperCase(letter);
                        }
                        //Append encrypted letter to end of the encrypted phrase
                        new_phrase.append(letter);
                    }
                }

            }

            //If character is not a letter, then append it to the end of the encrypted phrase
            else{
                new_phrase.append(ch);
            }
        }


        //Print final encrypted phrase
        System.out.println(new_phrase);
    }

    public void getPlainLetter(char cryptoLetter) {}
}
