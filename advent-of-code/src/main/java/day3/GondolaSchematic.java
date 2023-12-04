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
                        if (neighborCell != null && neighborCell.isDigit()) {
                            final int numberOfAdjacentCells = neighborCell.getNumberOfAdjacentCells();
                            partNumbers.add(numberOfAdjacentCells);
                        }
                    }
                }
            }
        }
        return partNumbers;
    }

    public List<Integer> getGearRatios() {
        final List<Integer> gearRatios = new ArrayList<>();
        for (final List<SchematicCell> line : gondolaSchematic) {
            for (final SchematicCell currentSchematicCell : line) {
                if (currentSchematicCell.getGearRatio() != 0) {
                    gearRatios.add(currentSchematicCell.getGearRatio());
                }
            }
        }
        return gearRatios;
    }

}
