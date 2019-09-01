package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.printableentity.PrintableCruise;
import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.CruiseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StartPage implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("allCruises", new CruiseService().viewAllCruises());
        return ConfigurationManager.getProperty("path.page.startpage");
    }
}
