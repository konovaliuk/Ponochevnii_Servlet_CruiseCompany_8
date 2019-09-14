package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ManagerAndBonuseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.study.poject.cruise.entity.Ticketclass.*;
import static ua.study.poject.cruise.util.StringStorage.BONUSES;

public class DeleteBonuses implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String ATTR_SCRUISE = "scruise";
        final String ALL_BONUSES_FOR_TICKETCLASS_1 = "allBonusesForTicketClass1";
        final String ALL_BONUSES_FOR_TICKETCLASS_2 = "allBonusesForTicketClass2";
        final String ALL_BONUSES_FOR_TICKETCLASS_3 = "allBonusesForTicketClass3";
        final String ALL_BONUSES_FOR_TICKETCLASS_4 = "allBonusesForTicketClass4";

        final String MESSAGE = "addBonusesMessage";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

//        request.getSession().removeAttribute(MESSAGE);

        // получаем bonuses - это List строк (один из 4) в которых баписаны ticketclassBonusId
        ManagerAndBonuseService managerAndBonuseService = new ManagerAndBonuseService();

        List<Long> ticketclassBonusIdList = new ArrayList<>();
        int result;
        String[] idStrArray = request.getParameterValues(BONUSES);
        if (idStrArray == null) {
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.errrecievelist");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }
        try {
            for (String tempStr : idStrArray) {
                ticketclassBonusIdList.add(Long.parseLong(tempStr));
            }
            result = managerAndBonuseService.deleteBonuses(ticketclassBonusIdList);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.errdel");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }
        if (result < 1) {
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.errdel");
        } else
            request.getSession().setAttribute(MESSAGE, "message.managebonuses.deleted");

        PrintableCruise selectedCruise = (PrintableCruise) request.getSession().getAttribute(ATTR_SCRUISE);
        Long selectedCruiseId = selectedCruise.getCruiseId();

        // Обновляем списки бонусов для каждого Ticketclass и отправить на JSP
        List<PrintableTicketclassBonus> printableTicketclassBonusListFirstClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FIRST);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_1, printableTicketclassBonusListFirstClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListSecondClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_SECOND);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_2, printableTicketclassBonusListSecondClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListThirdClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_THIRD);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_3, printableTicketclassBonusListThirdClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListFourthClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FOURTH);
        request.getSession().setAttribute(ALL_BONUSES_FOR_TICKETCLASS_4, printableTicketclassBonusListFourthClass);

        return ConfigurationManager.getProperty("path.page.managebonuses");
    }
}
