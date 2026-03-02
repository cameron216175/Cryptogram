package src.main.java;

import java.util.Random;

public class Cryptogram {
    protected String phrase = "This is a test cryptogram";
    private final StringBuilder encrypted_phrase = new StringBuilder();

    public void getFrequencies() {}

    public String getPhrase(){
        return phrase;
    }

    public StringBuilder getEncryptedPhrase() {
        return encrypted_phrase;
    }



}