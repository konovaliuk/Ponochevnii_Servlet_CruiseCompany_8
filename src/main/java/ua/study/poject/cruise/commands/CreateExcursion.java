package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;//id, excursion_name, price, description, port_id
import javax.servlet.http.HttpServletResponse;

import static ua.study.poject.cruise.util.StringStorage.*;

public class CreateExcursion implements Action {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "createexcursionMessage";
        final String ALL_PORTS = "allPorts";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }
        PortExcursionService portExcursionService = new PortExcursionService();

        request.getSession().setAttribute(ALL_PORTS, portExcursionService.getAllPorts());

        if (request.getParameter(CREATE_EXCURSION_FORM) == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.createexcursion");
        }

        String excursionName = request.getParameter(EXCURSION_NAME);
        String description = request.getParameter(DESCRIPTION);
        if(excursionName.length() < 2 || description.length() < 2){
            request.getSession().setAttribute(MESSAGE, "message.createexcursion.errfaild");
            return ConfigurationManager.getProperty("path.page.createexcursion");
        }
        try {
            double price = Double.parseDouble(request.getParameter(PRICE));
            if(price < 0){
                request.getSession().setAttribute(MESSAGE, "message.createexcursion.errfaild");
                return ConfigurationManager.getProperty("path.page.createexcursion");
            }
            Long portId = Long.parseLong(request.getParameter(SELECTED_PORT));

            int result = portExcursionService.createExcursion(excursionName, price, description, portId);
            if (result <= 0) {
                request.getSession().setAttribute(MESSAGE, "message.createexcursion.errfaild");
            } else request.getSession().setAttribute(MESSAGE, "message.createexcursion.ok");

        }catch (NumberFormatException e){
            request.getSession().setAttribute(MESSAGE, "message.createexcursion.errfaild");
        }
        return ConfigurationManager.getProperty("path.page.createexcursion");
    }

}
