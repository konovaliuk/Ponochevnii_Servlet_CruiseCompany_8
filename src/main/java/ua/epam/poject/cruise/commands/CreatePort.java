package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;

public class CreatePort implements Action {
    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        if (request.getParameter("createportForm") == null) {
            return ConfigurationManager.getProperty("path.page.createport");
        }

        PortExcursionService portExcursionService = new PortExcursionService();

        String country = request.getParameter("country");
        String city = request.getParameter("city");

        int result = portExcursionService.createPort(country, city);
        if (result <= 0) {
            request.setAttribute("errorMessage", "Не удалось создать порт, проверьте правильность заполнения полей");
        } else request.setAttribute("errorMessage", "Порт успешно создан");

        return ConfigurationManager.getProperty("path.page.createport");
    }
}
