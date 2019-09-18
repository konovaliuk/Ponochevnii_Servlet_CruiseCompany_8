package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ManagerAndBonuseService;
import ua.study.poject.cruise.service.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.study.poject.cruise.entity.Ticketclass.*;
import static ua.study.poject.cruise.entity.Ticketclass.TICKET_CLASS_FOURTH;
import static ua.study.poject.cruise.util.StringStorage.ALL_CRUISES;
import static ua.study.poject.cruise.util.StringStorage.SELECTED_CRUISE_ID;

public class ViewCruise implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        final String MESSAGE = "viewcruiseMessage";
        final String START_PAGE_MESSAGE = "startpageMessage";
        final String CRUISE = "cruise";
        final String SHIPSERVICE_LIST = "shipserviceList";
        final String ALL_BONUSES_FOR_TICKETCLASS_1 = "allBonusesForTicketClass1";
        final String ALL_BONUSES_FOR_TICKETCLASS_2 = "allBonusesForTicketClass2";
        final String ALL_BONUSES_FOR_TICKETCLASS_3 = "allBonusesForTicketClass3";
        final String ALL_BONUSES_FOR_TICKETCLASS_4 = "allBonusesForTicketClass4";

        String selectedCruiseIdStr = request.getParameter(SELECTED_CRUISE_ID);
        Long selectedCruiseId = null;
        if (selectedCruiseIdStr != null)
            try {
                selectedCruiseId = Long.parseLong(selectedCruiseIdStr);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute(MESSAGE, "message.viewcruise.errfind");
            }

        List<PrintableCruise> allCruises;
        try {
            allCruises = (List<PrintableCruise>) request.getSession().getAttribute(ALL_CRUISES);
        } catch (ClassCastException e) {
            request.getSession().setAttribute(START_PAGE_MESSAGE, "message.viewcruise.errgetall");
            return ConfigurationManager.getProperty("path.page.startpage");
        }

        PrintableCruise currentCruise = null;
        for (PrintableCruise cruise : allCruises) {
            if (cruise.getCruiseId().equals(selectedCruiseId)) {
                request.getSession().setAttribute(CRUISE, cruise);
                currentCruise = cruise;
                break;
            }
        }
        if(currentCruise == null){
            // Не удалось найти круиз
            return ConfigurationManager.getProperty("path.page.viewcruise");
        }

        // список услуг на данном корабле
        request.getSession().setAttribute(SHIPSERVICE_LIST, new ShipService().getAllServicesByShipId(currentCruise.getShipId())); // PrintableServiceOnShip

        // списки бонусв для каждого класса билетов
        ManagerAndBonuseService managerAndBonuseService = new ManagerAndBonuseService();
        List<PrintableTicketclassBonus> printableTicketclassBonusListFirstClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FIRST);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_1, printableTicketclassBonusListFirstClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListSecondClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_SECOND);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_2, printableTicketclassBonusListSecondClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListThirdClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_THIRD);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_3, printableTicketclassBonusListThirdClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListFourthClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FOURTH);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_4, printableTicketclassBonusListFourthClass);

        return ConfigurationManager.getProperty("path.page.viewcruise");
    }
}
