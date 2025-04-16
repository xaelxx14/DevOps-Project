package devops.project;

import java.util.List;
import java.util.Map;

public class DataFrameStatistics {

    private final DataFrame dataFrame;

    public DataFrameStatistics(DataFrame dataFrame) {
        this.dataFrame = dataFrame;
    }

    public double sum(String columnName) {
        if (!dataFrame.getColumns().containsKey(columnName)) {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }

        List<Object> columnData = dataFrame.getColumns().get(columnName);
        double sum = 0;

        for (Object value : columnData) {
            if (value instanceof Number) {
                sum += ((Number) value).doubleValue();
            } else {
                throw new IllegalArgumentException("Column data is not numeric: " + columnName);
            }
        }

        return sum;
    }

    public double mean(String columnName) {
        if (!dataFrame.getColumns().containsKey(columnName)) {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        List<Object> columnData = dataFrame.getColumns().get(columnName);
        return sum(columnName) / columnData.size();
    }

    public double min(String columnName) {
        if (!dataFrame.getColumns().containsKey(columnName)) {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        List<Object> columnData = dataFrame.getColumns().get(columnName);
        double min = Double.MAX_VALUE;

        for (Object value : columnData) {
            if (value instanceof Number) {
                min = Math.min(min, ((Number) value).doubleValue());
            } else {
                throw new IllegalArgumentException("Column data is not numeric: " + columnName);
            }
        }
        return min;
    }

    public double max(String columnName) {
        if (!dataFrame.getColumns().containsKey(columnName)) {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        List<Object> columnData = dataFrame.getColumns().get(columnName);
        double max = Double.MIN_VALUE;

        for (Object value : columnData) {
            if (value instanceof Number) {
                max = Math.max(max, ((Number) value).doubleValue());
            } else {
                throw new IllegalArgumentException("Column data is not numeric: " + columnName);
            }
        }
        return max;
    }

}