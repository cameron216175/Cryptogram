package src.main.java;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private String[] playerGameMapping;
    Player currentPlayer = new Player("");
    private String crypto_type = "";

    public static void main(String[] args) {
        Game game = new Game();
        Cryptogram cryptogram = new Cryptogram();

        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want a numbers or letters cryptogram?\n");
        String input = sc.nextLine();

        cryptogram = game.generateCryptogram(input);

        System.out.println("The cryptogram is:");
        for(String str : cryptogram.getEncryptedPhrase()) {
            System.out.print(str);
        }
        System.out.print("\n");

        game.playerGameMapping = new String[cryptogram.phrase.length()];

        game.playerGameMapping = cryptogram.getEncryptedPhrase().clone();
        for(int i = 0; i < game.playerGameMapping.length; i++) {

            if(game.playerGameMapping[i].charAt(0) != ' '){
                game.playerGameMapping[i] = "- ";
            }
        }

        boolean exit = false;

        while(!exit) {
            System.out.print("Enter a letter: \n");
            char letter = sc.nextLine().charAt(0);

            String encrypted_guess = "";

            if(game.getCryptoType().equals("letters")){
                System.out.print("Enter the encrypted letter you want to make a guess for: \n");
                encrypted_guess = sc.nextLine();
            }
            else if(game.getCryptoType().equals("numbers")){
                System.out.print("Enter the encrypted number you want to make a guess for: \n");
                encrypted_guess = sc.nextLine();
            }


            game.enterLetter(cryptogram, letter, encrypted_guess);

            System.out.println("Player guesses and cryptogram:");
            for (int i = 0; i < game.playerGameMapping.length; i++) {
                System.out.print(game.playerGameMapping[i]);
            }
            System.out.print("\n");
            for(String str : cryptogram.getEncryptedPhrase()) {
                System.out.print(str);
            }
            System.out.print("\n");

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
            crypto_type = "numbers";
            return new NumberCryptogram();
        }

        else if (input.equals("letters")) {
            crypto_type = "letters";
            return new LetterCryptogram();
        }

        else{
            System.out.println("Invalid input\n");
            return null;
        }
    }

    public void enterLetter(Cryptogram cryptogram, char letter, String encrypted_guess) {

        boolean isUppercase;
        letter = Character.toLowerCase(letter);

        for (String str : playerGameMapping) {
            if (str.charAt(0) == Character.toLowerCase(letter) || str.charAt(0) == Character.toUpperCase(letter)) {
                System.out.println("Error, you have already guessed this letter!");
                return;
            }
        }

        String[] encrypted_phrase = cryptogram.getEncryptedPhrase();

        char encrypted_char = encrypted_guess.charAt(0);
        encrypted_char = Character.toLowerCase(encrypted_char);

        for (int i = 0; i < encrypted_phrase.length; i++) {
            char ch = encrypted_phrase[i].charAt(0);

            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {

                    ch = Character.toLowerCase(ch);
                    isUppercase = true;
                }
                else {
                    isUppercase = false;
                }

                if (ch == encrypted_char) {
                    if (isUppercase) {
                        letter = Character.toUpperCase(letter);
                    }
                    if (playerGameMapping[i].equals("- ")) {

                        playerGameMapping[i] = letter + " ";
                        letter = Character.toLowerCase(letter);
                    }
                    else {
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

    public String getCryptoType() {
        return crypto_type;
    }
}
