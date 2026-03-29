package src.main.java;

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

public abstract class Cryptogram implements Serializable {

    protected String phrase;

    protected String[] encrypted_phrase;

    public String getPhrase() {

        return phrase;

    }

    public String[] getEncryptedPhrase() {

        return encrypted_phrase;

    }

    public Cryptogram() {}

    public HashMap<String, Integer> getFrequencies() {

        HashMap<String, Integer> freq = new HashMap<>();

        for(String s : encrypted_phrase) {

            if (s != null && !s.trim().isEmpty()) {
                String key = s.trim();
                if(freq.containsKey(key)) {
                    freq.put(key, freq.get(key) + 1);
                } else {
                    freq.put(key, 1);
                }
            }
        }

        return freq;

    }

    public static String newGram() {

        ArrayList<String> phrases = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/phrases.txt"));

            String line;

            while((line = reader.readLine()) != null) {

                if(!line.trim().isEmpty()) {

                    phrases.add(line);

                }

            }

            reader.close();

        }

        catch(Exception e) {

            e.printStackTrace();

        }

        Random rand = new Random();

        return phrases.get(rand.nextInt(phrases.size()));

    }

}
