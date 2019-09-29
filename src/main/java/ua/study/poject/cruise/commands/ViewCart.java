package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ViewCart implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String CRUISES_IN_CART = "cruisesInCart";
        final String TICKET_CLASSES_IN_CART = "ticketclassesInCart";
        final String EXCURSIONS_IN_CART = "excurisionsInCart";
        final String VIEWCART_MESSAGE = "viewcartMessage";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        if(request.getSession().getAttribute(CRUISES_IN_CART) == null || request.getSession().getAttribute(TICKET_CLASSES_IN_CART) == null){
            request.getSession().setAttribute(CRUISES_IN_CART, new ArrayList<PrintableCruise>());
            request.getSession().setAttribute(TICKET_CLASSES_IN_CART, new ArrayList<Ticketclass>());
        }
        if(request.getSession().getAttribute(EXCURSIONS_IN_CART) == null){
            request.getSession().setAttribute(EXCURSIONS_IN_CART, new ArrayList<Excursion>());
        }


        return ConfigurationManager.getProperty("path.page.viewcart");
    }
}
