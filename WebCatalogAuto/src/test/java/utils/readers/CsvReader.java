package utils.readers;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private CsvReader() {}

    public static List<String[]> readCsv(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                records.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV file: " + filePath, e);
        }
        return records;
    }
}