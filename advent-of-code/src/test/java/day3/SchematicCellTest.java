package day3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SchematicCellTest {

    @Test
    public void digitIsNotASymbol() {
        final SchematicCell c = new SchematicCell('5');
        assertThat(c.isSymbol()).isFalse();
        assertThat(c.isDigit()).isTrue();
    }

    @Test
    public void isGearTest() {
        final SchematicCell topNeighbor = new SchematicCell('5');
        final SchematicCell bottomNeighbor = new SchematicCell('3');
        final SchematicCell gearCel = new SchematicCell('*');
        gearCel.setTopNeighbor(topNeighbor);
        gearCel.setBottomNeighbor(bottomNeighbor);

        assertThat(gearCel.isGear()).isTrue();
    }

    @Test
    public void isGearTest2() {
        final SchematicCell topNeighbor = new SchematicCell('5');
        final SchematicCell topleftNeighbor = new SchematicCell('5');
        final SchematicCell bottomNeighbor = new SchematicCell('3');
        final SchematicCell gearCel = new SchematicCell('*');
        topNeighbor.setLeftNeighbor(topleftNeighbor);
        gearCel.setTopNeighbor(topNeighbor);
        gearCel.setBottomNeighbor(bottomNeighbor);

        assertThat(gearCel.isGear()).isTrue();
    }

    @Test
    public void isNotAGearTest() {
        final SchematicCell gearCel = new SchematicCell('*');
        gearCel.setTopNeighbor(new SchematicCell('5'));
        gearCel.setBottomNeighbor(new SchematicCell('3'));
        gearCel.setLeftNeighbor(new SchematicCell('1'));
        gearCel.setRightNeighbor(new SchematicCell('2'));

        assertThat(gearCel.isGear()).isFalse();
    }

    @Test
    public void isNotAGearTest2() {
        final SchematicCell gearCel = new SchematicCell('*');

        assertThat(gearCel.isGear()).isFalse();
    }
}
