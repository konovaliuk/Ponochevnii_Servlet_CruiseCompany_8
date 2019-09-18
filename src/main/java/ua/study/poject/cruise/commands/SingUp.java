package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.study.poject.cruise.util.StringStorage.*;

public class SingUp implements Action {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PASSWORD2 = "password2";
    private static final String FIRST_NAME = "firstname";
    private static final String SECOND_NAME = "secondname";
    private static final String EMAIL = "email";
    private static final String TEL = "tel";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        final String MESSAGE = "registrMessage";

        if (request.getParameter(SIGN_UP_FORM) == null) {
            return ConfigurationManager.getProperty("path.page.signup");
        }

        if (request.getParameter(LOGIN).equals("") || request.getParameter(PASSWORD).equals("")) {
            request.getSession().setAttribute(MESSAGE, "message.register.erroremptyfild");
            return ConfigurationManager.getProperty("path.page.signup");
        }

        UserService userService = new UserService();
        if (!request.getParameter(PASSWORD).equals(request.getParameter(PASSWORD2))) {
            request.getSession().setAttribute(MESSAGE, "message.register.errorpass1and2");
            return ConfigurationManager.getProperty("path.page.signup");
        }

        int userId = userService.addNewUser(request.getParameter(LOGIN), request.getParameter(PASSWORD), request.getParameter(FIRST_NAME),
                request.getParameter(SECOND_NAME), request.getParameter(EMAIL), request.getParameter(TEL));
        if (userId > 0) {
            HttpSession session = request.getSession();
            User newUser = userService.findUserByLoginPassword(request.getParameter(LOGIN), request.getParameter(PASSWORD));

            if (newUser.getId() < 0) {
                request.getSession().setAttribute(MESSAGE, "message.register.errorthereisuser");
                return ConfigurationManager.getProperty("path.page.signup");
            }

            session.setAttribute(USER_IN_SESSION, newUser);
            request.getSession().setAttribute(ALL_CRUISES, new CruiseService().viewAllCruises());
            return ConfigurationManager.getProperty("path.page.startpage");
        }
        request.getSession().setAttribute(MESSAGE, "message.register.errorthereisuser");
        return ConfigurationManager.getProperty("path.page.signup");
    }
}
