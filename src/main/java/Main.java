package src.main.java;

import java.io.IOException;
import java.util.ArrayList;
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
            System.out.println("\u001b[34mType Here:");
            System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
            String menu = sc.nextLine();

            switch (menu) {
                case "quit" -> {
                    running = false;
                    System.out.println("\u001b[34mQuitting Game");
                    players.updatePlayer(player);
                    players.savePlayers();
                }
                case "stats" -> {
                    printStats(player);
                    System.out.println("\u001b[34mto quit type anything");
                    System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
                    String stats = sc.nextLine();
                }
                case "top" -> {
                    printLeaderboard(players);
                    System.out.println("\u001b[34mto quit type anything");
                    System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
                    String stats = sc.nextLine();
                }
                case "new" -> {
                    printTitle();
                    String input;
                    System.out.println("\u001b[34mEnter 0 for numbers and 1 for letters cryptogram:");
                    while(true) {
                        System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
                        input = sc.nextLine();
                        if (input.equals("0")||input.equals("1")) break;
                        System.out.println("\u001b[31mInvalid input, please enter 0 or 1.\u001b[0m");
                    }

                    Game game = new Game(player, input);
                    game.playGame();
                }
                case "load" -> {
                    Game loadedGame = Game.loadGame(player);
                    if (loadedGame != null) {
                        loadedGame.playGame();
                    }


                }
                default -> {
                    System.out.println("\u001B[31mInvalid input!");
                }
            }
        }
    }

    public static void printStats(Player p) {
        p.updateAccuracy();
        printLineBreak("Statistics");
        System.out.println("\u001b[34m ◈ \u001b[38;5;214mPlayer name: \u001b[34m"+p.getUsername());
        System.out.println("\u001b[34m ◈ \u001b[38;5;214mTotal guesses made: \u001b[34m"+p.getTotalGuesses());
        System.out.println("\u001b[34m ◈ \u001b[38;5;214mTotal cryptograms completed: \u001b[34m"+p.getNumCryptogramsCompleted());
        System.out.println("\u001b[34m ◈ \u001b[38;5;214mTotal cryptograms played: \u001b[34m"+p.getNumCryptogramsPlayed());
        System.out.println("\u001b[34m ◈ \u001b[38;5;214mAccuracy: \u001b[34m% "+String.format("%.2f", p.getAccuracy()));
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
        System.out.println("        \u001b[34m               ╔════════════════════════════════════════╗");
        System.out.print  ("        \u001b[34m╔══════════════╣");
        System.out.println("\u001b[38;5;214m ◈ new - creates new cryptogram         \u001b[34m║");
        System.out.print  ("        \u001b[34m║  \u001b[38;5;214m◈  MENU  ◈\u001b[34m  ║");
        System.out.println("\u001b[38;5;214m ◈ load - loads saved cryptogram        \u001b[34m║");
        System.out.print  ("        \u001b[34m╚══════════════╣");
        System.out.println("\u001b[38;5;214m ◈ stats - lists player stats           \u001b[34m║");
        System.out.print  ("        \u001b[34m               ║");
        System.out.println("\u001b[38;5;214m ◈ quit - closes game                   \u001b[34m║");
        System.out.print  ("        \u001b[34m               ║");
        System.out.println("\u001b[38;5;214m ◈ top - gets leaderboard               \u001b[34m║");
        System.out.println("        \u001b[34m               ╚════════════════════════════════════════╝");
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

    public static void printLeaderboard(Players players) {
        ArrayList<Player> allPlayers = new ArrayList<>(players.getPlayers());
        ArrayList<Player> topTen = new ArrayList<>();
        int index = 10;
        if (!allPlayers.isEmpty()) {
            Player highestPlayer = allPlayers.get(0);
            printLineBreak("Leaderboard");
            for (int i = 0; i < index; i++) {
                for (Player player : allPlayers) {
                    double playerRatio = player.getNumCryptogramsPlayed() == 0 ? 0 :
                            (double) player.getNumCryptogramsCompleted() / player.getNumCryptogramsPlayed();

                    double highestRatio = highestPlayer.getNumCryptogramsPlayed() == 0 ? 0 :
                            (double) highestPlayer.getNumCryptogramsCompleted() / highestPlayer.getNumCryptogramsPlayed();

                    if (playerRatio > highestRatio) {
                        highestPlayer = player;
                    }
                }
                topTen.add(highestPlayer);
                allPlayers.remove(highestPlayer);
                if (allPlayers.isEmpty()) {
                    break;
                }
                highestPlayer = allPlayers.getFirst();
            }
        }
        int rank = 1;

        // Header
        System.out.println("\u001b[34m╔═══════════════════════════════════════════════════════╗");
        System.out.println("║              LEADERBOARD                              ║");
        System.out.println("╠════╦════════════════════╦═════════════════════════════╣");

        // Column titles
        System.out.printf("║ %-2s ║ %-18s ║ %-9s ║\n", "#", "Username", "Completed divided by Played");
        System.out.println("╠════╬════════════════════╬═════════════════════════════╣");

        for (Player player : topTen) {
            double ratio = 0;
            if (player.getNumCryptogramsPlayed() != 0) {
                ratio = ((double) player.getNumCryptogramsCompleted() / player.getNumCryptogramsPlayed())* 100;
            }
            System.out.printf(
                    "\u001b[38;5;214m║ \u001b[34m%-2d \u001b[38;5;214m║ %-18s ║ %25.2f %% ║\n",
                    rank,
                    player.getUsername(),
                    ratio
            );
            rank++;
        }

        // Footer
        System.out.println("\u001b[34m╚════╩════════════════════╩═════════════════════════════╝");

    }

    public static void printLineBreak(String name) {
        System.out.println("\n\u001b[38;5;214m╠═════════════════════════════════════╡ "+ name +" ╞═════════════════════════════════════╣\u001b[0m");
    }

    public static Player loadPlayer(Players players) {

        Scanner sc = new Scanner(System.in);
        printLineBreak("Please enter your username");
        System.out.print("\u001b[34m◇\u001b[38;5;214m◈\u001b[34m──►\u001b[0m ");
        String input = sc.nextLine();
        Player player = new Player(input);
        if (players.findPlayer(player) == null) {
            players.addPlayer(player);
            System.out.print("\u001b[34mUser not found, creating account. .\u001b[0m\n");
            return player;
        }
        System.out.print("\u001b[34mUser found, logging in. .\u001b[0m\n");
        return players.getPlayer(player.getUsername());
    }

}
