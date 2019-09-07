package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("allCruises", new CruiseService().viewAllCruises());
        request.getSession().invalidate();
        return ConfigurationManager.getProperty("path.page.startpage");
    }
}