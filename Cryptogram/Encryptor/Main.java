import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //Random number generator
        Random rand = new Random();

        //Fixed string for testing
        String quote = "I think therefore I am.";

        //Shift amount is random with a bound of 26
        int shift = rand.nextInt(25)+1;

        //Instantiation of the cryptogram
        String cryptogram = Caesar.encrypt(quote, shift);

        //Printing for comparison
        System.out.println("Original: " + quote);
        System.out.println("Crypt: " + cryptogram);

    }

}
