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
}
