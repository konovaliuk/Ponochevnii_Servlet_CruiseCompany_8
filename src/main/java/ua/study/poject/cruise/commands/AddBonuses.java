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
import static ua.study.poject.cruise.util.StringStorage.*;


public class AddBonuses implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "addBonusesMessage";
        final String CRUISE_LIST = "cruiseList";
        final String TICKETCLASS_LIST = "ticketclassList";
        final String ATTR_SCRUISE = "scruise";
        final String SHIPSERVICE_LIST = "shipserviceList";
        final String TICKETCLASS = "sticketclass";
        final String ALL_BONUSES_FOR_TICKETCLASS_1 = "allBonusesForTicketClass1";
        final String ALL_BONUSES_FOR_TICKETCLASS_2 = "allBonusesForTicketClass2";
        final String ALL_BONUSES_FOR_TICKETCLASS_3 = "allBonusesForTicketClass3";
        final String ALL_BONUSES_FOR_TICKETCLASS_4 = "allBonusesForTicketClass4";


        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }
//        request.getSession().removeAttribute("addBonusesMessage");

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
        User user = (User) request.getSession().getAttribute(USER_IN_SESSION);
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
        request.getSession().setAttribute(CRUISE_LIST, cruiseList);

        // 3. Получить список всех Ticketclass и отправить на JSP
        List<Ticketclass> ticketclassList = managerAndBonuseService.getAllTicketclass();
        request.getSession().setAttribute(TICKETCLASS_LIST, ticketclassList);


        if (request.getParameter(ADD_BONUSES_FORM) == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }


        // 4. Менеджер выбирает конкретный круиз
        String selectedCruiseStr = request.getParameter(SELECTED_CRUISE);
        if (selectedCruiseStr == null || selectedCruiseStr.equals("")) {
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.errselectcruise");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }

        Long selectedCruiseId;
        PrintableCruise selectedPrintableCruise = null;
        try {
            selectedCruiseId = Long.parseLong(selectedCruiseStr);

            for (PrintableCruise tempCruise : cruiseList) {
                if (tempCruise.getCruiseId().equals(selectedCruiseId)) {
                    request.getSession().setAttribute(ATTR_SCRUISE, tempCruise);
                    selectedPrintableCruise = tempCruise;
                    break;
                }
            }
            if (selectedPrintableCruise == null) {
                request.getSession().setAttribute(MESSAGE, "message.managebonuses.youneedselectcruise");
                return ConfigurationManager.getProperty("path.page.managebonuses");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.errselectcruise");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }

        // 5. Загрузить список всех сервисов на этом корабле и отправить на JSP
        request.getSession().setAttribute(SHIPSERVICE_LIST, shipService.getAllServicesByShipId(selectedPrintableCruise.getShipId())); // PrintableServiceOnShip


        // 6. Получить списки бонусов для каждого Ticketclass и отправить на JSP
        List<PrintableTicketclassBonus> printableTicketclassBonusListFirstClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FIRST);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_1, printableTicketclassBonusListFirstClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListSecondClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_SECOND);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_2, printableTicketclassBonusListSecondClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListThirdClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_THIRD);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_3, printableTicketclassBonusListThirdClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListFourthClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FOURTH);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_4, printableTicketclassBonusListFourthClass);

        // 7. Принять из JSP новый бонус и записать в БД
        // selectedCruiseStr

        String selectedTicketclassStr = request.getParameter(SELECTED_TICKETCLASS);
        String selectedshipserviceidStr = request.getParameter(SELECTED_SHIPSERVICE_ID);
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
                request.getSession().setAttribute(TICKETCLASS, temp);
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
                request.getSession().setAttribute(MESSAGE, "message.managebonuses.thereisbonus");
                return ConfigurationManager.getProperty("path.page.managebonuses");
            }
        }
        int result = managerAndBonuseService.addBonus(ticketclassObj.getId(), selectedshipserviceid, selectedCruiseId);
        if (result < 1) {
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.cannotsave");
        } else {
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.added");
        }

        // обновляем список всех бонусов на этом корабле
        printableTicketclassBonusListFirstClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FIRST);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_1, printableTicketclassBonusListFirstClass);

        printableTicketclassBonusListSecondClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_SECOND);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_2, printableTicketclassBonusListSecondClass);

        printableTicketclassBonusListThirdClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_THIRD);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_3, printableTicketclassBonusListThirdClass);

        printableTicketclassBonusListFourthClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FOURTH);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_4, printableTicketclassBonusListFourthClass);

        return ConfigurationManager.getProperty("path.page.managebonuses");

    }
}
