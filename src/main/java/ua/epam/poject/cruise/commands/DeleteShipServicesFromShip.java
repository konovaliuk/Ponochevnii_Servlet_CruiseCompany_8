package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.Ship;
import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.ShipService;
import ua.epam.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class DeleteShipServicesFromShip implements Action {
    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();
        ShipserviceService shipserviceService = new ShipserviceService();

        List<Ship> listShip = shipService.getAllShips();
        request.setAttribute("allShips", listShip); // отправляем в jsp список всех кораблей

        String selectedShip = request.getParameter("selectedship");
        if (selectedShip == null || selectedShip.equals("")) {
            request.setAttribute("errorMessage", "Сначала необходимо выбрать корабль");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        Long selectedShipId;
        try {
            selectedShipId = Long.parseLong(selectedShip);
            for (Ship ship : listShip) {
                if (ship.getId().equals(selectedShipId)) {
                    request.setAttribute("sship", ship);
                    break;
                }
            }
            request.setAttribute("selectshipservicesForm", "selectshipservicesForm");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Не удалось найти такой корабль в системе");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        List<Long> servicesIdList = new ArrayList<>();
        int result;
        String[] shipServicesArray = request.getParameterValues("shipservice");
        if(shipServicesArray == null){
            request.setAttribute("errorMessage", "Не удалось найти такой сервис");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }
        try {
            for (String tempStr : request.getParameterValues("shipservice")) {
                servicesIdList.add(Long.parseLong(tempStr));
            }
            result = shipserviceService.deleteServicesFromShip(selectedShipId, servicesIdList);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Не удалось найти такой сервис");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }
        if (result == 0) {
            request.setAttribute("errorMessage", "Не удалось удалить сервисы");
        } else
            request.setAttribute("errorMessage", "Сервисы удалены");

        request.setAttribute("allServicesOnSelectedShip", shipService.getAllServicesByShipId(selectedShipId)); // отправляем в jsp список всех сервисов, уже добавленых к выбранному кораблю
        request.setAttribute("allServicesInSystem", shipserviceService.getAllServisesInSystem()); // отправляем в jsp список всех сервисов в системе

        return ConfigurationManager.getProperty("path.page.addshipservicetoship");
    }
}
