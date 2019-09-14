package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.study.poject.cruise.util.StringStorage.*;

public class EditAccount implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "editaccountMessage";

        UserService userService = new UserService();

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        HttpSession session = request.getSession();

        if (request.getParameter(EDIT_ACCOUNT_FORM) == null) {
            return ConfigurationManager.getProperty("path.page.editaccount");
        }

        User currentUser = (User) session.getAttribute(USER_IN_SESSION);
        if (currentUser == null) {
            request.getSession().invalidate();
            return ConfigurationManager.getProperty("path.page.signin");
        }

        String password = request.getParameter(PASSWORD);
        String password2 = request.getParameter(PASSWORD2);
        String firstName = request.getParameter(FIRST_NAME);
        String secondName = request.getParameter(SECOND_NAME);
        String email = request.getParameter(EMAIL);
        String tel = request.getParameter(TEL);

        if (!password.equals(password2)) {
            request.getSession().setAttribute(MESSAGE, "message.editaccount.errorpass1and2");
            return ConfigurationManager.getProperty("path.page.editaccount");
        }
        if (password.equals("") || firstName.equals("") || secondName.equals("") || email.equals("") || tel.equals("")) {
            request.getSession().setAttribute(MESSAGE, "message.editaccount.errfillall");
            return ConfigurationManager.getProperty("path.page.editaccount");
        }

        User updatedUser = userService.fillFieldsUser(currentUser.getId(), currentUser.getLogin(), password, firstName, secondName, email, tel, currentUser.getRole());
        int i = userService.editAccount(currentUser, updatedUser);
        if (i < 1) {
            request.getSession().setAttribute(MESSAGE, "message.editaccount.eerupdate");
            // TODO подумать, как выводить конкретную ошибку
            return ConfigurationManager.getProperty("path.page.editaccount");
        } else {
            request.getSession().setAttribute(MESSAGE, "message.editaccount.ok");
            request.getSession().setAttribute(USER_IN_SESSION, updatedUser);
        }
        return ConfigurationManager.getProperty("path.page.editaccount");

    }
}
