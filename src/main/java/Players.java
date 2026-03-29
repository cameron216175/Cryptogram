package src.main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.*;


public class Players {
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private String playersFile = "src/playerData.csv";

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

    public boolean savePlayers() throws IOException {
        try {
            FileWriter myWriter = new FileWriter(playersFile);
            for (Player player : allPlayers) {
                myWriter.write(
                        player.getUsername() + "," +
                                player.getTotalGuesses() + "," +
                                player.getNumCryptogramsPlayed() + "," +
                                player.getNumCryptogramsCompleted() + "," +
                                player.getAccuracy() + "," +
                                player.getCorrectGuesses()+ "\n");
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
        for(int x = 0; x < allPlayers.size(); x++) {
            if (allPlayers.get(x).getUsername().equals(p.getUsername())) {
                allPlayers.remove(x);
                return true;
            }
        }
        return false;
    }

    public String findPlayer(Player p) {
        for (Player Player : allPlayers) {
            if (Player.getUsername().equals(p.getUsername())) {
                return Player.getUsername();
            }
        };
        return null;
    }

    public boolean updatePlayer(Player p) {
        if (findPlayer(p) != null) {
            for(int x = 0; x < allPlayers.size(); x++) {
                if (allPlayers.get(x).getUsername().equals(p.getUsername())) {
                    allPlayers.set(x, p);
                    return true;
                }
            }
        }
        return false;
    }

    public void readPlayers() {
        File players = new File(playersFile);

        try (Scanner reader = new Scanner(players)) {
            while (reader.hasNextLine()) {
                String p = reader.nextLine();
                String regex = ",";
                String[] playerData = p.split(regex);
                Player player = new Player(playerData[0], Integer.parseInt(playerData[1]), Integer.parseInt(playerData[2]), Integer.parseInt(playerData[3]), Double.parseDouble(playerData[4]), Integer.parseInt(playerData[5]));
                allPlayers.add(player);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }

    }

    public Player getPlayer(String playerName) {
        for (Player player : allPlayers) {
            if (player.getUsername().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public ArrayList<Player> getPlayers() {
        return allPlayers;
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
        PrintWriter writer = new PrintWriter(playersFile);
        writer.print("");
        writer.close();
    }
}
