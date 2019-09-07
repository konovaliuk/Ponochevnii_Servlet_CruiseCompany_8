package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Shipservice;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManageBonuses implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Shipservice shipService = new Shipservice();

        if (request.getSession(false) == null) {
            return ConfigurationManager.getProperty("path.page.signin");
        }

        HttpSession session = request.getSession();

//        if (request.getParameter("managebonusesForm") == null) {
//            request.getSession().setAttribute("allCruises", new CruiseService().viewAllBonusesInEachCruise((User)session.getAttribute(StringConstantsStorage.userKeyInSession)));
//            return "/WEB-INF/jsp/managebonuses.jsp";
//        }

        User currentUser = (User) session.getAttribute(StringConstantsStorage.userKeyInSession);
        if (currentUser == null) {
            request.getSession().invalidate();
            return ConfigurationManager.getProperty("path.page.signin");
        }

        // вывести список всех бонусов и напротив их multiple select чтобы можно было выбрать, каким классам билетов сделать их бесплатно



//        String password = request.getParameter(PASSWORD);
//        String firstName = request.getParameter(FIRST_NAME);
//        String secondName = request.getParameter(SECOND_NAME);
//        String email = request.getParameter(EMAIL);
//        String tel = request.getParameter(TEL);
//
//        if(password == null || firstName == null || secondName == null || email == null || tel == null){
//            request.getSession().setAttribute("errorMessage", "Необходимо заполнить все поля");
//            return "/WEB-INF/jsp/editaccount.jsp";
//        }
//
//        if(password.equals("") || firstName.equals("") || secondName.equals("") || email.equals("") || tel.equals("")){
//            request.getSession().setAttribute("errorMessage", "Необходимо заполнить все поля");
//            return "/WEB-INF/jsp/editaccount.jsp";
//        }
//
//        User updatedUser = shipService.fillFieldsUser(currentUser.getId(), currentUser.getLogin(), password, firstName, secondName, email, tel, currentUser.getRole());
//        int i = shipService.editAccount(currentUser, updatedUser);
//        if(i < 1) {
//            request.getSession().setAttribute("errorMessage", "Данные не обновлены");
//            return "/WEB-INF/jsp/editaccount.jsp";
//        } else{
//            request.getSession().setAttribute("errorMessage", "Данные успешно обновлены");
//            request.getSession().setAttribute(StringConstantsStorage.userKeyInSession, updatedUser);
//        }
//        return "/WEB-INF/jsp/editaccount.jsp";
//
//    }
        return "/";
    }
}
