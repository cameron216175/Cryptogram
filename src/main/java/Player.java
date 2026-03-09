package src.main.java;

public class Player {
	private String username;
	private double accuracy;
	private int totalGuesses;
	private int cryptogramsPlayed;
	private int cryptogramsCompleted;

	public Player(String name) {
		username = name;
		totalGuesses = 0;
		cryptogramsCompleted = 0;
		cryptogramsPlayed = 0;
		accuracy = 0.0;
	}

	public Player(String username, int totalGuesses, int cryptogramsCompleted, int cryptogramsPlayed, double accuracy) {
		this.username = username;
		this.totalGuesses = totalGuesses;
		this.cryptogramsCompleted = cryptogramsCompleted;
		this.cryptogramsPlayed = cryptogramsPlayed;
		this.accuracy = accuracy;
	}

	// SETTERS

	public void updateAccuracy() {}
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

	public int getTotalGuesses() {
		return totalGuesses;
	}

	public int getNumCryptogramsCompleted() {
        return cryptogramsCompleted;
    }
    
	public int getNumCryptogramsPlayed() {
        return cryptogramsPlayed;
    }
}
