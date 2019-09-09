package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.ManagerAndBonuseService;
import ua.study.poject.cruise.service.ShipService;
import ua.study.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddBonuses implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        // 1. Зная user.id получить список всех Ship на которых он может управлять бонусами.
        // 2. Получить список всех (Printable) круизов, в которых учавствуют эти корабли. Отправить список в JSP
        // 3. Получить список всех Ticketclass и отправить на JSP
        // 4. Менеджер выбирает конкретный круиз
        // 5. Загрузить список всех сервисов на этом корабле и отправить на JSP
        // 6. Получить списки бонусов для каждого Ticketclass и отправить на JSP
        // 7. Принять из JSP новый бонус и записать в БД
        //    Если менеджер будет удалять бонус, надо обработать это классе DeleteBonus

//        ShipserviceService shipServiceService = new ShipserviceService();
//        request.getSession().setAttribute("allServices", shipServiceService.getAllServisesInSystem());

//        request.getSession().setAttribute("allShipServicesInSystem", shipServiceService.getAllServisesInSystem());

        if (request.getParameter("addbonusesForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }

        ShipService shipService = new ShipService();

        // 1. Зная user.id получить список всех Ship на которых он может управлять бонусами.
        ManagerAndBonuseService managerAndBonuseService = new ManagerAndBonuseService();
        User user = (User) request.getSession().getAttribute(StringConstantsStorage.userKeyInSession);
        if (user == null) { // message Неудача
        }
        List<Ship> ships = managerAndBonuseService.getAllShipsByManagerId(user.getId());

        // 2. Получить список всех (Printable) круизов, в которых учавствуют эти корабли. Отправить список в JSP
        List<PrintableCruise> cruiseList = new ArrayList<>();
        CruiseService cruiseService = new CruiseService();
        for (Ship ship : ships) {
            cruiseList.addAll(cruiseService.getAllPrintableCruisesByShipId(ship.getId()));
        }
        request.getSession().setAttribute("cruiseList", cruiseList);

        // 3. Получить список всех Ticketclass и отправить на JSP
        List<Ticketclass> ticketclassList = managerAndBonuseService.getAllTicketclass();

        // 4. Менеджер выбирает конкретный круиз
        String selectedCruiseStr = request.getParameter("selectedcruise");
        if (selectedCruiseStr == null || selectedCruiseStr.equals("")) {
            request.getSession().setAttribute("addBonusesMessage",
                    "message.addbonuses.errselectcruise");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }

        Long selectedCruiseId;
        Long selectedTicketClass;
        PrintableCruise selectedPrintableCruise = null;
        try {
            selectedCruiseId = Long.parseLong(selectedCruiseStr);
            selectedTicketClass = Long.parseLong(request.getParameter("selectedTicketClass"));
            for (PrintableCruise tempCruise : cruiseList) {
                if (tempCruise.getCruiseId().equals(selectedCruiseId)) {
                    request.getSession().setAttribute("scruise", tempCruise);
                    selectedPrintableCruise = tempCruise;
                    break;
                }
            }
            if(selectedPrintableCruise == null){
                request.getSession().setAttribute("addBonusesMessage", "message.addbonuses.errselectcruise");
                return ConfigurationManager.getProperty("path.page.addshipservicetoship");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("addBonusesMessage", "message.addbonuses.errselectcruise");
            return ConfigurationManager.getProperty("path.page.addshipservicetoship");
        }



        // 5. Загрузить список всех сервисов на этом корабле и отправить на JSP
        request.getSession().setAttribute("allServicesOnSelectedShip", shipService.getAllServicesByShipId(selectedPrintableCruise.getShipId()));


        // 6. Получить списки бонусов для каждого Ticketclass и отправить на JSP
        for (int i = 0; i < ticketclassList.size(); i++) {
            managerAndBonuseService.getAllBonusesByCruiseIdTicketclassId(selectedCruiseId, ticketclassList.get(i).getId());
        }


        return ConfigurationManager.getProperty("path.page.managebonuses");
    }
}
