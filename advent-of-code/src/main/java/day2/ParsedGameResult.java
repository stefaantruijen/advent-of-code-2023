package day2;

import java.util.List;

public class ParsedGameResult {

    private final int gameNumber;
    private final boolean isPossible;
    private final List<Pick> picks;

    public ParsedGameResult(final int gameNumber, final boolean isPossible, final List<Pick> picks) {
        super();
        this.gameNumber = gameNumber;
        this.isPossible = isPossible;
        this.picks = picks;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public boolean isPossible() {
        return isPossible;
    }

    public List<Pick> getPicks() {
        return picks;
    }

}
