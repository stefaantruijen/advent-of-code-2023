package day2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class GameParserTest {

    @Test
    public void testSample() throws Exception {
        final List<String> sampleInput = List.of(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        final GameParser gameParser = new GameParser(12, 13, 14);

        assertThat(gameParser.gameIsPossible(sampleInput.get(0)).isPossible()).isTrue();
        assertThat(gameParser.gameIsPossible(sampleInput.get(0)).getGameNumber()).isEqualTo(1);
        assertThat(gameParser.gameIsPossible(sampleInput.get(1)).isPossible()).isTrue();
        assertThat(gameParser.gameIsPossible(sampleInput.get(1)).getGameNumber()).isEqualTo(2);
        assertThat(gameParser.gameIsPossible(sampleInput.get(2)).isPossible()).isFalse();
        assertThat(gameParser.gameIsPossible(sampleInput.get(2)).getGameNumber()).isEqualTo(3);
        assertThat(gameParser.gameIsPossible(sampleInput.get(3)).isPossible()).isFalse();
        assertThat(gameParser.gameIsPossible(sampleInput.get(3)).getGameNumber()).isEqualTo(4);
        assertThat(gameParser.gameIsPossible(sampleInput.get(4)).isPossible()).isTrue();
        assertThat(gameParser.gameIsPossible(sampleInput.get(4)).getGameNumber()).isEqualTo(5);
    }

    @Test
    public void javaTest() throws Exception {
        final var result = Arrays.asList(new String[] { "jos", "jef" });
        assertThat(result).hasSize(2);
    }

}
