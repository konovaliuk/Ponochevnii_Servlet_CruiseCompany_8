package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excurision;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.study.poject.cruise.util.StringStorage.*;

public class AddToCart implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String START_PAGE_MESSAGE = "startpageMessage";
        final String ALL_EXCURSIONS = "allExcursions";
        final String CRUISES_IN_CART = "cruisesInCart";
        final String TICKET_CLASSES_IN_CART = "ticketclassesInCart";
        final String EXCURSIONS_IN_CART = "excurisionsInCart";
        final String VIEW_CRUISE_MESSAGE = "viewcruiseMessage";
        final String VIEW_EXCURSION_MESSAGE = "viewexcursionMessage";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        List<PrintableCruise> allCruises;
        List<Ticketclass> allTicketclasses;
        List<Excurision> allExcursions;
        try {
            allCruises = (List<PrintableCruise>) request.getSession().getAttribute(ALL_CRUISES);
            allTicketclasses = (List<Ticketclass>) request.getSession().getAttribute(ALL_TICKETCLASSES);
            allExcursions = (List<Excurision>) request.getSession().getAttribute(ALL_EXCURSIONS);
        } catch (ClassCastException e) {
            request.getSession().setAttribute(START_PAGE_MESSAGE, "message.addtocart.errgetcruisesandtickets");
            return ConfigurationManager.getProperty("path.page.startpage");
        }

        if(request.getSession().getAttribute(CRUISES_IN_CART) == null || request.getSession().getAttribute(TICKET_CLASSES_IN_CART) == null){
            request.getSession().setAttribute(CRUISES_IN_CART, new ArrayList<PrintableCruise>());
            request.getSession().setAttribute(TICKET_CLASSES_IN_CART, new ArrayList<Ticketclass>());
        }
        if(request.getSession().getAttribute(EXCURSIONS_IN_CART) == null){
            request.getSession().setAttribute(EXCURSIONS_IN_CART, new ArrayList<Excurision>());
        }

        List<PrintableCruise> cruisesInCart = (List<PrintableCruise>)request.getSession().getAttribute(CRUISES_IN_CART);
        List<Ticketclass> ticketclassesInCart = (List<Ticketclass>)request.getSession().getAttribute(TICKET_CLASSES_IN_CART);
        List<Excurision> excurisionsInCart = (List<Excurision>)request.getSession().getAttribute(EXCURSIONS_IN_CART);


        if(request.getParameter(CRUISE_ID_TO_CART) != null){ // добавили круиз в корзину
            Long cruiseIdToCart = Long.parseLong(request.getParameter(CRUISE_ID_TO_CART));
            for (PrintableCruise currentCruise : allCruises) {
                if (currentCruise.getCruiseId().equals(cruiseIdToCart)) {
                    cruisesInCart.add(currentCruise);

                    String ticketeclass = request.getParameter(TICKETCLASS);
                    for(Ticketclass currentTicketclass : allTicketclasses){
                        if(currentTicketclass.getTicketclassName().equals(ticketeclass)){
                            ticketclassesInCart.add(currentTicketclass);
                            request.setAttribute(VIEW_CRUISE_MESSAGE, "message.addtocart.cruiseadded");
                            return ConfigurationManager.getProperty("path.page.viewcruise");
                        }
                    }
                    request.setAttribute(VIEW_CRUISE_MESSAGE, "message.addtocart.erraddcruise");
                    return ConfigurationManager.getProperty("path.page.viewcruise");
                }
            }
        } else { // добавили экскурсию
            if(request.getParameter(EXCURSION_ID_TO_CART) == null){
                request.setAttribute(VIEW_EXCURSION_MESSAGE, "message.addtocart.excurnotfound");
                return ConfigurationManager.getProperty("path.page.viewexcursion");
            }
            Long excursionIdToCart = Long.parseLong(request.getParameter(EXCURSION_ID_TO_CART));
            for(Excurision currentExcurision : allExcursions){
                if(currentExcurision.getId().equals(excursionIdToCart)){
                    excurisionsInCart.add(currentExcurision);
                    request.setAttribute(VIEW_EXCURSION_MESSAGE, "message.addtocart.excuradded");
                    return ConfigurationManager.getProperty("path.page.viewexcursion");
                }
            }
            request.setAttribute(VIEW_EXCURSION_MESSAGE, "message.addtocart.erraddexcur");
            return ConfigurationManager.getProperty("path.page.viewexcursion");
        }


        return ConfigurationManager.getProperty("path.page.startpage");
    }
}
