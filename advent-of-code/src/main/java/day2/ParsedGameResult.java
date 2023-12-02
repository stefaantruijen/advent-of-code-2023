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

    public int getPower() {
        int neededRed = 0;
        int neededGreen = 0;
        int neededBlue = 0;
        for (final Pick pick : picks) {
            neededRed = Math.max(pick.getNoRed(), neededRed);
            neededGreen = Math.max(pick.getNoGreen(), neededGreen);
            neededBlue = Math.max(pick.getNoBlue(), neededBlue);
        }

        final int factorForPowerRed = Math.max(neededRed, 1);
        final int factorForPowerGreen = Math.max(neededGreen, 1);
        final int factorForPowerBlue = Math.max(neededBlue, 1);

        return factorForPowerRed * factorForPowerGreen * factorForPowerBlue;
    }

}
