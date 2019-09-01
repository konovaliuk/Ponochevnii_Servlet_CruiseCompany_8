package ua.epam.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;

public class Pay implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/pay.jsp";
    }
}
