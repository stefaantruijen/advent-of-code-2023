package day4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ScratchcardTest {

    final String sampleInput = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """;

    @Test
    public void testSampleA() {
        final List<Scratchcard> scratchcards = new ArrayList<>();
        try (final Scanner s = new Scanner(sampleInput)) {
            while (s.hasNextLine()) {
                scratchcards.add(new Scratchcard(s.nextLine()));
            }
        }
        final Integer scratchcardPointSum = scratchcards.stream().map(Scratchcard::getPoints).reduce(0, (a, b) -> a + b);
        assertThat(scratchcardPointSum).isEqualTo(13);
    }

    @Test
    public void testParseline() {
        final Scratchcard scratchcard = new Scratchcard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");

        assertThat(scratchcard.getCardNumber()).isEqualTo(1);
        assertThat(scratchcard.getWinningNumbers()).containsExactly(41, 48, 83, 86, 17);
        assertThat(scratchcard.getDrawnNumbers()).containsExactly(83, 86, 6, 31, 17, 9, 48, 53);
    }
}
