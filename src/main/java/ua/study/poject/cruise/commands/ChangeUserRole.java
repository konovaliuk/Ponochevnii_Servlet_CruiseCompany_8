package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.study.poject.cruise.util.StringStorage.*;

public class ChangeUserRole implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "changeuserroleMessage";

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        UserService userService = new UserService();
        request.getSession().setAttribute("allRoles", userService.findAllRoles());
        if (request.getParameter(CHANGE_USER_ROLE_FORM) == null) {
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }


        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User oldUser = userService.findUserByLoginPassword(login, password);
        if (oldUser.getId() < 1) {
            request.getSession().setAttribute(MESSAGE, "message.changeuserrole.errnouser");
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }
        Role newRole = userService.findRoleByRoleName(request.getParameter(SELECTED_ROLE));

        if (newRole.getId() < 1) {
            request.getSession().setAttribute(MESSAGE, "message.changeuserrole.errnorole");
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }
        User userInSession = (User) request.getSession().getAttribute(USER_IN_SESSION);
        if (!userInSession.getRole().getRole().equals(Role.ROLE_ADMIN)) {
            request.getSession().setAttribute(MESSAGE, "message.changeuserrole.errnotadmin");
            return ConfigurationManager.getProperty("path.page.startpage");
        }
        int result = userService.changeUserRole(oldUser, userInSession, newRole);
        if (result <= 0) {
            request.getSession().setAttribute(MESSAGE, "message.changeuserrole.errfaild");
        } else {
            request.getSession().setAttribute(MESSAGE, "message.changeuserrole.ok");
            if (userInSession.getId().equals(oldUser.getId())) {
                oldUser.setRole(newRole);
                request.getSession().setAttribute(USER_IN_SESSION, oldUser);
            }
        }
        return ConfigurationManager.getProperty("path.page.changeuserrole");
    }
}
