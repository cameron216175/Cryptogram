package src.main.java;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private char[] playerGameMapping;
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


        game.playerGameMapping = new char[cryptogram.phrase.length()];

        boolean exit = false;

        while(!exit) {
            System.out.print("Enter a letter: \n");
            char letter = sc.nextLine().charAt(0);

            char encrypted_letter = '\0';
            int encrypted_value = 0;

            if(game.getCryptoType().equals("letters")){
                System.out.print("Enter the encrypted letter you want to make a guess for: \n");
                encrypted_letter = sc.nextLine().charAt(0);
            }
            else if(game.getCryptoType().equals("numbers")){
                System.out.print("Enter the encrypted number you want to make a guess for: \n");
                encrypted_value = Integer.parseInt(sc.nextLine());
            }


            game.enterLetter(cryptogram, letter, encrypted_letter, encrypted_value);

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

    public void enterLetter(Cryptogram cryptogram, char letter, char encrypted_letter, int encrypted_value) {


        boolean isUppercase;

        letter = Character.toLowerCase(letter);


        for (char c : playerGameMapping) {
            if (c == Character.toUpperCase(letter) || c == Character.toLowerCase(letter)) {
                System.out.println("Error, you have already guessed this letter!");
                return;
            }
        }
        String[] encrypted_phrase = cryptogram.getEncryptedPhrase();


        if(crypto_type.equals("letters")) {
            encrypted_letter = Character.toLowerCase(encrypted_letter);

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

                    if (ch == encrypted_letter) {
                        if (isUppercase) {
                            letter = Character.toUpperCase(letter);
                        }
                        if (playerGameMapping[i] == '\0') {

                            playerGameMapping[i] = letter;
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

        else if(crypto_type.equals("numbers")) {
            for(int i = 0; i < encrypted_phrase.length; i++) {
                int n = Character.getNumericValue(encrypted_phrase[i].charAt(0));
                System.out.println(n);
                int j = i;
                char ch = encrypted_phrase[j++].charAt(0);
                System.out.println(ch);

                if(n<10){
                    if(i == 0){
                        if(n == encrypted_value && ch == ' '){
                            playerGameMapping[i] = letter;
                        }
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
