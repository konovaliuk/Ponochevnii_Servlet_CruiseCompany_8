package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePort implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

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
            request.getSession().setAttribute("errorMessage", "Не удалось создать порт, проверьте правильность заполнения полей");
        } else request.getSession().setAttribute("errorMessage", "Порт успешно создан");

        return ConfigurationManager.getProperty("path.page.createport");
    }
}
