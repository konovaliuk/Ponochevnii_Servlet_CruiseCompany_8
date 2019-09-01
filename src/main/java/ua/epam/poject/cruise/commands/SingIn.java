package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.User;
import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.resource.MessageManager;
import ua.epam.poject.cruise.service.CruiseService;
import ua.epam.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class SingIn implements Action {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

//        request.setAttribute("command", null);
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        UserService userService = new UserService();
        User user = userService.findUserByLoginPassword(login, password);
//        password = null;
        if (user.getId() != -1) {
            request.getSession().setAttribute(StringConstantsStorage.userKeyInSession, user);
            request.setAttribute("allCruises", new CruiseService().viewAllCruises());
            page = ConfigurationManager.getProperty("path.page.startpage");
        } else {
            String mess = MessageManager.getProperty("message.loginerror");
            request.setAttribute(StringConstantsStorage.loginErrorMessage, mess);
            page = ConfigurationManager.getProperty("path.page.signin");
        }
        return page;
    }
}
