package src.main.java;

import java.io.Serializable;

public class Player implements Serializable {
	private String username;
	private double accuracy;
	private int totalGuesses;
	private int correctGuesses;
	private int cryptogramsPlayed;
	private int cryptogramsCompleted;

	public Player(String name) {
		username = name;
		totalGuesses = 0;
		cryptogramsCompleted = 0;
		cryptogramsPlayed = 0;
		accuracy = 0.0;
		correctGuesses = 0;
	}

	public Player(String username, int totalGuesses, int cryptogramsCompleted, int cryptogramsPlayed, double accuracy, int correctGuesses) {
		this.username = username;
		this.totalGuesses = totalGuesses;
		this.cryptogramsCompleted = cryptogramsCompleted;
		this.cryptogramsPlayed = cryptogramsPlayed;
		this.accuracy = accuracy;
		this.correctGuesses = correctGuesses;
	}

	// SETTERS

	public void updateAccuracy() {
		this.accuracy =  ((double) this.correctGuesses / this.totalGuesses) * 100.0;
	}
	public void incrementCryptogramsCompleted() {
        cryptogramsCompleted++;
    }
	public void incrementCryptogramsPlayed() {
        cryptogramsPlayed++;
    }
	public void incrementTotalGuesses() {totalGuesses++;}
	public void incrementCorrectGuesses() {correctGuesses++;}

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

	public int getCorrectGuesses() { return correctGuesses; }
}
