package day3;

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
        while (currentCell != null && currentCell.rightNeighborIsDigit()) {
            sb.append(currentCell.getRightNeighbor().getContent());
            currentCell.rightNeighbor.setCounted(true);
            currentCell = currentCell.getRightNeighbor();
        }
        return Integer.parseInt(sb.toString());
    }

    public int getGearRatio() {
        if (!isGear()) {
            return 0;
        }
        final Integer gearRatio = List.of(this.leftNeighbor, this.rightNeighbor, this.topNeighbor, this.bottomNeighbor)
            .stream()
            .filter(neighbor -> neighbor != null && neighbor.isDigit())
            .map(neighbor -> Integer.parseInt("" + neighbor.getContent()))
            .reduce(1, (a, b) -> a * b);
        return gearRatio;
    }

    boolean isGear() {
        return isSymbol() && getContent() == '*' && hasExactlyTwoNeighboringNumbers();
    }

    private boolean hasExactlyTwoNeighboringNumbers() {

        if(this.leftNeighborIsDigit()) {
            // if left is a digit, then
            // - only right is a digit is correct
            // - only top is a digit is correct
            // - only bottom is a digit is correct
            // if none of the above are true,
            // - topleft and top and right are correct
            // ...
            // we need something that can count how many surrounding numbers there are here
            if(this.rightNeighborIsDigit() && ! this.topNeighborIsDigit() && ! this.bottomLeftNeighborIsDigit() && ! this.topLeftNeighborIsDigit() && ! this.topRightNeighborIsDigit() && ! this.bottomLeftNeighborIsDigit() && ! this.bottomRightNeighborIsDigit()) {
                return true;
            }
        }

        final List<Supplier<Boolean>> potentialNeighbors = List.of(
                this::leftNeighborIsDigit,
                this::rightNeighborIsDigit,
                this::topNeighborIsDigit,
                this::bottomNeighborIsDigit,
                this::topLeftNeighborIsDigit,
                this::topRightNeighborIsDigit,
                this::bottomLeftNeighborIsDigit,
                this::bottomRightNeighborIsDigit);
        final long count = potentialNeighbors.stream().filter(Supplier::get).count();

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
