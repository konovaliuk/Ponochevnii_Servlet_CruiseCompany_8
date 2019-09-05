package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.ShipService;
import ua.epam.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateShip implements Action {
    @Override
    public String execute(HttpServletRequest request) {

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
                request.setAttribute("errorMessage", "Не удалось создать корабль, проверьте правильность заполнения полей");
            } else request.setAttribute("errorMessage", "Корабль успешно создан");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Не удалось создать корабль, проверьте правильность заполнения полей");
        }
        return ConfigurationManager.getProperty("path.page.createship");
    }
}
