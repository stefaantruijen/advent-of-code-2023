package day3;

import java.util.ArrayList;
import java.util.List;

public class GondolaSchematicBuilder {

    private final List<List<SchematicCell>> gondolaSchematicCells = new ArrayList<>();

    public GondolaSchematicBuilder addLine(final String line) {
        final char[] lineAsChars = line.toCharArray();
        SchematicCell previousCell = null;
        final List<SchematicCell> previousLine = gondolaSchematicCells.size() > 0 ? gondolaSchematicCells.getLast() : null;
        final List<SchematicCell> cellsOnThisLine = new ArrayList<>();
        for (int i = 0; i < lineAsChars.length; i++) {
            final SchematicCell currentSchematicCell = new SchematicCell(lineAsChars[i]);
            cellsOnThisLine.add(currentSchematicCell);

            // we know the left neighbor already
            currentSchematicCell.setLeftNeighbor(previousCell);

            // therefore we also know the right neighbor of our left neighbor - it is us
            if (previousCell != null) {
                previousCell.setRightNeighbor(currentSchematicCell);
            }

            // we now know the bottom neighbor of the cell on the previous line
            if (previousLine != null) {
                final SchematicCell topNeighborOfCurrentCell = previousLine.get(i);
                currentSchematicCell.setTopNeighbor(topNeighborOfCurrentCell);
                topNeighborOfCurrentCell.setBottomNeighbor(currentSchematicCell);
            }
            // our bottom and right neighbors will be filled in later
            currentSchematicCell.setRightNeighbor(null);
            currentSchematicCell.setBottomNeighbor(null);

            previousCell = currentSchematicCell;
        }
        gondolaSchematicCells.add(cellsOnThisLine);
        return this;
    }

    public GondolaSchematic build() {
        return new GondolaSchematic(gondolaSchematicCells);
    }
}
