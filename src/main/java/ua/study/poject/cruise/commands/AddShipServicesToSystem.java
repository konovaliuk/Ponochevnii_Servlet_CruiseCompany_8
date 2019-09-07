package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddShipServicesToSystem implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipserviceService shipServiceService = new ShipserviceService();
        request.getSession().setAttribute("allServices", shipServiceService.getAllServisesInSystem());

        request.getSession().setAttribute("allShipServicesInSystem", shipServiceService.getAllServisesInSystem());

        if (request.getParameter("addshipservicestosystemForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.addshipservicestosystem");
        }

        // id, service_name
        String newServiseInSystem = request.getParameter("newServiseInSystem");

        Service shipService = shipServiceService.getServeceByName(newServiseInSystem);

        if (shipService.getId() != -1) {
            request.getSession().setAttribute("addshipservicestosystemMessage", "message.addshipservicestosystem.servicealreadyexist");
            return ConfigurationManager.getProperty("path.page.addshipservicestosystem");
        }

        int result = shipServiceService.addNewServiceToSystem(newServiseInSystem);

        if (result <= 0) {
            request.getSession().setAttribute("addshipservicestosystemMessage", "message.addshipservicestosystem.faildtocreate");
        } else {
            request.getSession().setAttribute("allServices", shipServiceService.getAllServisesInSystem());
            request.getSession().setAttribute("addshipservicestosystemMessage", "message.addshipservicestosystem.creationok");
        }

        return ConfigurationManager.getProperty("path.page.addshipservicestosystem");
    }


}
