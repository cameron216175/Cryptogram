public class Player {
	private String username;
	private double accuracy;
	private int totalGuesses;
	private int cryptogramsPlayed;
	private int cryptogramsCompleted;

	public void updateAccuracy() {}

	public void incrementCryptogramsCompleted() {
        cryptogramsCompleted++;
    }
	public void incrementCryptogramsPlayed() {
        cryptogramsPlayed++;
    }

	public double getAccuracy() {
        return accuracy;
    }

	public int getNumCryptogramsCompleted() {
        return cryptogramsCompleted;
    }
    
	public int getNumCryptogramsPlayed() {
        return cryptogramsPlayed;
    }
}
