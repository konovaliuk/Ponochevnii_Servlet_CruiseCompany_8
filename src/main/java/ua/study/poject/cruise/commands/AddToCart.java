package ua.study.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToCart implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/addtocart.jsp";  // скорее всего на страницу переходить не будем, а будет просто действие
    }
}
