package ua.epam.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;

public class ErrorPage implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/error/error.jsp";
    }
}
