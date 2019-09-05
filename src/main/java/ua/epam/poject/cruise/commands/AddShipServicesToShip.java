package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.Service;
import ua.epam.poject.cruise.entity.Ship;
import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.ShipService;
import ua.epam.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddShipServicesToShip implements Action {
    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();
        ShipserviceService shipserviceService = new ShipserviceService();

        List<Ship> listShip = shipService.getAllShips();
        request.setAttribute("allShips", listShip); // отправляем в jsp список всех кораблей

        if (request.getParameter("selectshipservicesForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        String selectedShip = request.getParameter("selectedship");
        if (selectedShip == null || selectedShip.equals("")) {
            request.setAttribute("errorMessage", "Сначала необходимо выбрать корабль");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        Long selectedShipId;
        Integer payable;
        try {
            selectedShipId = Long.parseLong(selectedShip);
            payable = Integer.parseInt(request.getParameter("payable"));
            for (Ship ship : listShip) {
                if(ship.getId().equals(selectedShipId)) {
                    request.setAttribute("sship", ship);
                    break;
                }
            }

        } catch (NumberFormatException e) {
        request.setAttribute("errorMessage", "Не удалось найти такой корабль в системе");
        return ConfigurationManager.getProperty("path.page.addshipservicetoship");
    }

        request.setAttribute("allServicesInSystem", shipserviceService.getAllServisesInSystem()); // отправляем в jsp список всех сервисов в системе
        request.setAttribute("allServicesOnSelectedShip", shipService.getAllServicesByShipId(selectedShipId)); // отправляем в jsp список всех сервисов, уже добавленых к выбранному кораблю

        String selectedserviceIdStr = request.getParameter("selectedservice");
        if(selectedserviceIdStr.equals(""))
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");

        Long selectedserviceLong;
        try{
            selectedserviceLong = Long.parseLong(selectedserviceIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Не удалось найти такой сервис в системе");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        Service service = shipserviceService.getServeceById(selectedserviceLong);

        if (service.getId() == -1) {
            request.setAttribute("errorMessage", "Такого сервиса в системе нет");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        if(shipService.isServicePresentOnThisShip(selectedShipId, service.getId())){
            request.setAttribute("errorMessage", "Такой сервис уже есть на корабле, выберите другой");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }

        int result = shipService.addNewServiceToShip(selectedShipId, payable,service.getId());

        if (result <= 0) {
            request.setAttribute("errorMessage", "Не удалось добавить сервис, проверьте правильность заполнения полей");
        } else {
            request.setAttribute("allServices", shipserviceService.getAllServisesInSystem());
            request.setAttribute("errorMessage", "Сервис успешно добавлен");
            request.setAttribute("allServicesOnSelectedShip", shipService.getAllServicesByShipId(selectedShipId));
        }

        return ConfigurationManager.getProperty("path.page.addshipservicetoship");
    }
}
