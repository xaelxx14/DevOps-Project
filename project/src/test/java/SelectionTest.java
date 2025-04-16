import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import devops.project.DataFrame;

public class SelectionTest {

    public DataFrame createSimpleDf() {
        Map<String, List<Object>> data = new HashMap<>();
        data.put("A", Arrays.asList(1, 2, 3, 4, 5));
        data.put("B", Arrays.asList(6, 7, 8, 9, 10));
        data.put("C", Arrays.asList(11, 12, 13, 14, 15));
        return new DataFrame(data);

        // A B C
        // 1 6 11
        // 2 7 12
        // 3 8 13
        // 4 9 14
        // 5 10 15
    }

    @Test
    public void testIlocRowAndColumnRange() throws IOException {
        DataFrame df = createSimpleDf(); 
        DataFrame result = df.iloc(1, 3, 0, 1); // Rows 1-3, Columns 0-1
        assertEquals(3, result.getRowSize());
        assertEquals(2, result.getColumnSize());
    }

    @Test
    public void testIlocSingleRow() throws IOException {
        DataFrame df = createSimpleDf(); 
        DataFrame result = df.iloc(2, -1); // Row 2, all columns
        assertEquals(1, result.getRowSize());
        assertEquals(3, result.getColumnSize());
        assertEquals(Arrays.asList(3), result.getColumns().get("A"));
        assertEquals(Arrays.asList(8), result.getColumns().get("B"));
        assertEquals(Arrays.asList(13), result.getColumns().get("C"));
    }

    @Test
    public void testIlocSingleColumn() throws IOException {
        DataFrame df = createSimpleDf(); 
        DataFrame result = df.iloc(-1, 1); // All rows, Column 1
        assertEquals(5, result.getRowSize());
        assertEquals(1, result.getColumnSize());
        assertEquals(Arrays.asList(6, 7, 8, 9, 10), result.getColumns().get("B"));
    }

    @Test
    public void testCell() throws IOException {
        DataFrame df = createSimpleDf(); 
        DataFrame result = df.iloc(1, 1); // cell[1,1]
        assertEquals(1, result.getRowSize());
        assertEquals(1, result.getColumnSize());
        assertEquals(Arrays.asList(7), result.getColumns().get("B"));

        DataFrame result2 = df.iloc(2, 0); //  cell[2,2]
        assertEquals(1, result2.getRowSize());
        assertEquals(1, result2.getColumnSize());
        assertEquals(Arrays.asList(3), result2.getColumns().get("A"));

        DataFrame result3 = df.iloc(3, 2); // cell[3,2]
        assertEquals(1, result3.getRowSize());
        assertEquals(1, result3.getColumnSize());
        assertEquals(Arrays.asList(14), result3.getColumns().get("C"));
    }


}