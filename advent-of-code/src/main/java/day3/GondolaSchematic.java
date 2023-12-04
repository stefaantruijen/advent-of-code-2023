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
                if (currentSchematicCell.isGear()) {
                    final List<SchematicCell> gearRatioCells = new ArrayList<>();
                    if(currentSchematicCell.getLeftNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getLeftNeighbor());
                    }
                    if(currentSchematicCell.getRightNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getRightNeighbor());
                    }
                    if (currentSchematicCell.getTopNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getTopNeighbor());
                    }
                    if (currentSchematicCell.getBottomNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getBottomNeighbor());
                    }
                    if (currentSchematicCell.getTopNeighbor().getLeftNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getTopNeighbor().getLeftNeighbor());
                    }
                    if (currentSchematicCell.getTopNeighbor().getRightNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getTopNeighbor().getRightNeighbor());
                    }
                    if (currentSchematicCell.getBottomNeighbor().getLeftNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getBottomNeighbor().getLeftNeighbor());
                    }
                    if (currentSchematicCell.getBottomNeighbor().getRightNeighbor().isDigit()) {
                        gearRatioCells.add(currentSchematicCell.getBottomNeighbor().getRightNeighbor());
                    }
                    final Integer gearRatio = gearRatioCells
                            .stream()
                            .filter(cell -> cell != null)
                            .filter(SchematicCell::isDigit)
                            .map(SchematicCell::getNumberOfAdjacentCells)
                            .filter(number -> number != 0)
                            .reduce(1, (a, b) -> a * b);
                    System.out.println("added gear ratio " + gearRatio + " for cell " + currentSchematicCell);
                    gearRatios.add(gearRatio);
                }
            }
        }
        return gearRatios;
    }

}
