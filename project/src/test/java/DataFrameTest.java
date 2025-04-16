import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import devops.project.DataFrame;

class DataFrameTest {

    private String TEST_CSV_FILE = "src/main/data/f1db_csv/status.csv";
    @Test
    void testDataFrameConstructorCSV() throws IOException {

        DataFrame df = new DataFrame(TEST_CSV_FILE);
        Map<String, List<Object>> columns = df.getColumns();

        // Verify that the column names are correct
        assertTrue(columns.containsKey("statusId"));
        assertTrue(columns.containsKey("status"));

        // Verify the data in the "statusId" column
        List<Object> idColumn = columns.get("statusId");
        for (int i = 0; i < 10; i++) {
            assertEquals(String.valueOf(i + 1), idColumn.get(i));
        }


        // Verify the data in the "status" column
        List<Object> statusColumn = columns.get("status");
        int size = statusColumn.size() + 1; //(0 to 139 data) +1 to account for the header
        assertEquals(140, size);
        assertEquals("Finished", statusColumn.get(0)); 
        assertEquals("Oil pressure", statusColumn.get(50));
        assertEquals("Turbo", statusColumn.get(100));
        assertEquals("Cooling system", statusColumn.get(statusColumn.size()-1));
    }
    @Test
    void testDataFrameConstructor() throws IOException {
        Map<String, List<Object>> data = new HashMap<>();
        data.put("statusId", List.of("1", "2", "3", "4", "5"));
        data.put("status", List.of("Finished", "Accident", "Engine", "Gearbox", "Suspension"));
        DataFrame df = new DataFrame(data);
        Map<String, List<Object>> columns = df.getColumns();
        // Verify that the column names are correct
        assertTrue(columns.containsKey("statusId"));
        assertTrue(columns.containsKey("status"));
        // Verify the data in the "statusId" column
        List<Object> idColumn = columns.get("statusId");
        for (int i = 0; i < 5; i++) {
            assertEquals(String.valueOf(i + 1), idColumn.get(i));
        }
        // Verify the data in the "status" column
        List<Object> statusColumn = columns.get("status");
        assertEquals("Finished", statusColumn.get(0));
        assertEquals("Accident", statusColumn.get(1));
        assertEquals("Engine", statusColumn.get(2));
        assertEquals("Gearbox", statusColumn.get(3));
        assertEquals("Suspension", statusColumn.get(4));
        assertEquals(5, statusColumn.size());      
    }

}
