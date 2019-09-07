package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ShipService;
import ua.study.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class DeleteShipServicesFromShip implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();
        ShipserviceService shipserviceService = new ShipserviceService();

        List<Ship> listShip = shipService.getAllShips();
        request.getSession().setAttribute("allShips", listShip); // отправляем в jsp список всех кораблей

        String selectedShip = request.getParameter("selectedship");
        if (selectedShip == null || selectedShip.equals("")) {
            request.getSession().setAttribute("errorMessage", "Сначала необходимо выбрать корабль");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        Long selectedShipId;
        try {
            selectedShipId = Long.parseLong(selectedShip);
            for (Ship ship : listShip) {
                if (ship.getId().equals(selectedShipId)) {
                    request.getSession().setAttribute("sship", ship);
                    break;
                }
            }
            request.getSession().setAttribute("selectshipservicesForm", "selectshipservicesForm");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Не удалось найти такой корабль в системе");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        List<Long> servicesIdList = new ArrayList<>();
        int result;
        String[] shipServicesArray = request.getParameterValues("shipservice");
        if(shipServicesArray == null){
            request.getSession().setAttribute("errorMessage", "Не удалось найти такой сервис");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }
        try {
            for (String tempStr : request.getParameterValues("shipservice")) {
                servicesIdList.add(Long.parseLong(tempStr));
            }
            result = shipserviceService.deleteServicesFromShip(selectedShipId, servicesIdList);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Не удалось найти такой сервис");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }
        if (result == 0) {
            request.getSession().setAttribute("errorMessage", "Не удалось удалить сервисы");
        } else
            request.getSession().setAttribute("errorMessage", "Сервисы удалены");

        request.getSession().setAttribute("allServicesOnSelectedShip", shipService.getAllServicesByShipId(selectedShipId)); // отправляем в jsp список всех сервисов, уже добавленых к выбранному кораблю
        request.getSession().setAttribute("allServicesInSystem", shipserviceService.getAllServisesInSystem()); // отправляем в jsp список всех сервисов в системе

        return ConfigurationManager.getProperty("path.page.addshipservicetoship");
    }
}
