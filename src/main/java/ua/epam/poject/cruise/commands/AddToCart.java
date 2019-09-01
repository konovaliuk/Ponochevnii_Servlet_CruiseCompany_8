package ua.epam.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;

public class AddToCart implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/addtocart.jsp";  // скорее всего на страницу переходить не будем, а будет просто действие
    }
}
