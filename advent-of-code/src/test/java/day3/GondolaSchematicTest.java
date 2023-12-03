package day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class GondolaSchematicTest {

    final String sampleInput = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
        """;

    @Test
    public void testSample() throws Exception {
        final GondolaSchematicBuilder gsb = GondolaSchematic.builder();
        try (final Scanner s = new Scanner(sampleInput)) {
            while (s.hasNextLine()) {
                gsb.addLine(s.nextLine());
            }
        }
        final GondolaSchematic gondolaSchematic = gsb.build();
        final List<Integer> partNumbers = gondolaSchematic.getPartNumbers();
        final Integer sumOfPartNumbers = partNumbers.stream().reduce(0, (a, b) -> a + b);
        assertThat(sumOfPartNumbers).isEqualTo(4361);
    }
}
