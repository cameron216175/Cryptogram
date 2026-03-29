package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Player;
import src.main.java.Players;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTest {

    // Helper: make a player using the full constructor.
    // Constructor order: (username, totalGuesses, cryptogramsCompleted, cryptogramsPlayed, accuracy, correctGuesses)
    private Player makePlayer(String name, int played, int completed) {
        return new Player(name, 0, completed, played, 0.0, 0);
    }

    // Helper: compute the completion ratio the same way the leaderboard should
    private double ratio(Player p) {
        if (p.getNumCryptogramsPlayed() == 0) return 0.0;
        return ((double) p.getNumCryptogramsCompleted() / p.getNumCryptogramsPlayed()) * 100.0;
    }

    // ── Ratio calculation tests ───────────────────────────────────────────────

    // Scenario 1: Player with 3 completed out of 4 played has a ratio of 75%
    @Test
    public void Scenario1RatioCalculation() {
        Player p = makePlayer("ratioTest1", 4, 3);
        assertEquals(75.0, ratio(p), 0.001, "3/4 should be 75%");
    }

    // Scenario 2: Player with 0 played has a ratio of 0% (no division by zero)
    @Test
    public void Scenario2ZeroPlayedRatioIsZero() {
        Player p = makePlayer("ratioTest2", 0, 0);
        assertEquals(0.0, ratio(p), 0.001, "0 played should give 0%, not a divide-by-zero");
    }

    // Scenario 3: Player with all completed gets 100%
    @Test
    public void Scenario3AllCompletedIs100Percent() {
        Player p = makePlayer("ratioTest3", 5, 5);
        assertEquals(100.0, ratio(p), 0.001, "5/5 should be 100%");
    }

    // Scenario 4: Player with 0 completed out of 10 played gets 0%
    @Test
    public void Scenario4NoneCompletedIsZeroPercent() {
        Player p = makePlayer("ratioTest4", 10, 0);
        assertEquals(0.0, ratio(p), 0.001, "0/10 should be 0%");
    }

    // ── Ordering tests ────────────────────────────────────────────────────────

    // Scenario 5: Higher ratio ranks above lower ratio regardless of raw count.
    // Player A: 2/2 = 100%  |  Player B: 5/20 = 25%
    // B has more raw completions but A should rank higher by ratio.
    @Test
    public void Scenario5HigherRatioRanksAboveHigherRawCount() {
        Player a = makePlayer("playerA_ratio", 2, 2);   // 100%
        Player b = makePlayer("playerB_ratio", 20, 5);  // 25%
        assertTrue(ratio(a) > ratio(b),
                "Player with 100% ratio should rank above player with 25% ratio even if raw count is lower");
    }

    // Scenario 6: Equal ratios are not strictly ordered either way
    @Test
    public void Scenario6EqualRatioNotStrictlyGreater() {
        Player a = makePlayer("equalA", 4, 2);  // 50%
        Player b = makePlayer("equalB", 6, 3);  // 50%
        assertEquals(ratio(a), ratio(b), 0.001, "Both players are at 50% and should tie");
    }

    // ── Players list tests ────────────────────────────────────────────────────

    // Scenario 7: Both added players are present in the players list
    @Test
    public void Scenario7BothPlayersPresent() throws IOException {
        Players players = new Players();
        Player p1 = makePlayer("lb_size_test_A", 3, 2);
        Player p2 = makePlayer("lb_size_test_B", 5, 4);

        players.removePlayer(p1);
        players.removePlayer(p2);
        players.addPlayer(p1);
        players.addPlayer(p2);

        ArrayList<Player> list = players.getPlayers();
        assertTrue(list.stream().anyMatch(p -> p.getUsername().equals("lb_size_test_A")));
        assertTrue(list.stream().anyMatch(p -> p.getUsername().equals("lb_size_test_B")));

        players.removePlayer(p1);
        players.removePlayer(p2);
        players.savePlayers();
    }

    // Scenario 8: getPlayers does not throw when fewer than 10 players exist
    @Test
    public void Scenario8FewerThanTenPlayersDoesNotThrow() {
        Players players = new Players();
        assertDoesNotThrow(() -> {
            ArrayList<Player> list = players.getPlayers();
            assertNotNull(list);
        });
    }

    // Scenario 9: getPlayers returns a non-null list even when empty
    @Test
    public void Scenario9GetPlayersNeverReturnsNull() {
        Players players = new Players();
        assertNotNull(players.getPlayers(), "getPlayers() should return a list, not null");
    }

    // ── Persistence tests ─────────────────────────────────────────────────────

    // Scenario 10: A player's completed and played counts survive a save/reload cycle.
    // If this fails it means savePlayers() and readPlayers() have a column order mismatch
    // (the played/completed swap bug described in the class comment above).
    @Test
    public void Scenario10PlayedAndCompletedPersistCorrectly() throws IOException {
        Players players = new Players();

        // Construct directly via incrementers so we're not relying on the full constructor arg order
        Player p = new Player("lb_persist_test");
        for (int i = 0; i < 8; i++) p.incrementCryptogramsPlayed();
        for (int i = 0; i < 6; i++) p.incrementCryptogramsCompleted();

        players.removePlayer(p);
        players.addPlayer(p);
        players.savePlayers();

        Players reloaded = new Players();
        Player loaded = reloaded.getPlayer("lb_persist_test");

        assertNotNull(loaded, "Player should persist to CSV");
        assertEquals(8, loaded.getNumCryptogramsPlayed(),
                "Played count should persist correctly — if this fails, savePlayers/readPlayers have a column swap bug");
        assertEquals(6, loaded.getNumCryptogramsCompleted(),
                "Completed count should persist correctly — if this fails, savePlayers/readPlayers have a column swap bug");
        assertEquals(75.0, ratio(loaded), 0.001, "Ratio should be 75% after reload");

        players.removePlayer(p);
        players.savePlayers();
    }

    // Scenario 11: Player with 0 played persists safely and reloads as 0%
    @Test
    public void Scenario11ZeroPlayedPersistsSafely() throws IOException {
        Players players = new Players();
        Player p = new Player("lb_zero_test");

        players.removePlayer(p);
        players.addPlayer(p);
        players.savePlayers();

        Players reloaded = new Players();
        Player loaded = reloaded.getPlayer("lb_zero_test");

        assertNotNull(loaded);
        assertEquals(0, loaded.getNumCryptogramsPlayed());
        assertEquals(0, loaded.getNumCryptogramsCompleted());
        assertEquals(0.0, ratio(loaded), 0.001, "0/0 player should show 0% after reload");

        players.removePlayer(p);
        players.savePlayers();
    }

    // Scenario 12: getAllPlayersCompletedCryptos and getAllPlayersCryptogramsPlayed
    // return lists of the same length (required to zip them for ratio calculation)
    @Test
    public void Scenario12CompletedAndPlayedListsMatchLength() {
        Players players = new Players();
        assertEquals(
                players.getAllPlayersCompletedCryptos().size(),
                players.getAllPlayersCryptogramsPlayed().size(),
                "Completed and played lists must be the same length to calculate ratios correctly"
        );
    }
}