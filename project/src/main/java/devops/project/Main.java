package devops.project;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Example usage of DataFrame class
        try {
            DataFrame df = new DataFrame("src/main/data/f1db_csv/status.csv");
            System.out.println("DataFrame created successfully.");
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}