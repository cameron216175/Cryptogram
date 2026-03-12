package src.main.java;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Game implements Serializable {
    private String[] playerGameMapping;
    private String crypto_type = "";
    private int guessCount = 0;
    private Player player;


    public Game(Player p, String cryptType) {
        this.player = p;
        Cryptogram cryptogram = this.generateCryptogram(cryptType);

        this.playerGameMapping = new String[cryptogram.phrase.length()];

        this.playerGameMapping = cryptogram.getEncryptedPhrase().clone();
        for(int i = 0; i < this.playerGameMapping.length; i++) {

            if(this.playerGameMapping[i].charAt(0) != ' '){
                this.playerGameMapping[i] = "- ";
            }
        }
        playGame(cryptogram, p);
    }

    public Game(Player p) {
        this.player = p;
        Cryptogram cryptogram = loadGame();

        this.playerGameMapping = new String[cryptogram.phrase.length()];

        this.playerGameMapping = cryptogram.getEncryptedPhrase().clone();
        for(int i = 0; i < this.playerGameMapping.length; i++) {

            if(this.playerGameMapping[i].charAt(0) != ' '){
                this.playerGameMapping[i] = "- ";
            }
        }
        playGame(cryptogram, p);
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

    public void playGame( Cryptogram cryptogram, Player player) {
        // Cryptogram game loop
        boolean exit = false;

        //Loop until player completes game
        while(!exit) {

            System.out.print("\n");
            printCryptogram(cryptogram);

            System.out.println("\n\u001b[35mWhat would you like to do? (enter 'help' to see a list of commands!)");
            System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] inputs = input.split(" ");

            switch (inputs[0]) {
                case "undo" -> {
                    if (inputs.length > 2) {
                        System.out.println("Too many arguments!");
                        continue;
                    } else if (inputs.length < 2) {
                        System.out.println("Too few arguments!");
                        continue;
                    }

                    String undoLetter = inputs[1] + " ";
                    undoLetter(cryptogram, undoLetter);
                }
                case "save" -> {
                    saveGame(cryptogram);
                }
                case "load" -> {
                    cryptogram = loadGame();
                    printCryptogram(cryptogram);
                }
                case "exit" -> {
                    System.out.println("\u001b[35mExiting Game...\u001b[0m");
                    exit = true;
                }
                case "help" -> help();
                case "enter" -> {
                    if (inputs.length > 3) {
                        System.out.println("Too many arguments!");
                        continue;
                    } else if (inputs.length < 3) {
                        System.out.println("Too few arguments!");
                        continue;
                    }
                    //Take letter from the front of the string
                    char letter = inputs[1].charAt(0);
                    //Take encrypted guess from the rest of the string
                    String encrypted_guess = inputs[2];
                    //Call enterLetter method
                    String completion = enterLetter(cryptogram, letter, encrypted_guess);
                    if (completion.equals("Correct")) {
                        printLineBreak(" ");
                        printCompleted();
                        player.incrementCryptogramsCompleted();
                        exit = true;
                    }
                    else if (completion.equals("Incorrect")) {
                        printLineBreak(" ");
                        System.out.println("\u001b[35mIncorrect! Please try again!\u001b[0m");
                    }
                    System.out.print("\n");

                }
                default -> {
                    System.out.println("Invalid input!");
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
        System.out.println("\n\u001b[38;5;214m╠═════════════════════════════════════╡ "+ name +" ╞═════════════════════════════════════╣\u001b[0m");
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
        System.out.println("        \u001b[35m               ╔═════════════════════════════════════════════════════════╗");
        System.out.print  ("        \u001b[35m╔══════════════╣");
        System.out.println("\u001b[34m ◈ help - lists commands                                 \u001b[35m║");
        System.out.print  ("        \u001b[35m║  ◈  HELP  ◈  ║");
        System.out.println("\u001b[34m ◈ exit - quits current cryptogram                       \u001b[35m║");
        System.out.print  ("        \u001b[35m╚══════════════╣");
        System.out.println("\u001b[34m ◈ undo <a/14> - type undo and the indice                \u001b[35m║");
        System.out.print  ("        \u001b[35m               ║");
        System.out.println("\u001b[34m ◈ enter <a> <c/14> - type enter the char and the indice \u001b[35m║");
        System.out.print  ("        \u001b[35m               ║");
        System.out.println("\u001b[34m ◈ save - saves the current cryptogram                   \u001b[35m║");
        System.out.println("        \u001b[35m               ╚═════════════════════════════════════════════════════════╝");
        System.out.println("\u001b[0m");
    }

    public String enterLetter(Cryptogram cryptogram, char letter, String encrypted_guess) {

        //Initialise variables
        boolean isUppercase;
        boolean found = false;
        boolean isFull = true;
        boolean alreadyOverriding = false;
        int position = 0;

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
                            position = i;

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

            } else {
                //Update player stats here
                guessCount++;
                player.incrementTotalGuesses();
                chechGuess(position, cryptogram);
                player.updateAccuracy();
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
            } else {
                guessCount++;
                //Update player stats here
                player.incrementTotalGuesses();
                chechGuess(position, cryptogram);
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

        for (int i = 0; i < cryptogram.getEncryptedPhrase().length; i++) {
            if (cryptogram.getEncryptedPhrase()[i].equalsIgnoreCase(undoLetter)) {
                if (!playerGameMapping[i].equals("- ")) {
                    playerGameMapping[i] = "- ";
                    found = true;
                }
            }
        }

        if(found){
            System.out.println("\u001b[35mUndid guess for: \u001b[34m" + undoLetter + "\u001b[0m");
            guessCount--;
        }
        else{
            System.out.println("\u001b[35mNo guess found for " + undoLetter + "!\u001b[0m");

        }
    }

    public void viewFrequencies() {}

    public void chechGuess(int position, Cryptogram cryptogram) {
        System.out.println("player mapping: '"+playerGameMapping[position].charAt(0) +"'\ncryptogram: '"+cryptogram.getPhrase().charAt(position)+"'");
        if (playerGameMapping[position].charAt(0) == cryptogram.getPhrase().charAt(position)) {player.incrementCorrectGuesses();}
    }

    //SAVE GAME
    public void saveGame(Cryptogram cryptogram) {

        try {

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.ser"));
            out.writeObject(this);
            out.writeObject(cryptogram);
            out.close();
            System.out.println("Game saved...");

        } catch (Exception e) {

            System.out.println("Error saving game: " + e.getMessage());

        }

    }

    //LOAD GAME
    public Cryptogram loadGame() {

        try {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.ser"));

            Game loadedGame = (Game) in.readObject();

            Cryptogram cryptogram = (Cryptogram) in.readObject();

            this.playerGameMapping = loadedGame.playerGameMapping;
            this.guessCount = loadedGame.guessCount;
            this.crypto_type = loadedGame.crypto_type;

            in.close();

            System.out.println("Game loaded...");

            return cryptogram;

        } catch (Exception e) {

            System.out.println("Error loading game: " + e.getMessage());

            return null;

        }

    }

    public void setPlayerGameMapping(String[] playerGameMapping) {
        this.playerGameMapping = playerGameMapping;
    }

    // public void generateCryptogram() {}

    public void showSolution() {}

    public String getCryptoType() {
        return crypto_type;
    }
}