package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.ShipService;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateShip implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        UserService userService = new UserService();

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        HttpSession session = request.getSession();

        if (request.getParameter("createshipForm") == null) {
            return ConfigurationManager.getProperty("path.page.createship");
        }

        ShipService shipService = new ShipService();
        try {
            String shipName = request.getParameter("shipName");
            Long nStaff = Long.parseLong(request.getParameter("nStaff"));
            Long nFirstClass = Long.parseLong(request.getParameter("nFirstClass"));
            Long nSecondClass = Long.parseLong(request.getParameter("nSecondClass"));
            Long nThirdClass = Long.parseLong(request.getParameter("nThirdClass"));
            Long nFourthClass = Long.parseLong(request.getParameter("nFourthClass"));

            int result = shipService.createShip(shipName, nStaff, nFirstClass, nSecondClass, nThirdClass, nFourthClass);
            if (result <= 0) {
                request.getSession().setAttribute("createshipMessage", "message.createship.errfailed");
            } else request.getSession().setAttribute("createshipMessage", "message.createship.ok");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("createshipMessage", "message.createship.errfailed");
        }
        return ConfigurationManager.getProperty("path.page.createship");
    }
}
