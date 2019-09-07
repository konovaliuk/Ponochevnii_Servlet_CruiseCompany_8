package ua.study.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewExcursionsInPort implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/viewexcursioninports.jsp";
    }
}
