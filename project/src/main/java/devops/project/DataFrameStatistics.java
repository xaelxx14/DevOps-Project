package devops.project;

import java.util.List;
import java.util.Map;

/**
 * The {@code DataFrameStatistics} class provides statistical methods for a DataFrame.
 * It allows for calculating the sum, mean, min, and max of numeric columns.
 */
public class DataFrameStatistics {

    /**
     * The DataFrame object that this class operates on.
     */
    private final DataFrame dataFrame;

    /**
     * Constructs a DataFrameStatistics object with the given DataFrame.
     *
     * @param dataFrame The DataFrame to perform statistics on.
     */
    public DataFrameStatistics(DataFrame dataFrame) {
        this.dataFrame = dataFrame;
    }

    /**
     * Calculates the sum of a numeric column in the DataFrame.
     * @param columnName The name of the column to sum.
     * @return The sum of the column.
     * @throws IllegalArgumentException if the column does not exist or is not numeric.
     */
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

    /**
     * Calculates the mean of a numeric column in the DataFrame.
     * @param columnName The name of the column to calculate the mean for.
     * @return The mean of the column.
     * @throws IllegalArgumentException if the column does not exist or is not numeric.
     */
    public double mean(String columnName) {
        if (!dataFrame.getColumns().containsKey(columnName)) {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        List<Object> columnData = dataFrame.getColumns().get(columnName);
        return sum(columnName) / columnData.size();
    }

    /**
     * Calculates the minimum value of a numeric column in the DataFrame.
     * @param columnName The name of the column to find the minimum for.
     * @return The minimum value of the column.
     * @throws IllegalArgumentException if the column does not exist or is not numeric.
     */
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

    /**
     * Calculates the maximum value of a numeric column in the DataFrame.
     * @param columnName The name of the column to find the maximum for.
     * @return The maximum value of the column.
     * @throws IllegalArgumentException if the column does not exist or is not numeric.
     */
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