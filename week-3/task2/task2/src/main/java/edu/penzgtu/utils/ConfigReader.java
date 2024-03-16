package edu.penzgtu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties readConfig(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        properties.load(inputStream);
        return properties;
    }
}
