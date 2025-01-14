package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonReader {
    private List<Map<String, String>> data;

    public JsonReader(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(new File(filePath), List.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + filePath, e);
        }
    }

    public String getData(int rowIndex, String key) {
        if (rowIndex >= data.size()) {
            throw new IllegalArgumentException("Invalid row index: " + rowIndex);
        }
        Map<String, String> row = data.get(rowIndex);
        return row.get(key);
    }
}
