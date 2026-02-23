import java.util.Scanner;

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
        }

        else if (input.equals("letters")) {
            System.out.println("Creating letters cryptogram");
            LetterCryptogram letter_cryptogram = new LetterCryptogram();
        }

        else{
            System.out.println("Invalid input");
        }
    }

    public void enterLetter() {}

    public void undoLetter() {}
    
    public void viewFrequencies() {}

    public void saveGane() {}

    public void loadGame() {}

    // public void generateCryptogram() {}

    public void showSolution() {}
}
