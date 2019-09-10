package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ManagerAndBonuseService;
import ua.study.poject.cruise.service.ShipService;
import ua.study.poject.cruise.service.ShipserviceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.study.poject.cruise.entity.Ticketclass.*;
import static ua.study.poject.cruise.entity.Ticketclass.TICKET_CLASS_FOURTH;

public class DeleteBonuses implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        request.getSession().removeAttribute("addBonusesMessage");

        // получаем bonuses - это List строк (один из 4) в которых баписаны ticketclassBonusId
        ManagerAndBonuseService managerAndBonuseService = new ManagerAndBonuseService();

        List<Long> ticketclassBonusIdList = new ArrayList<>();
        int result;
        String[] idStrArray = request.getParameterValues("bonuses");
        if(idStrArray == null){
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.errrecievelist");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }
        try {
            for (String tempStr : idStrArray) {
                ticketclassBonusIdList.add(Long.parseLong(tempStr));
            }
            result = managerAndBonuseService.deleteBonuses(ticketclassBonusIdList);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.errdel");
            return ConfigurationManager.getProperty("path.page.managebonuses");
        }
        if (result < 1) {
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.errdel");
        } else
            request.getSession().setAttribute("addBonusesMessage", "message.managebonuses.deleted");

        PrintableCruise selectedCruise = (PrintableCruise)request.getSession().getAttribute("scruise");
        Long selectedCruiseId = selectedCruise.getCruiseId();

        // Обновляем списки бонусов для каждого Ticketclass и отправить на JSP
        List<PrintableTicketclassBonus> printableTicketclassBonusListFirstClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FIRST);
        request.getSession().setAttribute("allBonusesForTicketClass1", printableTicketclassBonusListFirstClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListSecondClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_SECOND);
        request.getSession().setAttribute("allBonusesForTicketClass2", printableTicketclassBonusListSecondClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListThirdClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_THIRD);
        request.getSession().setAttribute("allBonusesForTicketClass3", printableTicketclassBonusListThirdClass);

        List<PrintableTicketclassBonus> printableTicketclassBonusListFourthClass = managerAndBonuseService.getAllBonusesByCruiseIdTicketclassName(selectedCruiseId, TICKET_CLASS_FOURTH);
        request.getSession().setAttribute("allBonusesForTicketClass4", printableTicketclassBonusListFourthClass);

        return ConfigurationManager.getProperty("path.page.managebonuses");
    }
}
