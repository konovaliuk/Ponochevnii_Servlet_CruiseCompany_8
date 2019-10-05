package ua.study.poject.cruise.resource;

import java.util.ResourceBundle;

/**
 * Class retrieves information from file messages.properties
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    private MessageManager() {}
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
