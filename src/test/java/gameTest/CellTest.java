package gameTest;

import game.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void cellConstructorTest() {
        Cell cell = new Cell(2);
        assertEquals(2, cell.getColor());
    }

}
