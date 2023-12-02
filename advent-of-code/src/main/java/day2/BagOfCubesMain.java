package day2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import day1.TrebuchetCalibrationB;

public class BagOfCubesMain {

    public static void main(final String[] args) throws IOException {
        int numberOfPossibleGames = 0;

        final int totalNoRedInBag = 12;
        final int totalNoGreenInBag = 13;
        final int totalNoBlueInBag = 14;
        final GameParser gameParser = new GameParser(totalNoRedInBag, totalNoGreenInBag, totalNoBlueInBag);

        try (final InputStream inputFile = TrebuchetCalibrationB.class.getClassLoader().getResourceAsStream("day-2-input.txt")) {
            try (final Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    final String pick = scanner.nextLine();
                    final ParsedGameResult gameResult = gameParser.gameIsPossible(pick);
                    if (gameResult.isPossible()) {
                        numberOfPossibleGames += gameResult.getGameNumber();
                    }
                }
            }
        }
        System.out.println(
                "The sum of the IDs of games that are playable with only 12 red cubes, 13 green cubes, and 14 blue cubes = " + numberOfPossibleGames);
    }
}
