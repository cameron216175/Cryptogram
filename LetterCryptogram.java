import java.lang.String;
import java.lang.Character;


public class LetterCryptogram extends Cryptogram {

    protected String cryptogramAlphabet = "zyxwvutsrqponmlkjihgfedcba";
    protected String normalAlphabet =     "abcdefghijklmnopqrstuvwxyz";

    //If loading a saved cryptogram
    public LetterCryptogram(String file) {}

    //If creating a new cryptogram
    public LetterCryptogram() {
        String phrase = "This is a cryptogram.";
        StringBuilder new_phrase = new StringBuilder();
        int pos = 0;
        boolean uppercase = false;

        for(int i = 0; i < phrase.length(); i++) {
            char ch = phrase.charAt(i);

            if(Character.isLetter(ch)) {

                if(Character.isUpperCase(ch)) {
                    uppercase = true;
                    ch = Character.toLowerCase(ch);
                }
                else{
                    uppercase = false;
                }

                for(int j = 0; j < normalAlphabet.length(); j++) {
                    if(ch == normalAlphabet.charAt(j)) {
                        pos = j;
                        char letter = cryptogramAlphabet.charAt(pos);
                        if(uppercase) {
                            letter = Character.toUpperCase(letter);
                        }
                        new_phrase.append(letter);
                    }
                }

            }

            else{
                new_phrase.append(ch);
            }
        }



        System.out.println(new_phrase);
    }

    public void getPlainLetter(char cryptoLetter) {}
}
