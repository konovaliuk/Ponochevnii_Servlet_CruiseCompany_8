package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ShipService;
import ua.study.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.study.poject.cruise.util.StringStorage.*;

public class AddShipServicesToShip implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "addShipServiceToShipMessage";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();
        ShipserviceService shipserviceService = new ShipserviceService();

        List<Ship> listShip = shipService.getAllShips();
        request.getSession().setAttribute("allShips", listShip); // отправляем в jsp список всех кораблей

        if (request.getParameter(SELECT_SHIP_SERVICES_FORM) == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        String selectedShip = request.getParameter(SELECTED_SHIP);
        if (selectedShip == null || selectedShip.equals("")) {
            request.getSession().setAttribute(MESSAGE,
                    "message.addshipservicetoship.error");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        Long selectedShipId;
        Integer payable;
        try {
            selectedShipId = Long.parseLong(selectedShip);
            payable = Integer.parseInt(request.getParameter("payable"));
            for (Ship ship : listShip) {
                if (ship.getId().equals(selectedShipId)) {
                    request.getSession().setAttribute("sship", ship);
                    break;
                }
            }

        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE,
                    "message.addshipservicetoship.errorshipnotfound");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        request.getSession().setAttribute("allServicesInSystem", shipserviceService.getAllServisesInSystem()); // отправляем в jsp список всех сервисов в системе
        request.getSession().setAttribute("allServicesOnSelectedShip", shipService.getAllServicesByShipId(selectedShipId)); // отправляем в jsp список всех сервисов, уже добавленых к выбранному кораблю

        String selectedserviceIdStr = request.getParameter(SELECTED_SERVICE);
        if (selectedserviceIdStr.equals(""))
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");

        Long selectedserviceLong;
        try {
            selectedserviceLong = Long.parseLong(selectedserviceIdStr);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE,
                    "message.addshipservicetoship.errorservicenotfound");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        Service service = shipserviceService.getServeceById(selectedserviceLong);

        if (service.getId() == -1) {
            request.getSession().setAttribute(MESSAGE,
                    "message.addshipservicetoship.errorservicenotfound");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        if (shipService.isServicePresentOnThisShip(selectedShipId, service.getId())) {
            request.getSession().setAttribute(MESSAGE,
                    "message.addshipservicetoship.errorserviceduplicate");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        int result = shipService.addNewServiceToShip(selectedShipId, payable, service.getId());

        if (result <= 0) {
            request.getSession().setAttribute(MESSAGE,
                    "message.addshipservicetoship.errorservicefaild");
        } else {
            request.getSession().setAttribute("allServices", shipserviceService.getAllServisesInSystem());
            request.getSession().setAttribute(MESSAGE,
                    "message.addshipservicetoship.serviceadded");
            request.getSession().setAttribute("allServicesOnSelectedShip", shipService.getAllServicesByShipId(selectedShipId));
        }

        return ConfigurationManager.getProperty("path.page.addshipservicetoship");
    }
}
