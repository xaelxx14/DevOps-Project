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

/**
 * The {@code SelectionTest} class contains unit tests for the selection methods of the {@code DataFrame} class.
 */
public class SelectionTest {

    /**
     * Creates a simple DataFrame for testing purposes.
     * The DataFrame contains three columns: A, B, and C, with five rows of integer data.
     * @return A DataFrame with three columns and five rows.
     */
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

    /**
     * Tests the iloc method for selecting a range of rows and columns.
     * It verifies that the resulting DataFrame has the expected number of rows and columns.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testIlocRowAndColumnRange() throws IOException {
        DataFrame df = createSimpleDf(); 
        DataFrame result = df.iloc(1, 3, 0, 1); // Rows 1-3, Columns 0-1
        assertEquals(3, result.getRowSize());
        assertEquals(2, result.getColumnSize());
    }

    /**
     * Tests the iloc method for selecting a single row.
     * It verifies that the resulting DataFrame has the expected number of rows and columns.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
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

    /**
     * Tests the iloc method for selecting a single column.
     * It verifies that the resulting DataFrame has the expected number of rows and columns.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testIlocSingleColumn() throws IOException {
        DataFrame df = createSimpleDf(); 
        DataFrame result = df.iloc(-1, 1); // All rows, Column 1
        assertEquals(5, result.getRowSize());
        assertEquals(1, result.getColumnSize());
        assertEquals(Arrays.asList(6, 7, 8, 9, 10), result.getColumns().get("B"));
    }

    /**
     * Tests the iloc method for selecting a single cell.
     * It verifies that the resulting DataFrame has the expected number of rows, columns and value.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
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

    /**
     * Tests the selectByName method for selecting specific columns by name.
     * It verifies that the resulting DataFrame has the expected number of rows, columns and value.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testSelectByName() throws IOException {
        DataFrame df = createSimpleDf(); 
        DataFrame result = df.selectByName("A","C");
        assertEquals(5, result.getRowSize());
        assertEquals(2, result.getColumnSize());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result.getColumns().get("A"));
        assertEquals(Arrays.asList(11, 12, 13, 14, 15), result.getColumns().get("C"));
    }

    /**
     * Tests invalid index access in the iloc method.
    */
    @Test
    public void testBadIndex(){
        DataFrame df = createSimpleDf(); 
        assertThrows(IndexOutOfBoundsException.class, () -> {
            df.iloc(10, 1); // invalid row 
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            df.iloc(1, 10); // invalid column 
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            df.iloc(-1, -1); // invalid double negative
        });
    }

    /**
     * Tests invalid column name access in the selectByName method.
     * It verifies that an IllegalArgumentException is thrown when an invalid column name is provided.
     */
    @Test
    public void testBadName() {
        DataFrame df = createSimpleDf(); 
        assertThrows(IllegalArgumentException.class, () -> {
            df.selectByName("X"); // invalid name
        });
        assertThrows(IllegalArgumentException.class, () -> {
            df.selectByName("A", "X"); // one valid and one invalid  name
        });
    }


}