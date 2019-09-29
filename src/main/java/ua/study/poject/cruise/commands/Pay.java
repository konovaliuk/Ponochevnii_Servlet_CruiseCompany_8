package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CartPayService;
import ua.study.poject.cruise.util.StringStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Pay implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String CRUISES_IN_CART = "cruisesInCart";
        final String TICKET_CLASSES_IN_CART = "ticketclassesInCart";
        final String EXCURSIONS_IN_CART = "excurisionsInCart";
        final String VIEWCART_MESSAGE = "viewcartMessage";

        if(request.getSession().getAttribute(CRUISES_IN_CART) == null || request.getSession().getAttribute(TICKET_CLASSES_IN_CART) == null){
            request.getSession().setAttribute(CRUISES_IN_CART, new ArrayList<PrintableCruise>());
            request.getSession().setAttribute(TICKET_CLASSES_IN_CART, new ArrayList<Ticketclass>());
        }
        if(request.getSession().getAttribute(EXCURSIONS_IN_CART) == null){
            request.getSession().setAttribute(EXCURSIONS_IN_CART, new ArrayList<Excursion>());
        }

        List<PrintableCruise> cruisesInCart = (List<PrintableCruise>)request.getSession().getAttribute(CRUISES_IN_CART);
        List<Ticketclass> ticketclassesInCart = (List<Ticketclass>)request.getSession().getAttribute(TICKET_CLASSES_IN_CART);
        List<Excursion> excurisionsInCart = (List<Excursion>)request.getSession().getAttribute(EXCURSIONS_IN_CART);


        User currentUser = (User)request.getSession().getAttribute(StringStorage.USER_IN_SESSION);
        int result = new CartPayService().pay(currentUser.getId(), cruisesInCart, ticketclassesInCart, excurisionsInCart);

        if(result < 1){
            request.getSession().setAttribute(VIEWCART_MESSAGE, "message.pay.errpay");
            return ConfigurationManager.getProperty("path.page.viewcart");
        }
        request.getSession().setAttribute(VIEWCART_MESSAGE, "message.pay.ok");

        request.getSession().setAttribute(CRUISES_IN_CART, new ArrayList<PrintableCruise>());
        request.getSession().setAttribute(TICKET_CLASSES_IN_CART, new ArrayList<Ticketclass>());
        request.getSession().setAttribute(EXCURSIONS_IN_CART, new ArrayList<Excursion>());

        return ConfigurationManager.getProperty("path.page.viewcart");
    }
}
