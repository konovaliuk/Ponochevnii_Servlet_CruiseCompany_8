package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.study.poject.cruise.util.StringStorage.*;

public class SingIn implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "signinMessage";

        if (request.getParameter(SIGN_IN_FORM) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User user = new UserService().findUserByLoginPassword(login, password);

        if (user.getId() != -1) {
            request.getSession().setAttribute(USER_IN_SESSION, user);
            request.getSession().setAttribute(ALL_CRUISES, new CruiseService().viewAllCruises());
            return ConfigurationManager.getProperty("path.page.startpage");
        }
        request.getSession().setAttribute(MESSAGE, "message.loginerror");
        return ConfigurationManager.getProperty("path.page.signin");
    }
}
