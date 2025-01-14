package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // Making the props object public so it can be accessed directly
    public static final Properties props = new Properties();

    // Static block to load properties from the configuration file
    static {
        try (FileInputStream fis = new FileInputStream("src/sources/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    // Method to get a property value by key
    public static String getProperty(String key) {
        return props.getProperty(key);
    }
    
    public void SendFile() {
    	String filename = "C:\\Users\\SuprokasMoulick\\Downloads";
    	String destinationFileName = "C:\\Users\\SuprokasMoulick\\Downloads";
    }

    // Method to reload the properties file (if needed during runtime)
    public static void reloadProperties() {
        try (FileInputStream fis = new FileInputStream("src/sources/config.properties")) {
            props.clear();
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to reload config.properties file.");
        }
    }
}
