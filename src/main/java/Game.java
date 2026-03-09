package src.main.java;
import java.util.Scanner;

public class Game {
    private String[] playerGameMapping;
    private String crypto_type = "";
    private String[] guessHistory;
    private int guessCount = 0;

    public static void main(String[] args) {

        Game game = new Game();

        printTitle();

        Scanner sc = new Scanner(System.in);

        System.out.println("\u001b[35mEnter 0 for numbers and 1 for letters cryptogram:");
        System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
        String input = sc.nextLine();

        Cryptogram cryptogram = game.generateCryptogram(input);

        game.guessHistory = new String[cryptogram.getEncryptedPhrase().length];

        game.playerGameMapping = new String[cryptogram.phrase.length()];

        game.playerGameMapping = cryptogram.getEncryptedPhrase().clone();
        for(int i = 0; i < game.playerGameMapping.length; i++) {

            if(game.playerGameMapping[i].charAt(0) != ' '){
                game.playerGameMapping[i] = "- ";
            }
        }

        printCryptogram(cryptogram, game);
        System.out.print("\n");

        boolean exit = false;

        //Loop until player completes game
        while(!exit) {

            String encrypted_guess = "";
            char letter;

            //Get guess from player
            if (game.getCryptoType().equals("letters")) {
                System.out.println("\n\u001b[35mEnter the letter you want to guess and the encrypted letter you want to map it to: (e.g. c s) or type help to list commands");
                System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
            } else {
                System.out.println("\n\u001b[35mEnter the letter you want to guess and the encrypted number you want to map it to: (e.g. c 5) or type help to list commands");
                System.out.println("\n\u001b[35mEnter your guess followed by the encrypted letter (e.g. ac to guess that c decrypts to a):");
                System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
            }

            String letterGuess = sc.nextLine();
            if (letterGuess.equals("undo")) {
                System.out.println("\n\u001b[35mEnter the encrypted letter/number you want to undo:\u001b[0m");
                System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
                String undoLetter = sc.nextLine() + " ";
                game.undoLetter(cryptogram, undoLetter);
                printCryptogram(cryptogram, game);
                continue;
            } else if (letterGuess.equals("exit")) {
                System.out.println("\u001b[35mExiting Game...\u001b[0m");
                exit = true;
                continue;
            } else if (letterGuess.equals("help")) {
                help();
                continue;
            }
            letter = letterGuess.charAt(0);
            encrypted_guess = letterGuess.substring(1);

            //Call enterLetter method
            String completion = game.enterLetter(cryptogram, letter, encrypted_guess);

            System.out.print("\n");


            printCryptogram(cryptogram, game);
            System.out.print("\n");

            //Print success message if player won

            //Print fail message if player lost

            if (completion.equals("Correct")) {
                printCompleted();
                exit = true;
            } else if (completion.equals("Incorrect")) {
                System.out.print("\n\u001b[31mYou have incorrectly completed the cryptogram!\n");

            }
        }
    }


    public void Game(Player p, String cryptType) {}

    public void Game(Player p) {
    }

    public void getHint () {}

    public void loadPlayer() {}

    public void playGame() {}

