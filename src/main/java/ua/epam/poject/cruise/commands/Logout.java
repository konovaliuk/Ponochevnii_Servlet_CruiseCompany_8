package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.CruiseService;

import javax.servlet.http.HttpServletRequest;

public class Logout implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("allCruises", new CruiseService().viewAllCruises());
        String page = ConfigurationManager.getProperty("path.page.startpage");
        // уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}