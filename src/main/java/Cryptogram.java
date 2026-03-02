package src.main.java;

import java.util.Random;

public class Cryptogram {
    protected String phrase = "This is a test cryptogram";
    private final String[] encrypted_phrase = new String[phrase.length()];

    public void getFrequencies() {}

    public String getPhrase(){
        return phrase;
    }

    public String[] getEncryptedPhrase() {
        return encrypted_phrase;
    }



}