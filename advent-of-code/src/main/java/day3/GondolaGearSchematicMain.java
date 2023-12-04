package day3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class GondolaGearSchematicMain {

    public static void main(final String[] args) throws IOException {
        final GondolaSchematic gondolaSchematic;
        try (final InputStream inputFile = GondolaGearSchematicMain.class.getClassLoader().getResourceAsStream("day-3-input.txt")) {
            try (final Scanner scanner = new Scanner(inputFile)) {
                final GondolaSchematicBuilder gsb = GondolaSchematic.builder();
                while (scanner.hasNextLine()) {
                    gsb.addLine(scanner.nextLine());
                }
                gondolaSchematic = gsb.build();
            }
        }
        final Integer sumOfPartNumbers = gondolaSchematic.getGearRatios().stream().reduce(0, (a, b) -> a + b);
        System.out.println("the sum of all of the gears in the engine schematic  = " + sumOfPartNumbers);
    }
}
