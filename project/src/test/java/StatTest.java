import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import devops.project.DataFrame;
import devops.project.DataFrameStatistics;

/**
 * The {@code StatTest} class contains unit tests for the statistical methods of the {@code DataFrameStatistics} class.
 */
public class StatTest {

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

        // 3, 8, 13 (mean)
        // 1, 6, 11 (min)
        // 5, 10, 15 (max)
        // 15, 40, 65 (sum)

    }

    /**
     * Tests the sum method for calculating the sum of a numeric column.
     * It verifies that the sum is calculated correctly for each column.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testSum() throws IOException {
        DataFrame df = createSimpleDf();
        DataFrameStatistics stats = new DataFrameStatistics(df);
        double sumA = stats.sum("A");
        double sumB = stats.sum("B");
        double sumC = stats.sum("C");

        assertEquals(15.0, sumA);
        assertEquals(40.0, sumB);
        assertEquals(65.0, sumC);
    }

    /**
     * Tests the mean method for calculating the mean of a numeric column.
     * It verifies that the mean is calculated correctly for each column.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testMean() throws IOException {
        DataFrame df = createSimpleDf();
        DataFrameStatistics stats = new DataFrameStatistics(df);
        double meanA = stats.mean("A");
        double meanB = stats.mean("B");
        double meanC = stats.mean("C");

        assertEquals(3.0, meanA);
        assertEquals(8.0, meanB);
        assertEquals(13.0, meanC);
    }

    /**
     * Tests the min method for calculating the minimum value of a numeric column.
     * It verifies that the minimum value is calculated correctly for each column.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test 
    public void testMin() throws IOException {
        DataFrame df = createSimpleDf();
        DataFrameStatistics stats = new DataFrameStatistics(df);
        double minA = stats.min("A");
        double minB = stats.min("B");
        double minC = stats.min("C");

        assertEquals(1.0, minA);
        assertEquals(6.0, minB);
        assertEquals(11.0, minC);
    }

    /**
     * Tests the max method for calculating the maximum value of a numeric column.
     * It verifies that the maximum value is calculated correctly for each column.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testMax() throws IOException {
        DataFrame df = createSimpleDf();
        DataFrameStatistics stats = new DataFrameStatistics(df);
        double maxA = stats.max("A");
        double maxB = stats.max("B");
        double maxC = stats.max("C");

        assertEquals(5.0, maxA);
        assertEquals(10.0, maxB);
        assertEquals(15.0, maxC);
    }

    /**
     * Tests invalid column name.
     * It verifies that an IllegalArgumentException is thrown for each method.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testInvalidColumn() throws IOException {
        DataFrame df = createSimpleDf();
        DataFrameStatistics stats = new DataFrameStatistics(df);

        // Test for a non-existent column
        assertThrows(IllegalArgumentException.class, () -> {
            stats.sum("D");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stats.mean("D");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stats.min("D");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stats.max("D");
        });
    }

    /**
     * Tests non-numeric column.
     * It verifies that an IllegalArgumentException is thrown for each method.
     * @throws IOException if an error occurs while creating the DataFrame.
     */
    @Test
    public void testNonNumericColumn() throws IOException {
        Map<String, List<Object>> data = new HashMap<>();
        data.put("A", Arrays.asList(1, 2, 3, 4, 5));
        data.put("B", Arrays.asList("a", "b", "c", "d", "e")); // Non-numeric column
        DataFrame df = new DataFrame(data);
        DataFrameStatistics stats = new DataFrameStatistics(df);

        // Test for a non-numeric column
        assertThrows(IllegalArgumentException.class, () -> {
            stats.sum("B");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stats.mean("B");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stats.min("B");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stats.max("B");
        });
    }


}