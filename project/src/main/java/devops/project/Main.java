package devops.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Example usage of DataFrame class
        try {
            DataFrame df = new DataFrame("src/main/data/f1db_csv/status.csv");
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

        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}