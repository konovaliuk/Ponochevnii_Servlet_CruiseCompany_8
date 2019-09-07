package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;//id, excursion_name, price, description, port_id
import javax.servlet.http.HttpServletResponse;

public class CreateExcursion implements Action {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }
        PortExcursionService portExcursionService = new PortExcursionService();

        request.getSession().setAttribute("allPorts", portExcursionService.getAllPorts());

        if (request.getParameter("createexcursionForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.createexcursion");
        }

        String excursionName = request.getParameter("excursionName");
        String description = request.getParameter("description");
        try {
            double price = Double.parseDouble(request.getParameter("price"));
            Long portId = Long.parseLong(request.getParameter("selectedport"));

            int result = portExcursionService.createExcursion(excursionName, price, description, portId);
            if (result <= 0) {
                request.getSession().setAttribute("errorMessage", "Не удалось создать экскурсию, проверьте правильность заполнения полей");
            } else request.getSession().setAttribute("errorMessage", "Экскурсия успешно создана");

        }catch (NumberFormatException e){
            request.getSession().setAttribute("errorMessage", "Не удалось создать экскурсию, проверьте правильность заполнения полей");
        }
        return ConfigurationManager.getProperty("path.page.createexcursion");
    }

}
