package edu.penzgtu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceReader {
    public static Properties readConfig(String configFile) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getInputStream(configFile);
        properties.load(inputStream);
        return properties;
    }

    public static InputStream getInputStream(String file)  {
        return ClassLoader.getSystemResourceAsStream(file);
    }
}
