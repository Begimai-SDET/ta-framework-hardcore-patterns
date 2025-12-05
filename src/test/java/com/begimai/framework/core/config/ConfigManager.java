package com.begimai.framework.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final ConfigManager INSTANCE = new ConfigManager();

    private final Properties properties = new Properties();

    private ConfigManager() {
        String env = System.getProperty("env", "dev");
        String fileName = String.format("config/config.%s.properties", env);

        System.out.println(">>> [ConfigManager] LOADING CONFIG FOR ENV = " + env + " (" + fileName + ")");

        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + fileName, e);
        }
    }

    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.");
        }
        return value;
    }
}
