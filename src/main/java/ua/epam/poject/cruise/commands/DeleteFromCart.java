package ua.epam.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;

public class DeleteFromCart implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/deletefromcart.jsp"; // ни какой страницы не будет, а просто второй раз форвард
    }
}
