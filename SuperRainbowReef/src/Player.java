import java.util.Comparator;

// The purpose of this class is for printing high scores, just keeps track of players/scores

public class Player {

    private String playerName;
    private int playerScore;

    public Player(String name, int score) {
        this.playerName = name;
        this.playerScore = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}

// Used for sorting the arraylist
class Sortbyroll implements Comparator<Player> {
    public int compare(Player a, Player b) {
        return b.getPlayerScore() - a.getPlayerScore();
    }
}
