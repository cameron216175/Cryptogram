package src.main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


public class Players {
    private Object allPlayers;
    private Object playersFile;

    public boolean addPlayer(Player p) {
        if (findPlayer(p) == null) {
            try {

                FileWriter playerWriter = new FileWriter("./playerData.csv");
                playerWriter
                        .append(p.getUsername())
                        .append(",").append(String.valueOf(p.getTotalGuesses()))
                        .append(",").append(String.valueOf(p.getNumCryptogramsCompleted()))
                        .append(",").append(String.valueOf(p.getNumCryptogramsPlayed()))
                        .append(",").append(String.valueOf(p.getAccuracy()));
                playerWriter.close();
                return true;
            } catch (IOException e) {
                System.out.println("An error has occured.");
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean savePlayers(Player p) throws IOException {
        Path path = Paths.get("./playerData.csv");
        List<String> lines = Files.readAllLines(path);
        AtomicBoolean found = new AtomicBoolean(false);
        List<String> updatedLine = lines.stream()
                .map(line ->
                {
                    if (line.startsWith(p.getUsername() + ",")) {
                        found.set(true);
                        return p.getUsername() + "," +
                                String.valueOf(p.getTotalGuesses()) + "," +
                                String.valueOf(p.getNumCryptogramsCompleted()) + "," +
                                String.valueOf(p.getNumCryptogramsPlayed()) + "," +
                                String.valueOf(p.getAccuracy());
                    }
                    return line;
                })
                .collect(Collectors.toList());

        Files.write(path, updatedLine);
        return found.get();
    }
    public String findPlayer(Player p) {
        File players = new File("./playerData.csv");

        try (Scanner reader = new Scanner(players)) {
            while (reader.hasNextLine()) {
                String player = reader.nextLine();
                String regex = ",";
                String[] playerData = player.split(regex);
                if (playerData[0].equals(p.getUsername())) {
                    return playerData[0];
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }

        return null;
    }
    public void getAllPlayersAccuracies() {
        File players = new File("./playerData.csv");

        try (Scanner reader = new Scanner(players)) {
            List<String> stats = new ArrayList<>();
            while (reader.hasNextLine()) {
                String player = reader.nextLine();
                String regex = ",";
                String[] playerData = player.split(regex);
                stats.add(playerData[3]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }

    public void getAllPlayersCryptogramsPlayed() {
        File players = new File("./playerData.csv");

        try (Scanner reader = new Scanner(players)) {
            List<String> stats = new ArrayList<>();
            while (reader.hasNextLine()) {
                String player = reader.nextLine();
                String regex = ",";
                String[] playerData = player.split(regex);
                stats.add(playerData[3]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }
    public void getAllPlayersCompletedCryptos() {
        File players = new File("./playerData.csv");

        try (Scanner reader = new Scanner(players)) {
            List<String> stats = new ArrayList<>();
            while (reader.hasNextLine()) {
                String player = reader.nextLine();
                String regex = ",";
                String[] playerData = player.split(regex);
                stats.add(playerData[2]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }
}
