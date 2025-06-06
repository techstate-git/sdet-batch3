package com.cgstikers.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final String FILE_PATH = "src/test/resources/config/secrets.properties";
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("❌ Cannot load secret.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void set(String key, String value) {
        try {
            properties.setProperty(key, value);
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                properties.store(fos, null);
            }
        } catch (IOException e) {
            throw new RuntimeException("❌ Cannot write to secret.properties", e);
        }
    }

    public static void reload() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("❌ Cannot reload secret.properties", e);
        }
    }
}
