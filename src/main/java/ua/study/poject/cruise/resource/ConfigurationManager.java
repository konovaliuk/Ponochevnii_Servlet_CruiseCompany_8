package ua.study.poject.cruise.resource;

import java.util.ResourceBundle;

/**
 * Class retrieves information from file config.properties
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

