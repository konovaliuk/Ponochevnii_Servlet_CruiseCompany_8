package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.ManagerAndBonuseService;
import ua.study.poject.cruise.service.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.study.poject.cruise.entity.Ticketclass.*;

public class AddBonuses implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }
        request.getSession().removeAttribute("addBonusesMessage");

        // 1. Зная user.id получить список всех Ship на которых он может управлять бонусами.
        // 2. Получить список всех (Printable) круизов, в которых учавствуют эти корабли. Отправить список в JSP
        // 3. Получить список всех Ticketclass и отправить на JSP
        // 4. Менеджер выбирает конкретный круиз
        // 5. Загрузить список всех сервисов на этом корабле и отправить на JSP
        // 6. Получить списки бонусов для каждого Ticketclass и отправить на JSP
        // 7. Принять из JSP новый бонус и записать в БД
        //    Если менеджер будет удалять бонус, надо обработать это классе DeleteBonus

        ShipService shipService = new ShipService();
        ManagerAndBonuseService managerAndBonuseService = new ManagerAndBonuseService();

        // 1. Зная user.id получить список всех Ship на которых он может управлять бонусами.
        User user = (User) request.getSession().getAttribute(StringConstantsStorage.userKeyInSession);
        if (user == null) {
            return ConfigurationManager.getProperty("path.page.startpage");
        }
        List<Ship> ships = managerAndBonuseService.getAllShipsByManagerId(user.getId());

        // 2. Получить список всех (Printable) круизов, в которых учавствуют эти корабли. Отправить список в JSP
        List<PrintableCruise> cruiseList = new ArrayList<>();
        CruiseService cruiseService = new CruiseService();
        for (Ship ship : ships) {
            cruiseList.addAll(cruiseService.getAllPrintableCruisesByShipId(ship.getId()));
        } //allServicesOnShip
        request.getSession().setAttribute("cruiseList", cruiseList);

        // 3. Получить список всех Ticketclass и отправить на JSP
        List<Ticketclass> ticketclassList = managerAndBonuseService.getAllTicketclass();
        request.getSession().setAttribute("ticketclassList", ticketclassList);


        if (request.getParameter("addbonusesForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }


        // 4. Менеджер выбирает конкретный круиз
        String selectedCruiseStr = request.getParameter("selectedcruise");
        if (selectedCruiseStr == null || selectedCruiseStr.equals("")) {
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.errselectcruise");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }

        Long selectedCruiseId;
        PrintableCruise selectedPrintableCruise = null;
        try {
            selectedCruiseId = Long.parseLong(selectedCruiseStr);

            for (PrintableCruise tempCruise : cruiseList) {
                if (tempCruise.getCruiseId().equals(selectedCruiseId)) {
                    request.getSession().setAttribute("scruise", tempCruise);
                    selectedPrintableCruise = tempCruise;
                    break;
                }
            }
            if (selectedPrintableCruise == null) {
                request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.youneedselectcruise");
                return ConfigurationManager.getProperty("path.page.managebonuses");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.errselectcruise");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }

        // 5. Загрузить список всех сервисов на этом корабле и отправить на JSP
        request.getSession().setAttribute("shipserviceList", shipService.getAllServicesByShipId(selectedPrintableCruise.getShipId())); // PrintableServiceOnShip


        // 6. Получить списки бонусов для каждого Ticketclass и отправить на JSP
        List<PrintableTicketclassBonus> printableTicketclassBonusListFirstClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FIRST);
        request.getSession().setAttribute("allBonusesForTicketClass1", printableTicketclassBonusListFirstClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListSecondClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_SECOND);
        request.getSession().setAttribute("allBonusesForTicketClass2", printableTicketclassBonusListSecondClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListThirdClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_THIRD);
        request.getSession().setAttribute("allBonusesForTicketClass3", printableTicketclassBonusListThirdClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListFourthClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FOURTH);
        request.getSession().setAttribute("allBonusesForTicketClass4", printableTicketclassBonusListFourthClass);

        // 7. Принять из JSP новый бонус и записать в БД
        // selectedCruiseStr

        String selectedTicketclassStr = request.getParameter("selectedticketclass");
        String selectedshipserviceidStr = request.getParameter("selectedshipserviceid");
        if (selectedTicketclassStr == null || selectedTicketclassStr.equals("") || selectedshipserviceidStr == null || selectedshipserviceidStr.equals("")) {
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }

        Long ticketclassId = Long.parseLong(selectedTicketclassStr);
        Long selectedshipserviceid = Long.parseLong(selectedshipserviceidStr);

        // Получим объект Ticketclass по id
        Ticketclass ticketclassObj = null;
        for (Ticketclass temp : ticketclassList) {
            if (ticketclassId.equals(temp.getId())) {
                ticketclassObj = temp;
                request.getSession().setAttribute("sticketclass", temp);
                break;
            }
        }

        // Попытаемся найти в соответсвующем списке такой бонус, если он есть - новый добавлять не будем
        List<PrintableTicketclassBonus> listForFinding = new ArrayList<>();
        switch (ticketclassObj.getTicketclassName()) {
            case TICKET_CLASS_FIRST:
                listForFinding = printableTicketclassBonusListFirstClass;
                break;
            case TICKET_CLASS_SECOND:
                listForFinding = printableTicketclassBonusListSecondClass;
                break;
            case TICKET_CLASS_THIRD:
                listForFinding = printableTicketclassBonusListThirdClass;
                break;
            case TICKET_CLASS_FOURTH:
                listForFinding = printableTicketclassBonusListFourthClass;
        }

        // ticketclassBonus: ticketclassId, shipServiceId, cruiseId
        for (PrintableTicketclassBonus printableTicketclassBonus : listForFinding) {
            if (printableTicketclassBonus.getPrintableServiceOnShip().getShipServiceId().equals(selectedshipserviceid)) {
                request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.thereisbonus");
                return ConfigurationManager.getProperty("path.page.managebonuses");
            }
        }
        int result = managerAndBonuseService.addBonus(ticketclassObj.getId(), selectedshipserviceid, selectedCruiseId);
        if (result < 1) {
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.cannotsave");
        } else {
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.added");
        }

        // обновляем список всех бонусов на этом корабле
        printableTicketclassBonusListFirstClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FIRST);
        request.getSession().setAttribute("allBonusesForTicketClass1", printableTicketclassBonusListFirstClass);

        printableTicketclassBonusListSecondClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_SECOND);
        request.getSession().setAttribute("allBonusesForTicketClass2", printableTicketclassBonusListSecondClass);

        printableTicketclassBonusListThirdClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_THIRD);
        request.getSession().setAttribute("allBonusesForTicketClass3", printableTicketclassBonusListThirdClass);

        printableTicketclassBonusListFourthClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FOURTH);
        request.getSession().setAttribute("allBonusesForTicketClass4", printableTicketclassBonusListFourthClass);

        return ConfigurationManager.getProperty("path.page.managebonuses");

    }
}
