package src.main.java;

import java.util.Random;

public class Cryptogram {
    protected String phrase = "testing";
    protected final String[] encrypted_phrase = new String[phrase.length()];

    public void getFrequencies() {}

    public String getPhrase(){
        return phrase;
    }

    public String[] getEncryptedPhrase() {
        return encrypted_phrase;
    }



}