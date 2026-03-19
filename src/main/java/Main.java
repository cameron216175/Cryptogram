package src.main.java;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // Handling players login and create account
        Players players = new Players();
        printLogin();
        Player player = loadPlayer(players);
        boolean running = true;

        while (running) {

            // Menu for creating cryptograms loading and seeing player stats
            printMenu();
            System.out.println("\u001b[35mType Here:");
            System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
            String menu = sc.nextLine();

            switch (menu) {
                case "quit" -> {
                    running = false;
                    System.out.println("\u001b[35mQuitting Game");
                    players.updatePlayer(player);
                    players.savePlayers();
                }
                case "stats" -> {
                    printStats(player);
                    System.out.println("\u001b[35mto quit type anything");
                    System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
                    String stats = sc.nextLine();
                }
                case "new" -> {
                    player.incrementCryptogramsPlayed();
                    printTitle();
                    System.out.println("\u001b[35mEnter 0 for numbers and 1 for letters cryptogram:");
                    System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
                    String input = sc.nextLine();
                    Game game = new Game(player, input);
                    game.playGame();
                }
                case "load" -> {
                    Game loadedGame = Game.loadGame(player);
                    if (loadedGame != null) {
                        loadedGame.playGame();
                    }

                }
            }
        }
    }

    public static void printStats(Player p) {
        p.updateAccuracy();
        printLineBreak("Statistics");
        System.out.println("\u001b[34m ◈ Player name: \u001b[35m"+p.getUsername());
        System.out.println("\u001b[34m ◈ Total guesses made: \u001b[35m"+p.getTotalGuesses());
        System.out.println("\u001b[34m ◈ Total cryptograms completed: \u001b[35m"+p.getNumCryptogramsCompleted());
        System.out.println("\u001b[34m ◈ Total cryptograms played: \u001b[35m"+p.getNumCryptogramsPlayed());
        System.out.println("\u001b[34m ◈ Accuracy: \u001b[35m% "+String.format("%.2f", p.getAccuracy()));
        printLineBreak("     \u001b[34m◈\u001b[38;5;214m      ");
    }

    public static void printLogin() {
        System.out.println("\u001b[38;5;214m╭──────────────────────────────────────────╮");
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
        System.out.print  ("        \u001b[35m║  ◈  MENU  ◈  ║");
        System.out.println("\u001b[34m ◈ load - loads saved cryptogram        \u001b[35m║");
        System.out.print  ("        \u001b[35m╚══════════════╣");
        System.out.println("\u001b[34m ◈ stats - lists player stats           \u001b[35m║");
        System.out.print  ("        \u001b[35m               ║");
        System.out.println("\u001b[34m ◈ quit - closes game                   \u001b[35m║");
        System.out.println("        \u001b[35m               ╚════════════════════════════════════════╝");
        System.out.println("\u001b[0m");
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

    public static Player loadPlayer(Players players) {

        Scanner sc = new Scanner(System.in);
        printLineBreak("Please enter your username");
        System.out.print("\u001b[34m◇\u001b[35m◈\u001b[34m──►\u001b[0m ");
        String input = sc.nextLine();
        Player player = new Player(input);
        if (players.findPlayer(player) == null) {
            players.addPlayer(player);
            System.out.print("\u001b[35mUser not found, creating account. .\u001b[0m\n");
            return player;
        }
        System.out.print("\u001b[35mUser found, logging in. .\u001b[0m\n");
        return players.getPlayer(player.getUsername());
    }

}
