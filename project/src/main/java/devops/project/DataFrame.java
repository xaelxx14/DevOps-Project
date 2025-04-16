package devops.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code DataFrame} class represents a data frame where each column is a list of values.
 * It provides methods for data manipulation, selection, and printing.
 */
public class DataFrame {
    /**
     * A map where the key represents the column name and the value is a list of data for that column.
     */
    private Map<String, List<Object>> columns;

    /**
     * Constructs a DataFrame from a map of column names and their corresponding data.
     * @param data A map where the key is the column name and the value is a list of data for that column.
     * @throws IllegalArgumentException if the data is null or empty.
     */
    public DataFrame(Map<String, List<Object>> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
        this.columns = new HashMap<>(data); 
    }
    
    /**
     * Constructs a DataFrame from a CSV file.
     * @param fileName The name of the CSV file to read.
     * @throws IOException if an error occurs while reading the file.
     */
    public DataFrame(String fileName) throws IOException {
        columns = new HashMap<>();
        BufferedReader buffRead = new BufferedReader(new FileReader(fileName));
       
        //First line contains column names (headers)
        String headerLine = buffRead.readLine();
        String[] headers = headerLine.split(","); 
        for (String header : headers) {
            columns.put(header, new ArrayList<>());
        }

        //Add values to relative columns
        String line;
        while ((line = buffRead.readLine()) != null) {
            String[] data = line.split(",");
            for (int i = 0; i < data.length; i++) {
                //check if the first character is " to adjust the data
                if (data[i].charAt(0) == '"') {
                    data[i] = data[i].substring(1, data[i].length() - 1);
                }
                columns.get(headers[i]).add(data[i]);
            }
        }
        buffRead.close();
    }

    /**
     * Returns the columns of the DataFrame.
     * @return A map where the key is the column name and the value is a list of data for that column.
     */
    public Map<String, List<Object>> getColumns() {
        return columns;
    }

    public int  getColumnSize(){
        return columns.size();
    }

    public int getRowSize() {
        return columns.values().iterator().next().size();
    }

    /**
     * Prints the DataFrame to the console.
    */
    public void printDataFrame() {
        //Column names
        for (String columnName : columns.keySet()) {
            System.out.print(columnName + "\t");
        }
        System.out.println();
        //Vlues of each column
        int numRows = getRowSize();
        for (int i = 0; i < numRows; i++) {
            for (String columnName : columns.keySet()) {
                System.out.print(columns.get(columnName).get(i) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Prints the first n lines of the DataFrame to the console.
     * @param n The number of lines to print.
     */
    public void printFirstLines(int n) {
        //Column names
        for (String columnName : columns.keySet()) {
            System.out.print(columnName + "\t");
        }
        System.out.println();
        //The first n lines
        for (int i = 0; i < n; i++) {
            for (String columnName : columns.keySet()) {
                System.out.print(columns.get(columnName).get(i) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Prints the last n lines of the DataFrame to the console.
     * @param n The number of lines to print.
     */
    public void printLastLines(int n) {
        //Column names
        for (String columnName : columns.keySet()) {
            System.out.print(columnName + "\t");
        }
        System.out.println();
        //The last n lines
        int numRows = columns.values().iterator().next().size();
        int startRow = numRows -n ;
        for (int i = startRow; i < numRows; i++) {
            for (String columnName : columns.keySet()) {
                System.out.print(columns.get(columnName).get(i) + "\t");
            }
            System.out.println();
        }
    }
    
    /**
     * Returns a new DataFrame containing the specified rows and columns.
     * @param rowStart The starting index of the rows to select.
     * @param rowEnd The ending index of the rows to select.
     * @param colStart The starting index of the columns to select.
     * @param colEnd The ending index of the columns to select.
     * @return A new DataFrame containing the selected rows and columns.
     */
    public DataFrame iloc(int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowStart < 0 || rowEnd > getRowSize() || rowStart > rowEnd) {
            throw new IndexOutOfBoundsException("Row indices out of bounds: " + rowStart + " to " + rowEnd);
        }
        if (colStart < 0 || colEnd > getColumnSize() || colStart > colEnd) {
            throw new IndexOutOfBoundsException("Column indices out of bounds: " + colStart + " to " + colEnd);
        }
    
        List<String> columnNames = new ArrayList<>(columns.keySet());
        Map<String, List<Object>> selected = new HashMap<>();
        for (int i = colStart; i <= colEnd; i++) {
            String columnName = columnNames.get(i);
            List<Object> selectedData = new ArrayList<>();
            for (int j = rowStart; j <= rowEnd; j++) {
                selectedData.add(columns.get(columnName).get(j));
            }
            selected.put(columnName, selectedData);
        }
        return new DataFrame(selected);
    }

    /**
     * Returns a new DataFrame containing the specified cell or row/column.
     * @param rowIndex The index of the row to select.
     * @param colIndex The index of the column to select.
     * @return A new DataFrame containing the selected cell or row/column.
     */
    public DataFrame iloc(int rowIndex, int colIndex) {
        if (rowIndex < -1 || rowIndex >= getRowSize()) {
            throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex);
        }
        if (colIndex < -1 || colIndex >= getColumnSize()) {
            throw new IndexOutOfBoundsException("Column index out of bounds: " + colIndex);
        }
    
        if (rowIndex == -1) {
            return iloc(0, getRowSize()-1, colIndex, colIndex);
        } else if (colIndex == -1) {
            return iloc(rowIndex, rowIndex, 0, getColumnSize()-1);
        } else {
            return getCell(rowIndex, colIndex);
        }
    }

    /**
     * Returns a new DataFrame containing the specified cell.
     * @param rowIndex The index of the row to select.
     * @param colIndex The index of the column to select.
     * @return A new DataFrame containing the selected cell.
     */
    public DataFrame getCell(int rowIndex, int colIndex) {
        if (rowIndex < 0 || colIndex < 0 || rowIndex >= getRowSize() || colIndex >= getColumnSize()) {
            throw new IndexOutOfBoundsException("Row or column index out of bounds: rowIndex=" + rowIndex + ", colIndex=" + colIndex);
        }
        List<String> columnNames = new ArrayList<>(columns.keySet());
        String columnName = columnNames.get(colIndex);
        List<Object> cell = new ArrayList<>();
        cell.add(columns.get(columnName).get(rowIndex));
        Map<String, List<Object>> selected = new HashMap<>();
        selected.put(columnName, cell);
        return new DataFrame(selected);
    }

    /**
     * Returns a new DataFrame containing the specified columns by their names.
     * @param names The names of the columns to select.
     * @return A new DataFrame containing the selected columns.
     */
    public DataFrame selectByName(String... names) { // select by label
        Map<String, List<Object>> selected = new HashMap<>();
        for (String name : names) {
            if (!columns.containsKey(name)) {
                throw new IllegalArgumentException("Column not found: " + name);
            }
            selected.put(name, columns.get(name));
        }
        return new DataFrame(selected);
    }
     
}
