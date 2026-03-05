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

        printTitle();
        Scanner sc = new Scanner(System.in);
        System.out.println("\u001b[35mEnter 0 for numbers and 1 for letters cryptogram:");
        System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
        String input = sc.nextLine();

        cryptogram = game.generateCryptogram(input);

        printLineBreak("The cryptogram is");
        printTable(cryptogram, game);
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
            System.out.println("\n\u001b[35mEnter a letter:");
            System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
            char letter = sc.nextLine().charAt(0);

            String encrypted_guess = "";

            if(game.getCryptoType().equals("letters")){
                System.out.println("\n\u001b[35mEnter the encrypted letter you want to make a guess for:");
                System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
                encrypted_guess = sc.nextLine();
            }
            else if(game.getCryptoType().equals("numbers")){
                System.out.println("\n\u001b[35mEnter the encrypted number you want to make a guess for:");
                System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
                encrypted_guess = sc.nextLine();
            }


            game.enterLetter(cryptogram, letter, encrypted_guess);

            printLineBreak("Player guesses and cryptogram");

            printTable(cryptogram, game);
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

    public static void printTitle() {
        System.out.println("\u001b[38;5;214m╭──────────────────────────────────────────────────────────────────────────────────────────────────────────────╮");
        System.out.println("\u001b[34m    █████████                                  █████                                                         ");
        System.out.println("   ███░░░░░███                                ░░███                                                          ");
        System.out.println("  ███     ░░░  ████████  █████ ████ ████████  ███████    ██████   ███████ ████████   ██████   █████████████  ");
        System.out.println(" ░███         ░░███░░███░░███ ░███ ░░███░░███░░░███░    ███░░███ ███░░███░░███░░███ ░░░░░███ ░░███░░███░░███ ");
        System.out.println(" ░███          ░███ ░░░  ░███ ░███  ░███ ░███  ░███    ░███ ░███░███ ░███ ░███ ░░░   ███████  ░███ ░███ ░███ ");
        System.out.println(" ░░███     ███ ░███      ░███ ░███  ░███ ░███  ░███ ███░███ ░███░███ ░███ ░███      ███░░███  ░███ ░███ ░███ ");
        System.out.println("  ░░█████████  █████     ░░███████  ░███████   ░░█████ ░░██████ ░░███████ █████    ░░████████ █████░███ █████");
        System.out.println("   ░░░░░░░░░  ░░░░░       ░░░░░███  ░███░░░     ░░░░░   ░░░░░░   ░░░░░███░░░░░      ░░░░░░░░ ░░░░░ ░░░ ░░░░░");
        System.out.println("                          ███ ░███  ░███                         ███ ░███                                    ");
        System.out.println("                         ░░██████   █████                       ░░██████                                     ");
        System.out.println("                          ░░░░░░   ░░░░░                         ░░░░░░                                      ");
        System.out.println("\u001b[38;5;214m╰──────────────────────────────────────────────────────────────────────────────────────────────────────────────╯\u001b[0m");
    }

    public static void printLineBreak(String name) {
        System.out.println("\n\u001b[38;5;214m╠═════════════════════════════════════╡ "+ name +" ╞═════════════════════════════════════╣\u001b[0m\n");
    }

    public static void printTable(Cryptogram cryptogram, Game game) {
        String[] table = cryptogram.getEncryptedPhrase();
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < cryptogram.getEncryptedPhrase().length; i++) {
                switch (x) {
                    case 0 : {
                        if (i == 0) {
                            System.out.print("\u001b[38;5;214m╭─────╥");
                        } else if (i == cryptogram.getEncryptedPhrase().length - 1) {
                            System.out.println("\u001b[38;5;214m─────╮");
                        } else {
                            System.out.print("\u001b[38;5;214m─────╥");
                        }
                        break;
                    }
                    case 1 : {
                        String display;
                        if (table[i].equals("  ")) {
                            display = "░";
                        } else if (game.playerGameMapping != null && !game.playerGameMapping[i].equals("  ") &&  !game.playerGameMapping[i].equals("- ")) {
                            display = "\u001b[32m"+String.valueOf(game.playerGameMapping[i].charAt(0));
                        } else {
                            display = "-";
                        }
                        System.out.print("\u001b[34m   " + display + "  \u001b[0m");
                        break;
                    }
                    case 2 : {
                        if (i == 0) {
                            System.out.print("\n\u001b[38;5;214m╰─────╫");
                        } else if (i == cryptogram.getEncryptedPhrase().length - 1) {
                            System.out.println("\u001b[38;5;214m─────╯");

                        } else {
                            System.out.print("\u001b[38;5;214m─────╫");
                        }
                        break;
                    }
                    case 3 : {
                        String label = table[i];
                        System.out.print("\u001b[34m   " + label + " \u001b[0m");
                        break;
                    }
                }


            }
        }


    }

    public Cryptogram generateCryptogram(String input) {
        if (input.equals("0")) {
            crypto_type = "numbers";
            return new NumberCryptogram();
        }

        else if (input.equals("1")) {
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

        //Print error if player game mapping is full
        for (String str : playerGameMapping) {
            if (str.charAt(0) != '-') {
                isFull = false;
                break;
            }
        }
        if(isFull){
            System.out.println("Error, you have already made a guess for every encrypted value!");
            return;
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
                System.out.println("Error, " + encrypted_char + "was not found within the cryptogram!\n");
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
