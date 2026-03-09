package src.main.java;
import java.util.Scanner;

public class Game {
    private String[] playerGameMapping;
    private String crypto_type = "";
    private String[] guessHistory;
    private int guessCount = 0;

    public static void main(String[] args) {

        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        Players players = new Players();
        printLogin();
        Player player = loadPlayer(players);
        boolean running = true;

        while (running) {

            printMenu();
            System.out.println("\u001b[35mType Here: ");
            System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
            String menu = sc.nextLine();

            if (menu.equals("quit")) {
                running = false;
            } else if (menu.equals("stats")) {
                printStats(player);
                System.out.println("\u001b[35mto quit type anything");
                System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
                String stats = sc.nextLine();
            } else if (menu.equals("load")) {

            } else if (menu.equals("new")) {
                printTitle();
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



                boolean exit = false;

                //Loop until player completes game
                while(!exit) {

                    System.out.print("\n");
                    printCryptogram(cryptogram, game);

                    System.out.println("\n\u001b[35mWhat would you like to do? (enter 'help' to see a list of commands!)");
                    System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");

                    input = sc.nextLine();

                    switch (input) {
                        case "undo" -> {
                            System.out.println("\n\u001b[35mEnter the encrypted letter/number you want to undo:\u001b[0m");
                            System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
                            String undoLetter = sc.nextLine() + " ";
                            //Continue if empty string was entered
                            if(undoLetter.substring(0, undoLetter.length()-1).isEmpty()){
                                continue;
                            }

                            game.undoLetter(cryptogram, undoLetter);
                        }
                        case "exit" -> {
                            System.out.println("\u001b[35mExiting Game...\u001b[0m");
                            exit = true;
                        }
                        case "help" -> help();
                        case "enter" -> {

                            if (game.getCryptoType().equals("letters")) {
                                System.out.println("\n\u001b[35mEnter the letter you want to guess and the encrypted letter you want to map it to: (e.g. c s)");
                            } else {
                                System.out.println("\n\u001b[35mEnter the letter you want to guess and the encrypted number you want to map it to: (e.g. c 17)");
                            }

                            input = sc.nextLine();

                            //Continue if empty string entered
                            if(input.isEmpty()) {
                                continue;
                            }

                            //Take letter from the front of the string
                            char letter = input.charAt(0);
                            //Take encrypted guess from the rest of the string
                            String encrypted_guess = input.substring(1);

                            //Call enterLetter method
                            String completion = game.enterLetter(cryptogram, letter, encrypted_guess);
                            System.out.print("\n");

                            //Check if player completed cryptogram
                            if (completion.equals("Correct")) {
                                printCompleted();
                                exit = true;
                                //Update player stats here
                            } else if (completion.equals("Incorrect")) {
                                System.out.print("\n\u001b[31mYou have incorrectly completed the cryptogram!\n");
                                //Update player stats here
                            }
                        }
                        default -> {System.out.println("Invalid input!");}
                    }
                }
            }
        }
    }


    public void Game(Player p, String cryptType) {}

    public void Game(Player p) {
    }

    public void getHint () {}

    public static Player loadPlayer(Players players) {
        Scanner sc = new Scanner(System.in);
        printLineBreak("Please enter your usename");
        System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
        String input = sc.nextLine();
        Player player = new Player(input);
        if (players.findPlayer(player) == null) {
            players.addPlayer(player);
            System.out.print("\u001b[35mUser not found creating account\u001b[0m\n");
            return player;
        }
        System.out.print("\u001b[35mUser found creating account\u001b[0m\n");
        return players.getPlayer(player.getUsername());
    }

    public void playGame() {}

    public static void printStats(Player p) {
        printLineBreak("Statistics");
        System.out.println("\u001b[34m ◈ Player name: \u001b[35m"+p.getUsername());
        System.out.println("\u001b[34m ◈ Total guesses made: \u001b[35m"+p.getTotalGuesses());
        System.out.println("\u001b[34m ◈ Total cryptograms completed: \u001b[35m"+p.getNumCryptogramsCompleted());
        System.out.println("\u001b[34m ◈ Total cryptograms played: \u001b[35m"+p.getNumCryptogramsPlayed());
        printLineBreak("bottom");
    }

    public static void printLogin() {
        System.out.println("\u001b[38;5;214m╭──────────────────────────────────────────╮\u001b[0m");
        System.out.println("\u001b[34m   ██╗      ██████╗  ██████╗ ██╗███╗   ██╗");
        System.out.println("   ██║     ██╔═══██╗██╔════╝ ██║████╗  ██║");
        System.out.println("   ██║     ██║   ██║██║  ███╗██║██╔██╗ ██║");
        System.out.println("   ██║     ██║   ██║██║   ██║██║██║╚██╗██║");
        System.out.println("   ███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║");
        System.out.println("   ╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝");
        System.out.println("\u001b[38;5;214m╰──────────────────────────────────────────╯\u001b[0m");
    }

