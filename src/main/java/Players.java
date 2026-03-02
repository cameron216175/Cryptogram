package src.main.java;

import java.io.FileWriter;
import java.io.IOException;  
public class Players {
    private Object allPlayers;
    private Object playersFile;

    public void addPlayer(Player p) {
        try {
           FileWriter playerWriter = new FileWriter("./playerData.csv");
           playerWriter
                   .append(p.getUsername())
                   .append(",").append(String.valueOf(p.getTotalGuesses()))
                   .append(",").append(String.valueOf(p.getNumCryptogramsCompleted()))
                   .append(",").append(String.valueOf(p.getNumCryptogramsPlayed()))
                   .append(",").append(String.valueOf(p.getAccuracy()));
           playerWriter.close(); 
        } catch (IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
        

    }

    public void savePlayers() {}
    public void findPlayer(Player p) {}
    public void getAllPlayersAccuracies() {}
    public void getAllPlayersCryptogramsPlayed() {}
    public void getAllPlayersCompletedCryptos() {}
}
