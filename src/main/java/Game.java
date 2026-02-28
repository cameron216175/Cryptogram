import java.util.Scanner;

package src.main.java;

public class Game {
    private char[] playerGameMapping;
    Player currentPlayer = new Player();

    public void Game(Player p, String cryptType) {}

    public void Game(Player p) {}

    public void getHint () {}

    public void loadPlayer() {}

    public void playGame() {}

    public void generateCryptogram() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want a numbers or letters cryptogram?");

        String input = sc.nextLine();

        if (input.equals("numbers")) {
            System.out.println("Creating numbers cryptogram");
            NumberCryptogram number_cryptogram = new NumberCryptogram();
            System.out.print("The cryptogram is: " + number_cryptogram.getEncryptedPhrase());
        }

        else if (input.equals("letters")) {
            System.out.println("Creating letters cryptogram");
            LetterCryptogram letter_cryptogram = new LetterCryptogram();
            System.out.print("The cryptogram is: " + letter_cryptogram.getEncryptedPhrase());
        }

        else{
            System.out.println("Invalid input");
        }


    }

    public void enterLetter() {}

    public void undoLetter() {}
    
    public void viewFrequencies() {}

    public void saveGame() {}

    public void loadGame() {}

    // public void generateCryptogram() {}

    public void showSolution() {}
}
