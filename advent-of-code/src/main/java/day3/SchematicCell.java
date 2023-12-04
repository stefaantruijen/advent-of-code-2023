package day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class SchematicCell {

    private final char content;
    private SchematicCell leftNeighbor;
    private SchematicCell rightNeighbor;
    private SchematicCell bottomNeighbor;
    private SchematicCell topNeighbor;
    private boolean counted;

    public SchematicCell(final char content) {
        this.content = content;
    }

    public boolean isDigit() {
        return Character.isDigit(content);
    }

    public boolean isSymbol() {
        return !Character.isAlphabetic(content) && !Character.isDigit(content) && content != '.';
    }

    private boolean leftNeighborIsDigit() {
        return leftNeighbor != null && leftNeighbor.isDigit();
    }

    private boolean rightNeighborIsDigit() {
        return rightNeighbor != null && this.rightNeighbor.isDigit();
    }

    private boolean topNeighborIsDigit() {
        return topNeighbor != null && this.topNeighbor.isDigit();
    }

    private boolean bottomNeighborIsDigit() {
        return bottomNeighbor != null && this.bottomNeighbor.isDigit();
    }

    private boolean topLeftNeighborIsDigit() {
        return topNeighbor != null && topNeighbor.getLeftNeighbor() != null && this.topNeighbor.getLeftNeighbor().isDigit();
    }

    private boolean topRightNeighborIsDigit() {
        return topNeighbor != null && topNeighbor.getRightNeighbor() != null && this.topNeighbor.getRightNeighbor().isDigit();
    }

    private boolean bottomLeftNeighborIsDigit() {
        return bottomNeighbor != null && bottomNeighbor.getLeftNeighbor() != null && this.bottomNeighbor.getLeftNeighbor().isDigit();
    }

    private boolean bottomRightNeighborIsDigit() {
        return bottomNeighbor != null && bottomNeighbor.getRightNeighbor() != null && this.bottomNeighbor.getRightNeighbor().isDigit();
    }

    public int getNumberOfAdjacentCells() {
        if (!isDigit() || isCounted()) {
            return 0;
        }
        final StringBuilder sb = new StringBuilder();

        SchematicCell currentCell = this;
        while (currentCell != null && currentCell.leftNeighborIsDigit()) {
            sb.insert(0, currentCell.getLeftNeighbor().getContent());
            currentCell.leftNeighbor.setCounted(true);
            currentCell = currentCell.getLeftNeighbor();
        }
        sb.append(this.getContent());
        currentCell = this;
        this.setCounted(true);
        while (currentCell != null && currentCell.rightNeighborIsDigit()) {
            sb.append(currentCell.getRightNeighbor().getContent());
            currentCell.rightNeighbor.setCounted(true);
            currentCell = currentCell.getRightNeighbor();
        }
        return Integer.parseInt(sb.toString());
    }

    public int getGearRatio() {
        if (!isGear()) {
            return 1;
        }
        final List<SchematicCell> gearRatioCells = new ArrayList<>(List.of(this.topNeighbor, this.bottomNeighbor).stream()
                .filter(neighbor -> neighbor != null)
                .flatMap(topOrBottomNeighbor -> Arrays.asList(topOrBottomNeighbor, topOrBottomNeighbor.getLeftNeighbor(),
                        topOrBottomNeighbor.getRightNeighbor()).stream())
                .filter(neighbor -> neighbor != null)
                .toList());

        if (leftNeighbor != null) {
            gearRatioCells.add(this.leftNeighbor);
        }
        if (rightNeighbor != null) {
            gearRatioCells.add(this.rightNeighbor);
        }
        final Integer gearRatio = gearRatioCells
            .stream()
            .filter(SchematicCell::isDigit)
            .map(SchematicCell::getNumberOfAdjacentCells)
            .reduce(1, (a, b) -> a * b);
        return gearRatio;
    }

    boolean isGear() {
        return isSymbol() && getContent() == '*' && hasExactlyTwoNeighboringNumbers();
    }

    private boolean hasExactlyTwoNeighboringNumbers() {
        final List<Supplier<Boolean>> potentialNeighbors = List.of(
                this::leftNeighborIsDigit,
                this::rightNeighborIsDigit,
                this::topNeighborIsDigit,
                this::bottomNeighborIsDigit);
        long count = potentialNeighbors.stream().filter(Supplier::get).count();
        if (count > 2) {
            return false;
        }
        if (topLeftNeighborIsDigit()) {
            if (!topNeighborIsDigit()) {
                count++;
            }
        }
        if (topRightNeighborIsDigit()) {
            if (!topNeighborIsDigit()) {
                count++;
            }
        }
        if (bottomLeftNeighborIsDigit()) {
            if (!bottomNeighborIsDigit()) {
                count++;
            }
        }
        if (bottomRightNeighborIsDigit()) {
            if (!bottomNeighborIsDigit()) {
                count++;
            }
        }
        System.out.println("count = " + count);
        return count == 2;
    }

    /******************** GETTERS AND SETTERS ********************/
    public char getContent() {
        return content;
    }

    public SchematicCell getLeftNeighbor() {
        return leftNeighbor;
    }

    public SchematicCell getRightNeighbor() {
        return rightNeighbor;
    }

    public SchematicCell getBottomNeighbor() {
        return bottomNeighbor;
    }

    public SchematicCell getTopNeighbor() {
        return topNeighbor;
    }

    public void setCounted(final boolean counted) {
        this.counted = counted;
    }

    public boolean isCounted() {
        return this.counted;
    }

    public void setLeftNeighbor(final SchematicCell leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public void setRightNeighbor(final SchematicCell rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public void setBottomNeighbor(final SchematicCell bottomNeighbor) {
        this.bottomNeighbor = bottomNeighbor;
    }

    public void setTopNeighbor(final SchematicCell topNeighbor) {
        this.topNeighbor = topNeighbor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottomNeighbor, content, leftNeighbor, rightNeighbor, topNeighbor);
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
        final SchematicCell other = (SchematicCell) obj;
        return Objects.equals(bottomNeighbor, other.bottomNeighbor) && content == other.content && Objects.equals(leftNeighbor, other.leftNeighbor)
                && Objects.equals(rightNeighbor, other.rightNeighbor) && Objects.equals(topNeighbor, other.topNeighbor);
    }

    @Override
    public String toString() {
        return "" + content;
    }

}
