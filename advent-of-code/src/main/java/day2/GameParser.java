package day2;

import java.util.List;

public class GameParser {

    private final int totalNoRedInBag;
    private final int totalNoGreenInBag;
    private final int totalNoBlueInBag;

    public GameParser(final int totalNoRedInBag, final int totalNoGreenInBag, final int totalNoBlueInBag) {
        assert totalNoRedInBag > 0;
        assert totalNoGreenInBag > 0;
        assert totalNoBlueInBag > 0;

        this.totalNoRedInBag = totalNoRedInBag;
        this.totalNoGreenInBag = totalNoGreenInBag;
        this.totalNoBlueInBag = totalNoBlueInBag;
    }

    /**
     * @param pick Format: 'Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green'
     */
    public ParsedGameResult gameIsPossible(final String serializedPick) {
        final int gameNumber = Integer.parseInt(serializedPick.split(":")[0].split(" ")[1]);
        final List<Pick> pick = PickFactory.from(serializedPick);
        final boolean isPossible = pick.stream()
                .allMatch(
                    p -> p.getNoRed() <= totalNoRedInBag &&
                    p.getNoGreen() <= totalNoGreenInBag &&
                    p.getNoBlue() <= totalNoBlueInBag);
        return new ParsedGameResult(gameNumber, isPossible, pick);
    }

}
