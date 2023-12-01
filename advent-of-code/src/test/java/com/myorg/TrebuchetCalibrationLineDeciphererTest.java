package com.myorg;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.myorg.TrebuchetCalibrationLineDecipherer;
import com.myorg.TrebuchetCalibrationLineDeciphererB;

public class TrebuchetCalibrationLineDeciphererTest {

    @Test
    public void testDigitsOnly() throws Exception {
        final TrebuchetCalibrationLineDecipherer decipherer = new TrebuchetCalibrationLineDecipherer();
        final String testLine = "kjrqmzv9mmtxhgvsevenhvq7";

        final long result = decipherer.getCalibrationValueFromLine(testLine);

        assertThat(result).isEqualTo(97);
    }

    @Test
    public void testLettersOnly() throws Exception {
        final TrebuchetCalibrationLineDeciphererB decipherer = new TrebuchetCalibrationLineDeciphererB();
        final String testLine = "four2tszbgmxpbvninebxns6nineqbqzgjpmpqr";

        final long result = decipherer.getCalibrationValueFromLine(testLine);

        assertThat(result).isEqualTo(49);
    }

    @Test
    public void givenLegitString_stringToDigitWorks() throws Exception {
        final TrebuchetCalibrationLineDeciphererB decipherer = new TrebuchetCalibrationLineDeciphererB();

        final String result = decipherer.stringToDigitString("one");

        assertThat(result).isEqualTo("1");
    }
}
