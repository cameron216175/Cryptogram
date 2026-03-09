package src.main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.*;


public class Players {
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private String playersFile;

    public Players() {
        readPlayers();
    }

    public boolean addPlayer(Player p) {
        if (findPlayer(p) != null) {
            System.out.println("Player already exists!");
            return false;
        }
        allPlayers.add(p);
        return true;
    }

    public boolean savePlayers(Player p) throws IOException {
        try {
            FileWriter myWriter = new FileWriter("src/playerData.csv");
            for (Player player : allPlayers) {
                myWriter.write(
                        player.getUsername() + "," +
                                player.getTotalGuesses() + "," +
                                player.getNumCryptogramsPlayed() + "," +
                                player.getNumCryptogramsCompleted() + "," +
                                player.getAccuracy() + "\n");
            }
            myWriter.close();  // must close manually
            System.out.println("Successfully wrote to the file.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }

    }

    public boolean removePlayer(Player p) {
        if (findPlayer(p) != null) {
            for(int x = 0; x < allPlayers.size(); x++) {
                if (allPlayers.get(x).getUsername().equals(p.getUsername())) {
                    allPlayers.remove(x);
                }
            }
            return findPlayer(p) == null;
        }
        return false;
    }

    public String findPlayer(Player p) {
        for (Player Player : allPlayers) {
            if (Player.getUsername().equals(p.getUsername())) {
                return Player.getUsername();
            }
        }
        ;
        return null;
    }

    public void readPlayers() {
        File players = new File("src/playerData.csv");

        try (Scanner reader = new Scanner(players)) {
            while (reader.hasNextLine()) {
                String p = reader.nextLine();
                String regex = ",";
                String[] playerData = p.split(regex);
                Player player = new Player(playerData[0], Integer.parseInt(playerData[1]), Integer.parseInt(playerData[2]), Integer.parseInt(playerData[3]), Double.parseDouble(playerData[4]));
                allPlayers.add(player);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }

    }

    public Player getPlayer(String playerName) {
        for (Player Player : allPlayers) {
            if (Player.getUsername().equals(playerName)) {
                return Player;
            }
        }
        return null;
    }

    public List<Double> getAllPlayersAccuracies() {
        List<Double> stats = new ArrayList<>();
        for (Player Player : allPlayers) {
            stats.add(Player.getAccuracy());
        }
        return stats;
    }

    public List<Integer> getAllPlayersCryptogramsPlayed() {
        List<Integer> stats = new ArrayList<>();
        for (Player Player : allPlayers) {
            stats.add(Player.getNumCryptogramsPlayed());
        }
        return stats;
    }

    public List<Integer> getAllPlayersCompletedCryptos() {
        List<Integer> stats = new ArrayList<>();
        for (Player Player : allPlayers) {
            stats.add(Player.getNumCryptogramsCompleted());
        }
        return stats;
    }

    public void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/playerData.csv");
        writer.print("");
        writer.close();
    }
}
