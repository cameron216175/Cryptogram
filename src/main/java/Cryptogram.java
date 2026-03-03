package src.main.java;

import java.security.SecureRandom;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cryptogram {

    private static final String FILEPATH = "src/phrases.txt";

    protected final String cryptogramAlphabet = "poiuytrewqlkjhgfdsamnbvcxz";

    protected ArrayList<String> phrases;

    protected String originalPhrase;

    private SecureRandom random = new SecureRandom();

    public Cryptogram() {

        loadPhrases();

        selectPhrase();

    }


    //To be handled
    public void getFrequencies() {}
    //To be handled

    private void loadPhrases() {

        try  {

            List<String> lines = Files.readAllLines(Paths.get(FILEPATH));

            phrases = new ArrayList<>();

            for (String line : lines) {

                if (!line.trim().isEmpty()) {

                    phrases.add(line.trim());

                }

            }

        } catch (IOException e) {

            throw new IllegalStateException("No phrases found.", e);

        }

    }

    private void selectPhrase() {

        if (phrases.isEmpty()) {

            throw new IllegalStateException("No phrases found.");

        }

        int index = random.nextInt(phrases.size());

        originalPhrase = phrases.get(index);

    }

    public String getOriginalPhrase() {

        return originalPhrase;

    }

    public void newGram() {

        selectPhrase();

    }

}
