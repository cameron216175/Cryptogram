package src.main.java;
public class Player {
	private String username;
	private double accuracy;
	private int totalGuesses;
	private int cryptogramsPlayed;
	private int cryptogramsCompleted;

	public void updateAccuracy() {}

	// SETTERS

	public void incrementCryptogramsCompleted() {
        cryptogramsCompleted++;
    }
	public void incrementCryptogramsPlayed() {
        cryptogramsPlayed++;
    }

	// GETTERS

	public double getAccuracy() {
        return accuracy;
    }

	public String getUsername() {
		return username;
	}

	public int totalGuesses() {
		return totalGuesses;
	}

	public int getNumCryptogramsCompleted() {
        return cryptogramsCompleted;
    }
    
	public int getNumCryptogramsPlayed() {
        return cryptogramsPlayed;
    }
}
