package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.LetterCryptogram;
import src.main.java.NumberCryptogram;

import java.util.Scanner;

class LetterCryptogramTest {
    @BeforeEach
    void setUp() {
        // Setup code here
    }

    @Test
    void generateCryptogramTest() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want a numbers or letters cryptogram?");
        String input = sc.nextLine();
        if (input.equals("numbers")) {
            System.out.println("Creating numbers cryptogram");
            NumberCryptogram number_cryptogram = new NumberCryptogram();
            System.out.print("The cryptogram is: " + String.valueOf(number_cryptogram.getEncryptedPhrase()));
        } else if (input.equals("letters")) {
            System.out.println("Creating letters cryptogram");
            LetterCryptogram letter_cryptogram = new LetterCryptogram();
            System.out.print("The cryptogram is: " + String.valueOf(letter_cryptogram.getEncryptedPhrase()));
        } else {
            System.out.println("Invalid input");
        }

    }
}