    public static void printCompleted() {
        System.out.println("\u001b[38;5;214m ██████╗ ██████╗ ███╗   ███╗██████╗ ██╗     ███████╗████████╗███████╗██████╗ ██╗");
        System.out.println("██╔════╝██╔═══██╗████╗ ████║██╔══██╗██║     ██╔════╝╚══██╔══╝██╔════╝██╔══██╗██║");
        System.out.println("██║     ██║   ██║██╔████╔██║██████╔╝██║     █████╗     ██║   █████╗  ██║  ██║██║");
        System.out.println("██║     ██║   ██║██║╚██╔╝██║██╔═══╝ ██║     ██╔══╝     ██║   ██╔══╝  ██║  ██║╚═╝");
        System.out.println("╚██████╗╚██████╔╝██║ ╚═╝ ██║██║     ███████╗███████╗   ██║   ███████╗██████╔╝██╗");
        System.out.println(" ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚══════╝╚══════╝   ╚═╝   ╚══════╝╚═════╝ ╚═╝\u001b[0m");
    }

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
        System.out.println("\n\u001b[38;5;214m╠═════════════════════════════════════╡ "+ name +" ╞═════════════════════════════════════╣\u001b[0m");
    }

   public static void printCryptogram(Cryptogram cryptogram, Game game) {
       String[] encrypted = cryptogram.getEncryptedPhrase();

       printLineBreak("Cryptogram");
       for (String s : encrypted) System.out.printf("%-3s", s);
       System.out.println();

       if (game.playerGameMapping != null) {
           for (String s : game.playerGameMapping) {
               if (s.equals("- ")) {
                   System.out.printf("%-3s", s);
               } else {
                   System.out.printf("\u001b[32m%-3s\u001b[0m", s);
               }

           }
           System.out.println();
       } else {
           for (String s: encrypted) System.out.println("&-3s");
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

    public String trimInput (String encrypted_guess) {
        //Remove any whitespace from players guess
        int i = 0;
        for(char c : encrypted_guess.toCharArray()) {
            if(c == ' '){
                i++;
            }
            else{
                encrypted_guess = encrypted_guess.substring(i);
                break;
            }
        }
        return encrypted_guess;
    }

    public static void help() {
        printLineBreak("Commands");
        System.out.println("        \u001b[35m               ╔════════════════════════════════════════╗");
        System.out.print  ("        \u001b[35m╔══════════════╣");
        System.out.println("\u001b[34m ◈ help - lists commands                \u001b[35m║");
        System.out.print  ("        \u001b[35m║  ◈  HELP  ◈  ║");
        System.out.println("\u001b[34m ◈ exit - quits current cryptogram      \u001b[35m║");
        System.out.print  ("        \u001b[35m╚══════════════╣");
        System.out.println("\u001b[34m ◈ undo - type encrypted indice to undo \u001b[35m║");
        System.out.println("        \u001b[35m               ╚════════════════════════════════════════╝");
        System.out.println("\u001b[0m");
    }

    public String isValidGuess(char letter, String encrypted_guess) {
        //Print error and return if guess isn't a letter
        if(!Character.isLetter(letter)){
            System.out.println("Invalid input, " + letter + " is not a letter!\n");
            return "Error";
        }



        //Print error and return if player has already guessed the letter
        for (String str : playerGameMapping) {
            if (str.charAt(0) == Character.toLowerCase(letter) || str.charAt(0) == Character.toUpperCase(letter)) {
                System.out.println("\u001b[31mError, you have already guessed " + letter + " as an answer!");
                return "Error";
            }
        }
        return encrypted_guess;
    }

    public String enterLetter(Cryptogram cryptogram, char letter, String encrypted_guess) {

        //Initialise variables
        boolean isUppercase;
        boolean found = false;
        boolean isFull = true;
        boolean yes_override = false;
        boolean no_override = false;

        Scanner sc = new Scanner(System.in);

        //Print error and return if guess isn't a letter
        if(!Character.isLetter(letter)){
            System.out.println("Invalid input, " + letter + " is not a letter!\n");
            return "Error";
        }

        //Remove any whitespace from players guess
        int i = 0;
        for(char c : encrypted_guess.toCharArray()) {
            if(c == ' '){
                i++;
            }
            else{
                encrypted_guess = encrypted_guess.substring(i);
                break;
            }
        }

        //Print error and return if player has already guessed the letter
        for (String str : playerGameMapping) {
            if (str.charAt(0) == Character.toLowerCase(letter) || str.charAt(0) == Character.toUpperCase(letter)) {
                System.out.println("\u001b[31mError, you have already guessed " + letter + " as an answer!");
                return "Error";
            }
        }

        letter = Character.toLowerCase(letter);

        //Store encrypted phrase in a variable
        String[] encrypted_phrase = cryptogram.getEncryptedPhrase();

        //Only run if a letters cryptogram was made
        if(crypto_type.equals("letters")) {
            //Get the first char from user input string
            char encrypted_char = encrypted_guess.charAt(0);

            //Make char lowercase to be able to compare with other chars
            encrypted_char = Character.toLowerCase(encrypted_char);


            //Loop for length of encrypted phrase
            for (i = 0; i < encrypted_phrase.length; i++) {

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
                            guessHistory[guessCount] = encrypted_char + " ";
                            guessCount++;
                        }

                        //Allow player to override their guess
                        else {
                            if(!yes_override && !no_override) {
                                System.out.println("You have already mapped a guess to " + encrypted_char + "!\nPlease enter '1' to override it!\n");
                                String input = sc.nextLine();

                                if(input.equals("1")){
                                    System.out.println("Overriding!");
                                    yes_override = true;
                                }
                                else {
                                    System.out.println("Not overriding!");
                                    no_override = true;

                                }
                            }
                            if(yes_override){
                                playerGameMapping[i] = letter + " ";
                            }
                            letter = Character.toLowerCase(letter);
                        }

                        letter = Character.toLowerCase(letter);
                    }
                }
            }
            //Print error if encrypted char was not in the encrypted phrase
            if(!found) {
                System.out.println("\u001b[31mError, " + encrypted_char + " was not found within the cryptogram!");
                return "Error";
            }
        }
        //Only run if a numbers cryptogram was made
        else if(crypto_type.equals("numbers")) {
            //Append space to end of encrypted guess for easier comparisons
            encrypted_guess = encrypted_guess + " ";

            //Loop through encrypted phrase
            for (i = 0; i < encrypted_phrase.length; i++) {
                //Get string stored at element of encrypted phrase
                String num = encrypted_phrase[i];

                //If match is found
                if(num.equals(encrypted_guess)) {
                    found = true;

                    //Store letter in playerGameMapping if guess has not already been made there
                    if(playerGameMapping[i].equals("- ")) {
                        playerGameMapping[i] = letter + " ";
                        guessHistory[guessCount] = encrypted_guess;
                        guessCount++;
                    }

                    //Allow player to override their guess
                    else {
                        if(!yes_override && !no_override) {
                            System.out.println("You have already mapped a guess to " + encrypted_guess.substring(0, encrypted_guess.length() - 1) + "!\nPlease enter 1 to override it!\n");
                            String input = sc.nextLine();

                            if(input.equals("1")){
                                System.out.println("Overriding!");
                                yes_override = true;
                            }
                            else {
                                System.out.println("Not overriding!");
                                no_override = true;

                            }
                        }
                        if(yes_override){
                            playerGameMapping[i] = letter + " ";
                        }

                    }
                }
            }
            //Print error if the encrypted number was not found in the encrypted phrase
            if(!found) {
                System.out.println("\u001b[31mError, " + encrypted_guess.substring(0, encrypted_guess.length()-1) + " was not found within the cryptogram!");
                return "Error";
            }
        }

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
                if(str.charAt(0) == ' '){
                    x++;
                    continue;
                }
                if(Character.toLowerCase(str.charAt(0)) != Character.toLowerCase(cryptogram.getPhrase().charAt(x))){
                    return "Incorrect";
                }
                x++;
            }
            return "Correct";
        }

        return "Incomplete";
    }

    public void undoLetter(Cryptogram cryptogram, String undoLetter) {
        if (guessCount == 0) {
            System.out.println("\u001b[35mNothing to undo!\u001b[0m\n");
            return;
        }
        boolean found = false;
        for (int i = 0; i < guessCount; i++) {
            if (guessHistory[i].equals(undoLetter)) {
                found = true;
                for (int j = i; j < guessCount; j++) {
                    guessHistory[j] = guessHistory[j + 1];
                }
                guessHistory[guessCount - 1] = "";
                guessCount--;
                break;

            }
        }
        if (!found) {
            System.out.println("\u001b[35mNo guess found for: " + undoLetter + "\u001b[0m\n");
        } else {
            String[] encrypted_phrase = cryptogram.getEncryptedPhrase();

            for (int i = 0; i < encrypted_phrase.length; i++) {
                if (encrypted_phrase[i].equalsIgnoreCase(undoLetter)) {
                    playerGameMapping[i] = "- ";
                }
            }
            System.out.println("\u001b[35mUndid guess for: \u001b[34m" + undoLetter + "\u001b[0m");
        }
    }

    public void viewFrequencies() {}

    public void saveGame() {}

    public void loadGame() {}

    // public void generateCryptogram() {}

    public void showSolution() {}

    public String getCryptoType() {
        return crypto_type;
    }
}