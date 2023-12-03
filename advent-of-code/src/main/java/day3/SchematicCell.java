package day3;

import java.util.Objects;

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
