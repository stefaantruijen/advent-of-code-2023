package day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GondolaSchematic {

    private final List<List<SchematicCell>> gondolaSchematic;

    public static GondolaSchematicBuilder builder() {
        return new GondolaSchematicBuilder();
    }

    GondolaSchematic(final List<List<SchematicCell>> gondolaSchematicCells) {
        this.gondolaSchematic = gondolaSchematicCells;
    }

    public List<Integer> getPartNumbers() {
        final List<Integer> partNumbers = new ArrayList<>();
        for (final List<SchematicCell> line : gondolaSchematic) {
            for (final SchematicCell currentSchematicCell : line) {
                if (currentSchematicCell.isSymbol()) {
//                    System.out.println("Found cell that is a symbol");
                    // orthogonally
                    final SchematicCell topNeighbor = currentSchematicCell.getTopNeighbor();
                    final SchematicCell bottomNeighbor = currentSchematicCell.getBottomNeighbor();
                    final List<SchematicCell> neighboringCells = new ArrayList<>(Arrays.asList(
                            currentSchematicCell.getLeftNeighbor(),
                            currentSchematicCell.getRightNeighbor(),
                            topNeighbor,
                            bottomNeighbor));

                    // diagonally
                    if (topNeighbor != null) {
                        neighboringCells.add(topNeighbor.getLeftNeighbor());
                        neighboringCells.add(topNeighbor.getRightNeighbor());
                    }
                    if (bottomNeighbor != null) {
                        neighboringCells.add(bottomNeighbor.getLeftNeighbor());
                        neighboringCells.add(bottomNeighbor.getRightNeighbor());
                    }

                    for (final SchematicCell neighborCell : neighboringCells) {
//                        System.out.println("Processing neighbor cell " + neighborCell);
                        if (neighborCell != null && neighborCell.isDigit()) {
                            final int numberOfAdjacentCells = neighborCell.getNumberOfAdjacentCells();
//                            System.out.println("Adding number to list: " + numberOfAdjacentCells);
                            partNumbers.add(numberOfAdjacentCells);
                        }
                    }
                }
            }
        }
        return partNumbers;
    }

}
