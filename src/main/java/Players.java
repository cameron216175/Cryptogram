package src.main.java;
import java.io.FileWriter;
import java.io.IOException;  
public class Players {
    private Object allPlayers;
    private Object playersFile;

    public void addPlayer(Player p) {
        try {
           FileWriter playerWriter = new FileWriter("./playerData.csv");
           playerWriter.append(p.getUsername()+","+Integer.toString(p.totalGuesses())+","+Integer.toString(p.getNumCryptogramsCompleted())+","+Integer.toString(p.getNumCryptogramsPlayed())+","+Double.toString(p.getAccuracy()));
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
