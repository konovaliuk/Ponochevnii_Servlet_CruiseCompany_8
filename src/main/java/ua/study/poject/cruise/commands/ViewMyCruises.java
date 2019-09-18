package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewMyCruises implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.getProperty("path.page.viewmycruises");
    }
}
