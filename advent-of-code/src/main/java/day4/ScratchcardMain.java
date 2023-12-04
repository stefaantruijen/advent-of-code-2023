package day4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScratchcardMain {
    public static void main(final String[] args) throws IOException {
        final List<Scratchcard> scratchcards = new ArrayList<>();
        try (final InputStream inputFile = ScratchcardMain.class.getClassLoader().getResourceAsStream("day-4-input.txt")) {
            try (final Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    scratchcards.add(new Scratchcard(scanner.nextLine()));
                }
            }
        }
        final Integer totalScore = scratchcards
                .stream()
                .map(Scratchcard::getPoints)
                .reduce(0, (a, b) -> a + b);
        System.out.println("The elf's cardstack has a total score of  = " + totalScore);
    }
}
