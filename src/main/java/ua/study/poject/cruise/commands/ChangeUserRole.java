package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserRole implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        UserService userService = new UserService();
        request.getSession().setAttribute("allRoles", userService.findAllRoles());
        if (request.getParameter("changeuserroleForm") == null) {
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }


        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User oldUser = userService.findUserByLoginPassword(login, password);
        if (oldUser.getId() < 1) {
            request.getSession().setAttribute("changeuserroleMessage", "message.changeuserrole.errnouser");
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }
        Role newRole = userService.findRoleByRoleName(request.getParameter("selectedrole"));

        if (newRole.getId() < 1) {
            request.getSession().setAttribute("changeuserroleMessage", "message.changeuserrole.errnorole");
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }
        User userInSession = (User) request.getSession().getAttribute(StringConstantsStorage.userKeyInSession);
        if (!userInSession.getRole().getRole().equals(Role.ROLE_ADMIN)) {
            request.getSession().setAttribute("changeuserroleMessage", "message.changeuserrole.errnotadmin");
            return ConfigurationManager.getProperty("path.page.startpage");
        }
        int result = userService.changeUserRole(oldUser, userInSession, newRole);
        if (result <= 0) {
            request.getSession().setAttribute("changeuserroleMessage", "message.changeuserrole.errfaild");
        } else {
            request.getSession().setAttribute("changeuserroleMessage", "message.changeuserrole.ok");
            if (userInSession.getId().equals(oldUser.getId())) {
                oldUser.setRole(newRole);
                request.getSession().setAttribute(StringConstantsStorage.userKeyInSession, oldUser);
            }
        }
        return ConfigurationManager.getProperty("path.page.changeuserrole");
    }
}