    public static void printMenu() {
        System.out.println("\u001b[38;5;214m╭──────────────────────────────────────────╮\u001b[0m");
        System.out.println("\u001b[34m   ███╗   ███╗███████╗███╗   ██╗██╗   ██╗");
        System.out.println("   ████╗ ████║██╔════╝████╗  ██║██║   ██║");
        System.out.println("   ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║");
        System.out.println("   ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║");
        System.out.println("   ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝");
        System.out.println("   ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝");
        System.out.println("\u001b[38;5;214m╰──────────────────────────────────────────╯\u001b[0m");

        printLineBreak("Commands");
        System.out.println("        \u001b[35m               ╔════════════════════════════════════════╗");
        System.out.print  ("        \u001b[35m╔══════════════╣");
        System.out.println("\u001b[34m ◈ new - creates new cryptogram         \u001b[35m║");
        System.out.print  ("        \u001b[35m║  ◈  HELP  ◈  ║");
        System.out.println("\u001b[34m ◈ load - loads saved cryptogram        \u001b[35m║");
        System.out.print  ("        \u001b[35m╚══════════════╣");
        System.out.println("\u001b[34m ◈ quit - quits game fully              \u001b[35m║");
        System.out.print  ("        \u001b[35m               ║");
        System.out.println("\u001b[34m ◈ stats - lists player stats           \u001b[35m║");
        System.out.println("        \u001b[35m               ╚════════════════════════════════════════╝");
        System.out.println("\u001b[0m");
    }

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

    public String trimInput (String string) {
        //Remove any whitespace from players guess
        int i = 0;
        for(char c : string.toCharArray()) {
            if(c == ' '){
                i++;
            }
            else{
                string = string.substring(i);
                break;
            }
        }
        return string;
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

    public String enterLetter(Cryptogram cryptogram, char letter, String encrypted_guess) {

        //Initialise variables
        boolean isUppercase;
        boolean found = false;
        boolean isFull = true;
        boolean alreadyOverriding = false;

        Scanner sc = new Scanner(System.in);

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

        encrypted_guess = trimInput(encrypted_guess);
        letter = Character.toUpperCase(letter);

        //Store encrypted phrase in a variable
        String[] encrypted_phrase = cryptogram.getEncryptedPhrase();

        //Only run if a letters cryptogram was made
        if(crypto_type.equals("letters")) {
            //Get the first char from user input string
            char encrypted_char = encrypted_guess.charAt(0);

            //Make char lowercase to be able to compare with other chars
            encrypted_char = Character.toUpperCase(encrypted_char);


            //Loop for length of encrypted phrase
            for (int i = 0; i < encrypted_phrase.length; i++) {

                //Get char stored at the next element of the encrypted phrase
                char ch = encrypted_phrase[i].charAt(0);

                //Check if char is a letter or a space
                if (Character.isLetter(ch)) {

                    //If a match is found
                    if (ch == encrypted_char) {
                        found = true;

                        //Store letter in playerGameMapping if a guess has not already been made there
                        if (playerGameMapping[i].equals("- ")) {
                            playerGameMapping[i] = letter + " ";
                            guessHistory[guessCount] = encrypted_char + " ";
                            guessCount++;
                            //Update player stats here
                        }

                        //Allow player to override their guess
                        else {
                            if(!alreadyOverriding) {
                                System.out.println("You have already mapped a guess to " + encrypted_char + "!\nPlease enter '1' to override it!\n");
                                String input = sc.nextLine();

                                if(!input.equals("1")){
                                    System.out.println("Not Overriding!\n");
                                    return "Incomplete";
                                }
                                else{
                                    alreadyOverriding = true;
                                }
                            }
                            playerGameMapping[i] = letter + " ";
                        }
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
            for (int i = 0; i < encrypted_phrase.length; i++) {
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
                        //Update player stats here
                    }

                    //Allow player to override their guess
                     else {
                        if(!alreadyOverriding) {
                            System.out.println("You have already mapped a guess to " + encrypted_guess.substring(0, encrypted_guess.length() - 1) + "!\nPlease enter 1 to override it!\n");
                            String input = sc.nextLine();

                            if(!input.equals("1")){
                                System.out.println("Not overriding!");
                                return "Incomplete";
                            }
                            else {
                                alreadyOverriding = true;
                            }
                        }
                        playerGameMapping[i] = letter + " ";
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