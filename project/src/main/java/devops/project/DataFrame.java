package devops.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataFrame {
    //Key represents the column name and each value is a list of data for that column.
    private Map<String, List<Object>> columns;

    //Constructor taking as parameters the contents of each column
    public DataFrame(Map<String, List<Object>> columns) {
        this.columns = columns;
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
}
