package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.Service;
import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;

public class AddShipServicesToSystem implements Action {
    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipserviceService shipServiceService = new ShipserviceService();
        request.setAttribute("allServices", shipServiceService.getAllServisesInSystem());

        request.setAttribute("allShipServicesInSystem", shipServiceService.getAllServisesInSystem());

        if (request.getParameter("addshipservicestosystemForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.addshipservicestosystem");
        }

        // id, service_name
        String newServiseInSystem = request.getParameter("newServiseInSystem");

        Service shipService = shipServiceService.getServeceByName(newServiseInSystem);

        if (shipService.getId() != -1) {
            request.setAttribute("errorMessage", "Такой сервис уже есть в системе");
            return ConfigurationManager.getProperty("path.page.addshipservicestosystem");
        }

        int result = shipServiceService.addNewServiceToSystem(newServiseInSystem);

        if (result <= 0) {
            request.setAttribute("errorMessage", "Не удалось создать сервис, проверьте правильность заполнения полей");
        } else {
            request.setAttribute("allServices", shipServiceService.getAllServisesInSystem());
            request.setAttribute("errorMessage", "Сервис успешно создан");
        }

        return ConfigurationManager.getProperty("path.page.addshipservicestosystem");
    }


}
