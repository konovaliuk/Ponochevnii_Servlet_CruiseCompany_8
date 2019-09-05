package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.User;
import ua.epam.poject.cruise.resource.ConfigurationManager;
import ua.epam.poject.cruise.service.UserService;

import javax.servlet.http.HttpServletRequest;
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
    public String execute(HttpServletRequest request) {

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
            request.setAttribute("errorMessage", "Пароли в 1 и 2 поле не совпадают");
            return ConfigurationManager.getProperty("path.page.editaccount");
        }
        if (password.equals("") || firstName.equals("") || secondName.equals("") || email.equals("") || tel.equals("")) {
            request.setAttribute("errorMessage", "Необходимо заполнить все поля");
            return ConfigurationManager.getProperty("path.page.editaccount");
        }

        User updatedUser = userService.fillFieldsUser(currentUser.getId(), currentUser.getLogin(), password, firstName, secondName, email, tel, currentUser.getRole());
        int i = userService.editAccount(currentUser, updatedUser);
        if (i < 1) {
            request.setAttribute("errorMessage", "Данные не обновлены");
            // TODO подумать, как выводить конкретную ошибку
            return ConfigurationManager.getProperty("path.page.editaccount");
        } else {
            request.setAttribute("errorMessage", "Данные успешно обновлены");
            request.getSession().setAttribute(StringConstantsStorage.userKeyInSession, updatedUser);
        }
        return ConfigurationManager.getProperty("path.page.editaccount");

    }
}
