package day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PickFactory {

    /**
     * @param pick Format: 'Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green'
     */
    public static List<Pick> from(final String serializedGame) {
        final String serializedGamePicks = serializedGame.split(":")[1];
        final List<String> serializedPicks = Arrays.asList(serializedGamePicks.split(";"));
        final List<Pick> deserializedPicks = new ArrayList<>();

        for (final String currentPick : serializedPicks) {
            deserializedPicks.add(deserializePick(currentPick));
        }
        return deserializedPicks;
    }

    private static Pick deserializePick(final String currentPick) {
        int noRedInPick = 0;
        int noGreenInPick = 0;
        int noBlueInPick = 0;
        final List<String> currentPickPerColor = Arrays.asList(currentPick.split(","));
        for (final String currentColorSerialized : currentPickPerColor) {
            final String[] split = currentColorSerialized.strip().split(" ");
            final int numberOfBallsOfThisColor = Integer.parseInt(split[0]);
            final String thisColor = split[1];
            if ("red".equalsIgnoreCase(thisColor)) {
                noRedInPick = numberOfBallsOfThisColor;
            } else if ("green".equalsIgnoreCase(thisColor)) {
                noGreenInPick = numberOfBallsOfThisColor;
            } else if ("blue".equalsIgnoreCase(thisColor)) {
                noBlueInPick = numberOfBallsOfThisColor;
            }
        }
        return new Pick(noRedInPick, noGreenInPick, noBlueInPick);
    }
}
