package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.resource.MessageManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingIn implements Action {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;

        if (request.getParameter("signinForm") == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        UserService userService = new UserService();
        User user = userService.findUserByLoginPassword(login, password);

        if (user.getId() != -1) {
            request.getSession().setAttribute(StringConstantsStorage.userKeyInSession, user);
            request.getSession().setAttribute("allCruises", new CruiseService().viewAllCruises());
            page = ConfigurationManager.getProperty("path.page.startpage");
        } else {
            request.getSession().setAttribute("signinMessage", "message.loginerror");
            page = ConfigurationManager.getProperty("path.page.signin");
        }
        return page;
    }
}
