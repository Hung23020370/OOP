import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClearRowsTest {

    @Test
    void clearRows() {
        boolean[][] test = new boolean[][] {
                    {true, true, true},
                    {true, true, false},
                    {false, false, true},
                    {true, true, true},
                    {false, true, true},
                    {false, false, false},
                    {false, false, false},
        };
        boolean[][] expectResult = new boolean[][] {
                {true, true, false},
                {false, false, true},
                {false, true, true},
                {false, false, false},
                {false, false, false},
                {false, false, false},
                {false, false, false},
        };
        ClearRows clearrows = new ClearRows();
        boolean[][] actualResult = clearrows.clearRows(test);
        assertArrayEquals(expectResult,actualResult);
    }
}