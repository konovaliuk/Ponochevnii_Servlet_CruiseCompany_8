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
import static ua.study.poject.cruise.util.StringStorage.EXCURSION_ID_TO_CART;

public class DeleteFromCart implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String CRUISES_IN_CART = "cruisesInCart";
        final String TICKET_CLASSES_IN_CART = "ticketclassesInCart";
        final String EXCURSIONS_IN_CART = "excurisionsInCart";
        final String MESSAGE = "viewcartMessage";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
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

        if (request.getParameter(DELETE_EXCURSION_FROM_CART_FORM) != null) { // удаляем экскурсию
            Long excursionId;
            try{
                excursionId = Long.parseLong(request.getParameter(DELETE_EXCURSION_FROM_CART_FORM));
            } catch (NumberFormatException e){
                request.getSession().setAttribute(MESSAGE, "message.deletefromcart.errdelexcursion");
                return ConfigurationManager.getProperty("path.page.viewcart");
            }
            for (int i = 0; i < excurisionsInCart.size(); i++) {
                if(excurisionsInCart.get(i).getId().equals(excursionId)){
                    excurisionsInCart.remove(i);
                    request.getSession().setAttribute(MESSAGE, "message.deletefromcart.delexcurok");
                    return ConfigurationManager.getProperty("path.page.viewcart");
                }
            }
            request.getSession().setAttribute(MESSAGE, "message.deletefromcart.excurnotfound");
            return ConfigurationManager.getProperty("path.page.viewcart");
        }

        if (request.getParameter(DELETE_CRUISE_FROM_CART_FORM) != null) { // удаляем круиз
            Long cruiseId;
            try{
                cruiseId = Long.parseLong(request.getParameter(DELETE_CRUISE_FROM_CART_FORM));
            } catch (NumberFormatException e){
                request.getSession().setAttribute(MESSAGE, "message.deletefromcart.errdelcruise");
                return ConfigurationManager.getProperty("path.page.viewcart");
            }
            for (int i = 0; i < cruisesInCart.size(); i++) {
                if(cruisesInCart.get(i).getCruiseId().equals(cruiseId)){
                    cruisesInCart.remove(i);
                    ticketclassesInCart.remove(i);
                    request.getSession().setAttribute(MESSAGE, "message.deletefromcart.delcruise");
                    return ConfigurationManager.getProperty("path.page.viewcart");
                }
            }
            request.getSession().setAttribute(MESSAGE, "message.deletefromcart.cruisenotfound");
            return ConfigurationManager.getProperty("path.page.viewcart");
        }

        return ConfigurationManager.getProperty("path.page.viewcart");
    }
}
