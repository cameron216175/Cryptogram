import java.util.Random;

public class Cryptogram {
    protected String phrase = "This is a test cryptogram";
    protected StringBuilder cryptogramAlphabet = new StringBuilder();

    public void getFrequencies() {}

    public String getPhrase(){
        return phrase;
    }

    public StringBuilder getCryptogramAlphabet(){
        return cryptogramAlphabet;
    }


    public void createCryptogramAlphabet(StringBuilder cryptogramAlphabet){
        Random rand = new Random();

        int shift = rand.nextInt(100);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        shift = shift % 26;

        for (char c : alphabet.toCharArray()) {

            //Encryption if letter is upper case
            if (Character.isUpperCase(c)) {
                char encrypted = (char) ((c - 'A' + shift) % 26 + 'A');
                cryptogramAlphabet.append(encrypted);
            }

            //Encryption if letter is lower case
            else if(Character.isLowerCase(c)){
                char encrypted = (char) ((c - 'a' + shift) % 26 + 'a');
                cryptogramAlphabet.append(encrypted);
            }
        }
    }
}