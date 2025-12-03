package com.begimai.framework.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        String env = System.getProperty("env", "dev");
        String fileName = String.format("config/config.%s.properties", env);

        System.out.println(">>> LOADING CONFIG FOR ENV = " + env + " (" + fileName + ")");

        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + fileName, e);
        }
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.");
        }
        return value;
    }
}
