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

        String[] encrypted_phrase = cryptogram.getEncryptedPhrase();

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
                if(encrypted_phrase[i].length() == 3){
                    System.out.print(" ");
                }
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

        //Initialise variables
        boolean isUppercase;
        letter = Character.toLowerCase(letter);
        boolean found = false;
        boolean isFull = true;

        //Print error and return if guess isn't a letter
        if(!Character.isLetter(letter)){
            System.out.println("Invalid input, " + letter + " is not a letter!\n");
            return;
        }

        //Print error and return if player has already guessed the letter
        for (String str : playerGameMapping) {
            if (str.charAt(0) == Character.toLowerCase(letter) || str.charAt(0) == Character.toUpperCase(letter)) {
                System.out.println("Error, you have already guessed " + letter + " as an answer!");
                return;
            }
        }

        //Store encrypted phrase in a variable
        String[] encrypted_phrase = cryptogram.getEncryptedPhrase();

        //Only run if a letters cryptogram was made
        if(crypto_type.equals("letters")) {
            //Get the first char from user input string
            char encrypted_char = encrypted_guess.charAt(0);

            //Make char lowercase to be able to compare with other chars
            encrypted_char = Character.toLowerCase(encrypted_char);


            //Loop for length of encrypted phrase
            for (int i = 0; i < encrypted_phrase.length; i++) {

                //Get char stored at the next element of the encrypted phrase
                char ch = encrypted_phrase[i].charAt(0);

                //Check if char is a letter or a space
                if (Character.isLetter(ch)) {

                    //Turn char to lowercase for comparison, but keep track if it was uppercase
                    if (Character.isUpperCase(ch)) {
                        ch = Character.toLowerCase(ch);
                        isUppercase = true;
                    } else {
                        isUppercase = false;
                    }

                    //If a match is found
                    if (ch == encrypted_char) {
                        found = true;

                        //Turn letter to uppercase if it previously was
                        if (isUppercase) {
                            letter = Character.toUpperCase(letter);
                        }

                        //Store letter in playerGameMapping if a guess has not already been made there
                        if (playerGameMapping[i].equals("- ")) {
                            playerGameMapping[i] = letter + " ";
                            letter = Character.toLowerCase(letter);

                            //Check if user has filled out cryptogram
                            for (String str : playerGameMapping) {
                                if (str.charAt(0) == '-') {
                                    isFull = false;
                                    break;
                                }
                            }
                            //Check if user has successfully completed cryptogram
                            if(isFull){
                                int x = 0;
                                for(String str : playerGameMapping){
                                    if (str.charAt(x) != cryptogram.getPhrase().charAt(x)) {
                                        System.out.print("Incorrect guess!");
                                        return;
                                    }
                                    System.out.print("Correct guess!");
                                    return;
                                }
                            }

                        }
                        //Print error and return if player has already made a guess for the encrypted char
                        else {
                            System.out.println("Error, you have already mapped a guess to " + encrypted_char + "!\n");
                            return;
                        }
                    }
                }
            }
            //Print error if encrypted char was not in the encrypted phrase
            if(!found) {
                System.out.println("Error, " + encrypted_char + " was not found within the cryptogram!\n");
            }
        }
        //Only run if a numbers cryptogram was made
        else if(crypto_type.equals("numbers")) {
            //Append space to end of encrypted guess for easier comparisons
            encrypted_guess = encrypted_guess + " ";

            //Loop through encrypted phrase
            for (int i = 0; i < encrypted_phrase.length; i++) {
                //Get string stored at element of encrypted phrase
                String num = encrypted_phrase[i];

                //If match is found
                if(num.equals(encrypted_guess)) {
                    found = true;
                    //Store letter in playerGameMapping if guess has not already been made there
                    if(playerGameMapping[i].equals("- ")) {
                        playerGameMapping[i] = letter + " ";

                        //Check if user has filled out cryptogram
                        for (String str : playerGameMapping) {
                            if (str.charAt(0) == '-') {
                                isFull = false;
                                break;
                            }
                        }
                        //Check if user has successfully completed cryptogram
                        if(isFull){
                            int x = 0;
                            for(String str : playerGameMapping){
                                if (str.charAt(x) != cryptogram.getPhrase().charAt(x)) {
                                    System.out.print("Incorrect guess!");
                                    return;
                                }
                                System.out.print("Correct guess!");
                                return;
                            }
                        }

                    }
                    //Print error and return if player has already made a guess for the encrypted number
                    else{
                        System.out.println("Error, you have already mapped a guess to " + encrypted_guess.substring(0, encrypted_guess.length()-1) +"!\n");
                        return;
                    }
                }
            }
            //Print error if the encrypted number was not found in the encrypted phrase
            if(!found) {
                System.out.println("Error, " + encrypted_guess.substring(0, encrypted_guess.length()-1) + " was not found within the cryptogram!\n");
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
