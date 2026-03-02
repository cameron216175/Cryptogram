import java.lang.String;
import java.lang.Character;
import java.util.Random;


public class LetterCryptogram extends Cryptogram {

    //Initialise string to store encrypted letters
    protected StringBuilder encryptedAlphabet = new StringBuilder();

    //If loading a saved cryptogram
    public LetterCryptogram(String file) {}

    //If creating a new cryptogram
    public LetterCryptogram() {

        //Initialise variables
        int pos = 0;
        boolean uppercase = false;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        //Fill string with encrypted letters
        createEncryptedAlphabet(encryptedAlphabet);

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

                //Keep track if character is lowercase
                else{
                    uppercase = false;
                }

                //Compare character from phrase at with every letter in the alphabet
                for(int j = 0; j < alphabet.length(); j++) {

                    //When a match is found
                    if(ch == alphabet.charAt(j)) {
                        pos = j;

                        //Set letter to the value in the cryptogram alphabet at the corresponding position
                        char letter = encryptedAlphabet.charAt(pos);

                        //Set letter to uppercase if it previously was
                        if(uppercase) {
                            letter = Character.toUpperCase(letter);
                        }
                        //Append encrypted letter to end of the encrypted phrase
                        getEncryptedPhrase().append(letter);
                    }
                }

            }

            //If character is not a letter, then append it to the end of the encrypted phrase
            else{
                getEncryptedPhrase().append(ch);
            }

        }
    }

    public void createEncryptedAlphabet(StringBuilder cryptogramAlphabet){
        Random rand = new Random();

        //Initialise variables
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

    public void getPlainLetter(char cryptoLetter) {}


    public StringBuilder getCryptogramAlphabet(){
        return encryptedAlphabet;
    }
}
