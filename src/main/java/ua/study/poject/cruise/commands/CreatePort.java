package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.study.poject.cruise.util.StringStorage.*;

public class CreatePort implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "createnewportMessage";

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        if (request.getParameter(CREATE_PORT_FORM) == null) {
            return ConfigurationManager.getProperty("path.page.createport");
        }

        PortExcursionService portExcursionService = new PortExcursionService();

        String country = request.getParameter(COUNTRY);
        String city = request.getParameter(CITY);

        int result = portExcursionService.createPort(country, city);
        if (result <= 0) {
            request.getSession().setAttribute(MESSAGE, "message.createport.errfaild");
        } else request.getSession().setAttribute(MESSAGE, "message.createport.ok");

        return ConfigurationManager.getProperty("path.page.createport");
    }
}
