package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.study.poject.cruise.util.StringStorage.ALL_CRUISES;

public class StartPage implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute(ALL_CRUISES, new CruiseService().viewAllCruises());
        return ConfigurationManager.getProperty("path.page.startpage");
    }
}
