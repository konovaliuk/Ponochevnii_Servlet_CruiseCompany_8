package ua.epam.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;

public class CreateExcursion implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/error/createexcursion.jsp";


    }
}
