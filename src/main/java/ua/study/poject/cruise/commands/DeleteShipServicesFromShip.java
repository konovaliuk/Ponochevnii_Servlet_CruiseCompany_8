package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ShipService;
import ua.study.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.study.poject.cruise.util.StringStorage.*;

public class DeleteShipServicesFromShip implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "errorMessage";
        final String ALL_SHIPS = "allShips";
        final String SSHIPS = "sship";
        final String ALL_SERVICES_ON_SELECTED_SHIP = "allServicesOnSelectedShip";
        final String ALL_SERVICES_IN_SYSTEM = "allServicesInSystem";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();
        ShipserviceService shipserviceService = new ShipserviceService();

        List<Ship> listShip = shipService.getAllShips();
        request.getSession().setAttribute(ALL_SHIPS, listShip); // отправляем в jsp список всех кораблей

        String selectedShip = request.getParameter(SELECTED_SHIP);
        if (selectedShip == null || selectedShip.equals("")) {
            request.getSession().setAttribute(MESSAGE, "message.addshipservicetoship.advert");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        Long selectedShipId;
        try {
            selectedShipId = Long.parseLong(selectedShip);
            for (Ship ship : listShip) {
                if (ship.getId().equals(selectedShipId)) {
                    request.getSession().setAttribute(SSHIPS, ship);
                    break;
                }
            }
            request.getSession().setAttribute(SELECT_SHIP_SERVICES_FORM, SELECT_SHIP_SERVICES_FORM);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE, "message.addshipservicetoship.errorshipnotfound");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        List<Long> servicesIdList = new ArrayList<>();
        int result;
        String[] shipServicesArray = request.getParameterValues(SHIP_SERVICE);
        if(shipServicesArray == null){
            request.getSession().setAttribute(MESSAGE, "message.addshipservicetoship.errorservicenotfound");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }
        try {
            for (String tempStr : shipServicesArray) {
                servicesIdList.add(Long.parseLong(tempStr));
            }
            result = shipserviceService.deleteServicesFromShip(selectedShipId, servicesIdList);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE, "message.addshipservicetoship.errorservicenotfound");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }
        if (result == 0) {
            request.getSession().setAttribute(MESSAGE, "message.addshipservicetoship.errdelete");
        } else
            request.getSession().setAttribute(MESSAGE, "message.addshipservicetoship.delok");

        request.getSession().setAttribute(ALL_SERVICES_ON_SELECTED_SHIP, shipService.getAllServicesByShipId(selectedShipId)); // отправляем в jsp список всех сервисов, уже добавленых к выбранному кораблю
        request.getSession().setAttribute(ALL_SERVICES_IN_SYSTEM, shipserviceService.getAllServisesInSystem()); // отправляем в jsp список всех сервисов в системе

        return ConfigurationManager.getProperty("path.page.addshipservicetoship");
    }
}
