package day1;

import java.util.List;

public class TrebuchetCalibrationLineDeciphererB extends TrebuchetCalibrationLineDecipherer {

    private static final List<String> NUMBERS_SPELLED_OUT = List.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    @Override
    public long getCalibrationValueFromLine(final String line) {
        final String moddedLine = replaceSpelledOutDigitsWithActualDigits(line);
        return super.getCalibrationValueFromLine(moddedLine);
    }

    private String replaceSpelledOutDigitsWithActualDigits(final String line) {
        String moddedLine = line;
        for (final String numberSpelledOut : NUMBERS_SPELLED_OUT) {
            moddedLine = moddedLine.replaceAll(numberSpelledOut, stringToDigitString(numberSpelledOut));
        }
        return moddedLine;
    }

    String stringToDigitString(final String s) {
        return switch (s) {
                case "zero" -> "0";
                case "one" -> "1";
                case "two" -> "2";
                case "three" -> "3";
                case "four" -> "4";
                case "five" -> "5";
                case "six" -> "6";
                case "seven" -> "7";
                case "eight" -> "8";
                case "nine" -> "9";
                default -> throw new IllegalArgumentException("Cannot parse [" + s + "] to digit");
        };
    }

}
