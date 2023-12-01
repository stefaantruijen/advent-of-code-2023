package com.myorg;

import java.io.InputStream;
import java.util.Scanner;

public class TrebuchetCalibrationB {
    public static void main(final String[] args) throws Exception {
        long sumOfAllCalibrationValues = 0L;
        final TrebuchetCalibrationLineDecipherer decipherer = new TrebuchetCalibrationLineDecipherer();

        try (final InputStream inputFile = TrebuchetCalibrationB.class.getClassLoader().getResourceAsStream("day-1-input.txt")) {
            try (final Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    final String line = scanner.nextLine();
                    sumOfAllCalibrationValues += decipherer.getCalibrationValueFromLine(line);
                }
            }
        }
        System.out.println("The sum of all calibration values = " + sumOfAllCalibrationValues);
    }
}
