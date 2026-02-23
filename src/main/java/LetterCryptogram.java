package src.main.java;

import java.lang.String;


public class LetterCryptogram extends Cryptogram {

    protected String cryptogramAlphabet = "poiuytrewqlkjhgfdsamnbvcxz";

    //If loading a saved cryptogram
    public LetterCryptogram(String file) {}

    //If creating a new cryptogram
    public LetterCryptogram() {
        String phrase = "This is a cryptogram";
        String newphrase = "";

        int i = 0;

        while(i < phrase.length()){
            char letter = phrase.charAt(i);
            if(letter == 'T'){
                newphrase = newphrase + cryptogramAlphabet.charAt(i);
            }
        }

        System.out.println(newphrase);
    }

    public void getPlainLetter(char cryptoLetter) {}
}
