package devops.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        // Example usage of DataFrame class
        try {
            DataFrame df = new DataFrame("project/src/main/data/f1db_csv/status.csv");
            System.out.println("DataFrame 1 created successfully.");
            df.printDataFrame();

            System.out.println("------------------------------------------------");
            System.out.println("First 10 rows of the DataFrame:");
            df.printFirstLines(10);

            System.out.println("------------------------------------------------");
            System.out.println("Last 10 rows of the DataFrame:");
            df.printLastLines(10);
            System.out.println("------------------------------------------------");
            Map<String, List<Object>> data = new HashMap<>();
            data.put("Id", List.of("1", "2", "3", "4", "5"));
            data.put("Color", List.of("Blue", "Red", "Green", "Black", "Wwhite"));
            DataFrame df_ = new DataFrame(data);
            System.out.println("DataFrame 2 created successfully.");
            df_.printDataFrame();
            System.out.println("------------------------------------------------");
            System.out.println("First 4 rows of the DataFrame:");
            df_.printFirstLines(4);
            System.out.println("------------------------------------------------");
            System.out.println("Last 2 rows of the DataFrame:");
            df_.printLastLines(2);

            System.out.println("------------------------------------------------");
            System.out.println("Select row by index : (3,-1)");
            DataFrame selectedRow =df.iloc(3,-1);
            selectedRow.printDataFrame();

            System.out.println("------------------------------------------------");
            System.out.println("Select column by index : (-1,1)");
            DataFrame selectedCol = df.iloc(-1,1);
            selectedCol.printDataFrame();

            System.out.println("------------------------------------------------");
            System.out.println("Select rows and column by index : (0,5,1,1)");
            DataFrame selected = df.iloc(0, 5, 1, 1);
            selected.printDataFrame();

            System.out.println("------------------------------------------------");
            System.out.println("Select cell by row and cloumn index: (0,1)");
            DataFrame cell = df.iloc(0, 1);
            cell.printDataFrame();

        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}