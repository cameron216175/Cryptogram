package src.main.java;
import java.util.Scanner;

public class Game {
    private char[] playerGameMapping;
    Player currentPlayer = new Player("");

    public static void main(String[] args) {
        Game game = new Game();
        Cryptogram cryptogram = new Cryptogram();

        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want a numbers or letters cryptogram?\n");
        String input = sc.nextLine();

        cryptogram = game.generateCryptogram(input);

        System.out.println("The cryptogram is: " + cryptogram.getEncryptedPhrase());

        game.playerGameMapping = new char[cryptogram.phrase.length()];

        boolean exit = false;

        while(exit == false) {
            System.out.print("Enter a letter: \n");
            char letter = sc.nextLine().charAt(0);
            System.out.print("Enter the encrypted letter you want to make a guess for: \n");
            char encrypted_letter = sc.nextLine().charAt(0);

            game.enterLetter(cryptogram, letter, encrypted_letter);

            System.out.println("Player guesses and cryptogram: \n");
            for (int i = 0; i < game.playerGameMapping.length; i++) {
                System.out.print(game.playerGameMapping[i]);
            }

            System.out.println("\n" + cryptogram.getEncryptedPhrase() + "\n");

            System.out.print("Do you want to exit the game?\n");
            String end = sc.nextLine();
            if(end.equals("yes")){
                exit = true;
            }
        }
    }

    public void Game(Player p, String cryptType) {}

    public void Game(Player p) {}

    public void getHint () {}

    public void loadPlayer() {}

    public void playGame() {}

    public Cryptogram generateCryptogram(String input) {





        if (input.equals("numbers")) {
            return new NumberCryptogram();
        }

        else if (input.equals("letters")) {
            return new LetterCryptogram();
        }

        else{
            System.out.println("Invalid input\n");
            return null;
        }
    }

    public void enterLetter(Cryptogram cryptogram, char letter, char encrypted_letter) {

        String encrypted_phrase = String.valueOf(cryptogram.getEncryptedPhrase());
        boolean isUppercase;

        letter = Character.toLowerCase(letter);
        encrypted_letter = Character.toLowerCase(encrypted_letter);

        for (char c : playerGameMapping) {
            if (c == Character.toUpperCase(letter) || c == Character.toLowerCase(letter)) {
                System.out.println("Error, you have already guessed this letter!");
                return;
            }
        }

        for (int i = 0; i < encrypted_phrase.length(); i++) {
            char ch = encrypted_phrase.charAt(i);
            if (Character.isLetter(ch)) {
                if(Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                    isUppercase = true;
                }
                else{
                    isUppercase = false;
                }

                if (ch == encrypted_letter) {
                    if(isUppercase) {
                        letter = Character.toUpperCase(letter);
                    }
                    if(playerGameMapping[i] == '\0') {
                        playerGameMapping[i] = letter;
                        letter = Character.toLowerCase(letter);
                    }
                    else{
                        System.out.println("Error, you have already made a guess for this letter!\n");
                        return;
                    }
                }
            }
        }
    }

    public void undoLetter() {}
    
    public void viewFrequencies() {}

    public void saveGame() {}

    public void loadGame() {}

    // public void generateCryptogram() {}

    public void showSolution() {}
}
