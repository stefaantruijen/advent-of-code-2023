package day2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PickFactoryTest {

    @Test
    public void testPickFactory() {
        final String serializedGame = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";

        final List<Pick> result = PickFactory.from(serializedGame);

        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo(new Pick(4, 0, 3));
        assertThat(result.get(1)).isEqualTo(new Pick(1, 2, 6));
        assertThat(result.get(2)).isEqualTo(new Pick(0, 2, 0));
    }
}
