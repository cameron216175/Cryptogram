package src.main.java;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;


public class Game implements Serializable {
    private Cryptogram cryptogram;
    private Player player;
    private ArrayList<String> playerGameMapping = new ArrayList<>();
    private String crypto_type = "";
    private int guessCount = 0;


    public Game(Player p, String cryptType) {
        this.player = p;
        this.cryptogram = this.generateCryptogram(cryptType);
        player.incrementCryptogramsPlayed();

        this.playerGameMapping.addAll(Arrays.asList(cryptogram.getEncryptedPhrase()));

        for (int i = 0; i < this.playerGameMapping.size(); i++) {
            if (this.playerGameMapping.get(i).charAt(0) != ' ') {
                this.playerGameMapping.set(i, "- ");
            }
        }
    }

    public void playGame() {
        // Cryptogram game loop
        boolean exit = false;

        //Loop until player completes game
        while (!exit) {

            System.out.print("\n");
            printCryptogram(this.cryptogram);

            System.out.println("\n\u001b[34mWhat would you like to do? (enter 'help' to see a list of commands!)");
            System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] inputs = input.split(" ");

            switch (inputs[0]) {
                case "undo" -> {
                    if (inputs.length > 2) {
                        System.out.println("\u001B[31mToo many arguments!");
                        continue;
                    } else if (inputs.length < 2) {
                        System.out.println("\u001B[31mToo few arguments!");
                        continue;
                    }

                    String undoLetter = inputs[1] + " ";
                    undoLetter(this.cryptogram, undoLetter);
                }
                case "save" -> {
                    saveGame();
                }
                case "exit" -> {
                    System.out.println("\u001b[34mExiting Game...\u001b[0m");
                    exit = true;
                }
                case "help" -> help();
                case "enter" -> {
                    if (inputs.length > 3) {
                        System.out.println("\u001B[31mToo many arguments!");
                        continue;
                    } else if (inputs.length < 3) {
                        System.out.println("\u001B[31mToo few arguments!");
                        continue;
                    }
                    //Take letter from the front of the string
                    char letter = inputs[1].charAt(0);
                    //Take encrypted guess from the rest of the string
                    String encrypted_guess = inputs[2];
                    //Call enterLetter method
                    String completion = enterLetter(this.cryptogram, letter, encrypted_guess);

                    exit = checkCompletion(completion);
                }
                case "hint" -> {
                    getHint();
                }
                case "solt" -> {
                    showSolution();
                    exit = true;
                    System.out.println("\u001b[34mto quit type anything");
                    System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
                    String stats = sc.nextLine();

                }
                case "freq"->{
                    viewFrequencies();
                }
                default -> {
                    System.out.println("\u001B[31mInvalid input!");
                    continue;
                }
            }
        }
    }

    public static void printCompleted() {
        System.out.println("\u001b[38;5;214m ██████╗ ██████╗ ███╗   ███╗██████╗ ██╗     ███████╗████████╗███████╗██████╗ ██╗");
        System.out.println("██╔════╝██╔═══██╗████╗ ████║██╔══██╗██║     ██╔════╝╚══██╔══╝██╔════╝██╔══██╗██║");
        System.out.println("██║     ██║   ██║██╔████╔██║██████╔╝██║     █████╗     ██║   █████╗  ██║  ██║██║");
        System.out.println("██║     ██║   ██║██║╚██╔╝██║██╔═══╝ ██║     ██╔══╝     ██║   ██╔══╝  ██║  ██║╚═╝");
        System.out.println("╚██████╗╚██████╔╝██║ ╚═╝ ██║██║     ███████╗███████╗   ██║   ███████╗██████╔╝██╗");
        System.out.println(" ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚══════╝╚══════╝   ╚═╝   ╚══════╝╚═════╝ ╚═╝\u001b[0m");
    }


    public static void printLineBreak(String name) {
        System.out.println("\n\u001b[38;5;214m╠═════════════════════════════════════╡ " + name + " ╞═════════════════════════════════════╣\u001b[0m");
    }

    public void printCryptogram(Cryptogram cryptogram) {
        String[] encrypted = cryptogram.getEncryptedPhrase();

        printLineBreak("Cryptogram");
        for (String s : encrypted) System.out.printf("%-3s", s);
        System.out.println();

        if (this.playerGameMapping != null) {
            for (String s : this.playerGameMapping) {
                if (s.equals("- ")) {
                    System.out.printf("%-3s", s);
                } else {
                    System.out.printf("\u001b[32m%-3s\u001b[0m", s);
                }

            }
            System.out.println();
        } else {
            for (String s : encrypted) System.out.println("&-3s");
        }
    }


    public Cryptogram generateCryptogram(String input) {
        if (input.equals("0")) {
            crypto_type = "numbers";
            return new NumberCryptogram();
        } else if (input.equals("1")) {
            crypto_type = "letters";
            return new LetterCryptogram();
        } else {
            System.out.println("Invalid input\n");
            return null;
        }
    }

    public String trimInput(String string) {
        //Remove any whitespace from players guess
        int i = 0;
        for (char c : string.toCharArray()) {
            if (c == ' ') {
                i++;
            } else {
                string = string.substring(i);
                break;
            }
        }
        return string;
    }

    public static void help() {
        printLineBreak("Commands");
        System.out.println("        \u001b[34m               ╔═════════════════════════════════════════════════════════╗");
        System.out.print("        \u001b[34m╔══════════════╣");
        System.out.println("\u001b[38;5;214m ◈ help - lists commands                                 \u001b[34m║");
        System.out.print("        \u001b[34m║  \u001b[38;5;214m◈  HELP  ◈\u001b[34m  ║");
        System.out.println("\u001b[38;5;214m ◈ exit - quits current cryptogram                       \u001b[34m║");
        System.out.print("        \u001b[34m╚══════════════╣");
        System.out.println("\u001b[38;5;214m ◈ undo <a/14> - type undo and the indice                \u001b[34m║");
        System.out.print("        \u001b[34m               ║");
        System.out.println("\u001b[38;5;214m ◈ enter <a> <c/14> - type enter the char and the indice \u001b[34m║");
        System.out.print("        \u001b[34m               ║");
        System.out.println("\u001b[38;5;214m ◈ hint - Gives a letter in the correct place            \u001b[34m║");
        System.out.print("        \u001b[34m               ║");
        System.out.println("\u001b[38;5;214m ◈ save - saves the current cryptogram                   \u001b[34m║");
        System.out.print("        \u001b[34m               ║");
        System.out.println("\u001b[38;5;214m ◈ freq - view letter frequencies                        \u001b[34m║");
        System.out.print("        \u001b[34m               ║");
        System.out.println("\u001b[38;5;214m ◈ solt - reveals the entire cryptogram solution         \u001b[34m║");
        System.out.println("        \u001b[34m               ╚═════════════════════════════════════════════════════════╝");
        System.out.println("\u001b[0m");
    }

    public String enterLetter(Cryptogram cryptogram, char letter, String encrypted_guess) {

        //Initialise variables
        boolean found = false;
        boolean isFull = true;
        boolean alreadyOverriding = false;
        int position = 0;

        Scanner sc = new Scanner(System.in);

        //Print error and return if guess isn't a letter
        if (!Character.isLetter(letter)) {
            System.out.println("\u001b[31mInvalid input, \u001b[34m" + letter + "\u001b[31m is not a letter!\n");
            return "Error";
        }

        //Print error and return if player has already guessed the letter
        for (String str : playerGameMapping) {
            if (str.charAt(0) == Character.toLowerCase(letter) || str.charAt(0) == Character.toUpperCase(letter)) {
                System.out.println("\u001b[31mError, you have already guessed \u001b[34m" + letter + "\u001b[31m as an answer!");
                return "Error";
            }
        }

        encrypted_guess = trimInput(encrypted_guess);

        letter = Character.toUpperCase(letter);

        //Store encrypted phrase in a variable
        String[] encrypted_phrase = cryptogram.getEncryptedPhrase();

        //Only run if a letters cryptogram was made
        if (crypto_type.equals("letters")) {
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
                        if (playerGameMapping.get(i).equals("- ")) {
                            playerGameMapping.set(i, letter + " ");
                            position = i;

                        }

                        //Allow player to override their guess
                        else {
                            if (!alreadyOverriding) {
                                System.out.println("\u001b[31mYou have already mapped a guess to \u001b[34m" + encrypted_char + "\u001b[31m!\nPlease enter '1' to override it!\n");
                                System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
                                String input = sc.nextLine();

                                if (!input.equals("1")) {
                                    System.out.println("\u001b[31mNot Overriding!\n");
                                    return "Incomplete";
                                } else {
                                    alreadyOverriding = true;
                                }
                            }
                            playerGameMapping.set(i, letter + " ");
                        }
                    }
                }
            }
            //Print error if encrypted char was not in the encrypted phrase
            if (!found) {
                System.out.println("\u001b[31mError, \u001b[34m" + encrypted_char + "\u001b[31m was not found within the cryptogram!");
                return "Error";

            } else {
                //Update player stats here
                guessCount++;
                player.incrementTotalGuesses();
                checkGuess(cryptogram, letter);
                player.updateAccuracy();
            }
        }
        //Only run if a numbers cryptogram was made
        else if (crypto_type.equals("numbers")) {
            //Append space to end of encrypted guess for easier comparisons
            encrypted_guess = encrypted_guess + " ";

            //Loop through encrypted phrase
            for (int i = 0; i < encrypted_phrase.length; i++) {
                //Get string stored at element of encrypted phrase
                String num = encrypted_phrase[i];

                //If match is found
                if (num.equals(encrypted_guess)) {
                    found = true;

                    //Store letter in playerGameMapping if guess has not already been made there
                    if (playerGameMapping.get(i).equals("- ")) {
                        playerGameMapping.set(i, letter + " ");
                        position = i;
                    }

                    //Allow player to override their guess
                    else {
                        if (!alreadyOverriding) {
                            System.out.println("\u001b[31mYou have already mapped a guess to \u001b[34m" + encrypted_guess.substring(0, encrypted_guess.length() - 1) + "\u001b[31m!\nPlease enter 1 to override it!\n");
                            System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
                            String input = sc.nextLine();

                            if (!input.equals("1")) {
                                System.out.println("\u001b[31mNot overriding!");
                                return "Incomplete";
                            } else {
                                alreadyOverriding = true;
                            }
                        }
                        playerGameMapping.set(i, letter + " ");
                    }
                }
            }
            //Print error if the encrypted number was not found in the encrypted phrase
            if (!found) {
                System.out.println("\u001b[31mError, \u001b[34m" + encrypted_guess.substring(0, encrypted_guess.length() - 1) + "\u001b[31m was not found within the cryptogram!");
                return "Error";
            } else {
                guessCount++;
                //Update player stats here
                player.incrementTotalGuesses();
                checkGuess(cryptogram, letter);
                player.updateAccuracy();
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
        if (isFull) {
            int x = 0;
            for (String str : playerGameMapping) {
                if (str.charAt(0) == ' ') {
                    x++;
                    continue;
                }
                if (Character.toLowerCase(str.charAt(0)) != Character.toLowerCase(cryptogram.getPhrase().charAt(x))) {
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
            System.out.println("\u001b[31mNothing to undo!\u001b[0m\n");
            return;
        }

        boolean found = false;

        for (int i = 0; i < cryptogram.getEncryptedPhrase().length; i++) {
            if (cryptogram.getEncryptedPhrase()[i].equalsIgnoreCase(undoLetter)) {
                if (!playerGameMapping.get(i).equals("- ")) {
                    playerGameMapping.set(i, "- ");
                    found = true;
                }
            }
        }

        if (found) {
            System.out.println("\u001b[34mUndid guess for: \u001b[34m" + undoLetter + "\u001b[0m");
            guessCount--;
        } else {
            System.out.println("\u001b[31mNo guess found for " + undoLetter + "!\u001b[0m");

        }
    }

    public void checkGuess(Cryptogram cryptogram, char letter) {
        String phrase = cryptogram.getPhrase();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toUpperCase(phrase.charAt(i)) == Character.toUpperCase(letter)) {
                player.incrementCorrectGuesses();
                return; // only count once per guess, not per occurrence
            }
        }
    }

    //SAVE GAME
    public void saveGame() {

        String filename = "savegame_" + player.getUsername() + ".ser";
        File file = new File(filename);

        // Check if save file already exists and prompt for overwrite
        if (file.exists()) {
            System.out.println("\u001b[34mA saved game already exists. Enter '1' to overwrite it, anything else to cancel:");
            System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (!input.equals("1")) {
                System.out.println("\u001b[34mSave cancelled.");
                return;
            }
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
            out.close();
            System.out.println("Game saved...");
        } catch (Exception e) {

            System.out.println("Error saving game: " + e.getMessage());

        }

    }

    //LOAD GAME
    public static Game loadGame(Player player) {

        String filename = "savegame_" + player.getUsername() + ".ser";
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            Game game = (Game) in.readObject();
            //Cryptogram cryptogram = (Cryptogram) in.readObject();
            //this.playerGameMapping = loadedGame.playerGameMapping;
            //this.guessCount = loadedGame.guessCount;
            //this.crypto_type = loadedGame.crypto_type;
            in.close();
            System.out.println("Game loaded...");
            //return cryptogram;
            return game;
        } catch (Exception e) {
            System.out.println("\u001b[31mError loading game: " + e.getMessage());
            return null;
        }

    }

    public void setPlayerGameMapping(ArrayList<String> playerGameMapping) {
        this.playerGameMapping = playerGameMapping;
    }

    public ArrayList<String> getPlayerGameMapping() {
        return playerGameMapping;
    }

    // public void generateCryptogram() {}

    public String getSolution() {
        return cryptogram.getPhrase();
    }

    public void showSolution() {
        System.out.println("\u001b[34m╔════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                        SOLUTION                                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════╣");

        System.out.println("\u001b[38;5;214m   " + getSolution());

        System.out.println("\u001b[34m╚════════════════════════════════════════════════════════════════════════════════════════╝\u001b[0m");
    }

    public void viewFrequencies() {
        HashMap<Character, Double> englishFrequencies = new HashMap<>();
        //frequency of letters in the english language just used cornell website table for values
        englishFrequencies.put('E', 12.02);englishFrequencies.put('T', 9.10);englishFrequencies.put('A', 8.12);
        englishFrequencies.put('O', 7.68);englishFrequencies.put('I', 7.31);englishFrequencies.put('N', 6.95);
        englishFrequencies.put('S', 6.28);englishFrequencies.put('R', 6.02);englishFrequencies.put('H', 5.92);
        englishFrequencies.put('D', 4.32);englishFrequencies.put('L', 3.98);englishFrequencies.put('U', 2.88);
        englishFrequencies.put('C', 2.71);englishFrequencies.put('M', 2.61);englishFrequencies.put('F', 2.30);
        englishFrequencies.put('Y', 2.11);englishFrequencies.put('W', 2.09);englishFrequencies.put('G', 2.03);
        englishFrequencies.put('P', 1.82);englishFrequencies.put('B', 1.49);englishFrequencies.put('V', 1.11);
        englishFrequencies.put('K', 0.69);englishFrequencies.put('X', 0.17);englishFrequencies.put('Q', 0.11);
        englishFrequencies.put('J', 0.10);englishFrequencies.put('Z', 0.07);

        HashMap<String, Integer> cryptoFrequencies = this.cryptogram.getFrequencies();
        int total = 0;
        for (int count : cryptoFrequencies.values()) {
            total += count;
        }
        // Header
        System.out.println("\u001b[34m╔════════════════════════════════════════════╗");
        System.out.println("║          LETTER FREQUENCY ANALYSIS         ║");
        System.out.println("╠═══════╦════════════════╦═══════════════════╣");
        // Column titles
        System.out.printf("║ %-5s ║ %-14s ║ %-17s ║\n", "Char", "Cryptogram %", "English %");
        System.out.println("╠═══════╬════════════════╬═══════════════════╣");
        for (char c = 'A'; c <= 'Z'; c++) {
            String key = String.valueOf(c);
            double frequency = (Double)englishFrequencies.get(c);
            if (cryptoFrequencies.containsKey(key)) {
                int count = (Integer)cryptoFrequencies.get(key);
                double cryptogramPercentage = (double)count * (double)100.0F / (double)total;
                System.out.printf(
                        "\u001b[38;5;214m║ \u001b[34m%-5c \u001b[38;5;214m║ \u001b[34m%12.2f %% \u001b[38;5;214m║ \u001b[34m%15.2f %% \u001b[38;5;214m║\n",
                        c,
                        cryptogramPercentage,
                        frequency
                );
            } else {
                System.out.printf(
                        "\u001b[38;5;214m║ \u001b[34m%-5c \u001b[38;5;214m║ %12.2f %% ║ %15.2f %% ║\n",
                        c,
                        0.0,
                        frequency
                );
            }
        }
        // Footer
        System.out.println("\u001b[34m╚═══════╩════════════════╩═══════════════════╝\u001b[0m");
        }

    public void getHint() {

        String phrase = cryptogram.getPhrase();

        ArrayList<Character> unguessed = new ArrayList<>();
        for (int i = 0; i < phrase.length(); i++) {
            char c = Character.toUpperCase(phrase.charAt(i));
            if ((playerGameMapping.get(i).equals("- ") || phrase.charAt(i) != playerGameMapping.get(i).charAt(0)) && phrase.charAt(i) != '"' && phrase.charAt(i) != ' ' && !unguessed.contains(c) ) {
                unguessed.add(c);
            }
        }

        // Checks if the user can actually be given a hint
        if(unguessed.isEmpty()){
            System.out.println("\u001b[34mNo more hints were found.");
            return;
        } else if (unguessed.size() == 1) {
            System.out.println("\u001b[34mThere is only one letter left, you are not allowed to use anymore hints");
            return;
        }

        // System chooses random letter from unguessed letters
        Random rand = new Random();
        char hintLetter = unguessed.get(rand.nextInt(unguessed.size()));

        // Remove any incorrect guesses of a letter if it's the hint letter
        for (int i = 0; i < playerGameMapping.size(); i++) {
            String s = playerGameMapping.get(i);
            if (s.charAt(0) != '-' && Character.toUpperCase(s.charAt(0)) == Character.toUpperCase(hintLetter)){
                playerGameMapping.set(i, "- ");
            }
        }

        // Reveal all positions of the hint letter
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toUpperCase(phrase.charAt(i)) == Character.toUpperCase(hintLetter)) {
                playerGameMapping.set(i, phrase.charAt(i) + " ");
            }
        }

        player.incrementTotalGuesses();
        player.incrementCorrectGuesses();
        player.updateAccuracy();

        System.out.println("\u001b[34mHint: Here are all the positions for the letter \u001b[38;5;214m" + hintLetter);
    }

    public String getCryptoType() {
        return crypto_type;
    }

    public Cryptogram getCryptogram() {
        return cryptogram;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean checkCompletion(String completion){

        boolean exit = false;

        if (completion.equals("Correct")) {
            printLineBreak(" ");
            System.out.println();
            printCompleted();
            printLineBreak(" ");
            this.player.incrementCryptogramsCompleted();
            String filename = "savegame_" + player.getUsername() + ".ser";
            File file = new File(filename);
            if (file.exists()) {
                try {
                    if (!file.delete()) {
                        System.out.println("File deletion failed.");
                    }
                } catch (Exception e) {
                    System.out.println("File deletion failed." + e.getMessage());
                }
            }
            exit = true;
        }

        else if (completion.equals("Incorrect")) {
            printLineBreak(" ");
            System.out.println("\u001b[34mIncorrect! Please try again!\u001b[0m");
        }
        System.out.print("\n");

        return exit;
    }
}