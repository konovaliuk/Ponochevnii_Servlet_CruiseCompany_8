package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.Role;
import ua.epam.poject.cruise.entity.User;
import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserRole implements Action {
    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        UserService userService = new UserService();
        request.setAttribute("allRoles", userService.findAllRoles());
        if (request.getParameter("changeuserroleForm") == null) {
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }


        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User oldUser = userService.findUserByLoginPassword(login, password);
        if (oldUser.getId() < 1) {
            request.setAttribute("errorMessage", "Пользователь с таким логином и паролем не найден");
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }
        Role newRole = userService.findRoleByRoleName(request.getParameter("selectedrole"));

        if (newRole.getId() < 1) {
            request.setAttribute("errorMessage", "Такая роль не найдена, попробуйте еще раз");
            return ConfigurationManager.getProperty("path.page.changeuserrole");
        }
        User userInSession = (User) request.getSession().getAttribute(StringConstantsStorage.userKeyInSession);
        if (!userInSession.getRole().getRole().equals(Role.ROLE_ADMIN)) {
            request.setAttribute("errorMessage", "Только администратор может менять роли пользователя");
            return ConfigurationManager.getProperty("path.page.startpage");
        }
        int result = userService.changeUserRole(oldUser, userInSession, newRole);
        if (result <= 0) {
            request.setAttribute("errorMessage", "Не удалось изменить роль пользователя, проверьте правильность заполнения полей");
        } else {
            request.setAttribute("errorMessage", "Роль успешно изменена");
            if (userInSession.getId().equals(oldUser.getId())) {
                oldUser.setRole(newRole);
                request.getSession().setAttribute(StringConstantsStorage.userKeyInSession, oldUser);
            }
        }
        return ConfigurationManager.getProperty("path.page.changeuserrole");
    }
}
