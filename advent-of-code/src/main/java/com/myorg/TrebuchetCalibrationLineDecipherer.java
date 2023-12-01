package com.myorg;

public class TrebuchetCalibrationLineDecipherer {

    public long getCalibrationValueFromLine(final String line) {
        final char[] lineChars = line.toCharArray();
        String firstDigit = null;
        String lastDigit = null;
        for (int i = 0; i < lineChars.length; i++) {
            final char curChar = lineChars[i];
            if (Character.isDigit(curChar)) {
                if (firstDigit == null) {
                    firstDigit = "" + curChar;
                } else {
                    lastDigit = "" + curChar;
                }
            }
        }

        if (firstDigit == null) {
            throw new IllegalArgumentException("No digit found in string [" + line + "]");
        }

        if (lastDigit == null) {
            lastDigit = firstDigit;
        }

        return Long.parseLong(firstDigit + lastDigit);
    }

}
