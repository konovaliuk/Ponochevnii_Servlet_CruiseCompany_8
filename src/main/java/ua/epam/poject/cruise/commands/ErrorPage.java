package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ErrorPage implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.error");
    }
}
