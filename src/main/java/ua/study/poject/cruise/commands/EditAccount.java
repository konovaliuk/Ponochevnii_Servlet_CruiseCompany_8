package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditAccount implements Action {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PASSWORD2 = "password2";
    private static final String FIRST_NAME = "firstname";
    private static final String SECOND_NAME = "secondname";
    private static final String EMAIL = "email";
    private static final String TEL = "tel";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        UserService userService = new UserService();

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        HttpSession session = request.getSession();

        if (request.getParameter("editaccountForm") == null) {
            return ConfigurationManager.getProperty("path.page.editaccount");
        }

        User currentUser = (User) session.getAttribute(StringConstantsStorage.userKeyInSession);
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

        if(!password.equals(password2)){
            request.getSession().setAttribute("editaccountMessage", "message.editaccount.errorpass1and2");
            return ConfigurationManager.getProperty("path.page.editaccount");
        }
        if (password.equals("") || firstName.equals("") || secondName.equals("") || email.equals("") || tel.equals("")) {
            request.getSession().setAttribute("editaccountMessage", "message.editaccount.errfillall");
            return ConfigurationManager.getProperty("path.page.editaccount");
        }

        User updatedUser = userService.fillFieldsUser(currentUser.getId(), currentUser.getLogin(), password, firstName, secondName, email, tel, currentUser.getRole());
        int i = userService.editAccount(currentUser, updatedUser);
        if (i < 1) {
            request.getSession().setAttribute("editaccountMessage", "message.editaccount.eerupdate");
            // TODO подумать, как выводить конкретную ошибку
            return ConfigurationManager.getProperty("path.page.editaccount");
        } else {
            request.getSession().setAttribute("editaccountMessage", "message.editaccount.ok");
            request.getSession().setAttribute(StringConstantsStorage.userKeyInSession, updatedUser);
        }
        return ConfigurationManager.getProperty("path.page.editaccount");

    }
}
