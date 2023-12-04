package day4;

import java.util.Arrays;
import java.util.Objects;

public class Scratchcard {

    private final int cardNumberNumber;
    private final int[] winningNumbers;
    private final int[] drawnNumbers;

    public Scratchcard(final String nextLine) {
        final String[] lineSplit = nextLine.split(":");
        final String gamePart = lineSplit[0];
        final String[] winningNumbersPartOfCard = lineSplit[1].split("\\|")[0].split(" ");
        final String[] drawnNumbersPartOfCard = lineSplit[1].split("\\|")[1].split(" ");
        winningNumbers = parseCardPart(winningNumbersPartOfCard);
        this.drawnNumbers = parseCardPart(drawnNumbersPartOfCard);
        this.cardNumberNumber = Integer.parseInt(gamePart.split(" ")[gamePart.split(" ").length - 1]);
    }

    private int[] parseCardPart(final String[] winningNumbersPartOfCard) {
        return Arrays.stream(winningNumbersPartOfCard)
                .filter(cardnumber -> !"".equals(cardnumber))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public int getPoints() {
        int numberOfWinningNumbers = -1;
        for (final int drawnNumber : drawnNumbers) {
            for (final int winningNumber : winningNumbers) {
                if (drawnNumber == winningNumber) {
                    numberOfWinningNumbers++;
                }
            }
        }
        return (int) Math.pow(2, numberOfWinningNumbers);
    }

    public int getCardNumber() {
        return cardNumberNumber;
    }

    public int[] getWinningNumbers() {
        return winningNumbers;
    }

    public int[] getDrawnNumbers() {
        return drawnNumbers;
    }

    @Override
    public String toString() {
        return "Scratchcard [cardNumber=" + cardNumberNumber + ", winningNumbers=" + winningNumbers + ", drawnNumbers=" + drawnNumbers + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(drawnNumbers, cardNumberNumber, winningNumbers);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Scratchcard other = (Scratchcard) obj;
        return Objects.equals(drawnNumbers, other.drawnNumbers) && cardNumberNumber == other.cardNumberNumber
                && Objects.equals(winningNumbers, other.winningNumbers);
    }

}
