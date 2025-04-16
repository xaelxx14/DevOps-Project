package devops.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFrame {
    //Key represents the column name and each value is a list of data for that column.
    private Map<String, List<Object>> columns;

    //Constructor taking as parameters the contents of each column
    public DataFrame(Map<String, List<Object>> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
        this.columns = new HashMap<>(data); 
    }
    
    //Constructor taking as parameter the name of a CSV file
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

    public Map<String, List<Object>> getColumns() {
        return columns;
    }

    public int  getColumnSize(){
        return columns.size();
    }

    public int getRowSize() {
        return columns.values().iterator().next().size();
    }

    //Print methods for the dataframe

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
}
