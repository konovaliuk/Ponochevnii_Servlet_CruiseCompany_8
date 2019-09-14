package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ShipService;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.study.poject.cruise.util.StringStorage.*;

public class CreateShip implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "createshipMessage";

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        if (request.getParameter(CREATE_SHIP_FORM) == null) {
            return ConfigurationManager.getProperty("path.page.createship");
        }

        ShipService shipService = new ShipService();
        try {
            String shipName = request.getParameter(SHIP_NAME);
            Long nStaff = Long.parseLong(request.getParameter(N_STAFF));
            Long nFirstClass = Long.parseLong(request.getParameter(N_FIRST_CLASS));
            Long nSecondClass = Long.parseLong(request.getParameter(N_SECOND_CLASS));
            Long nThirdClass = Long.parseLong(request.getParameter(N_THIRD_CLASS));
            Long nFourthClass = Long.parseLong(request.getParameter(N_FOURTH_CLASS));

            int result = shipService.createShip(shipName, nStaff, nFirstClass, nSecondClass, nThirdClass, nFourthClass);
            if (result <= 0) {
                request.getSession().setAttribute(MESSAGE, "message.createship.errfailed");
            } else request.getSession().setAttribute(MESSAGE, "message.createship.ok");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE, "message.createship.errfailed");
        }
        return ConfigurationManager.getProperty("path.page.createship");
    }
}
