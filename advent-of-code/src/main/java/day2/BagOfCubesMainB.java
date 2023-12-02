package day2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import day1.TrebuchetCalibrationB;

public class BagOfCubesMainB {

    public static void main(final String[] args) throws IOException {
        int sumOfPowers = 0;

        final int totalNoRedInBag = 12;
        final int totalNoGreenInBag = 13;
        final int totalNoBlueInBag = 14;
        final GameParser gameParser = new GameParser(totalNoRedInBag, totalNoGreenInBag, totalNoBlueInBag);

        try (final InputStream inputFile = TrebuchetCalibrationB.class.getClassLoader().getResourceAsStream("day-2-input.txt")) {
            try (final Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    final String pick = scanner.nextLine();
                    final ParsedGameResult gameResult = gameParser.gameIsPossible(pick);
                    sumOfPowers += gameResult.getPower();
                }
            }
        }
        System.out
                .println("The sum of powers of minimal number of required balls = " + sumOfPowers);
    }
}
