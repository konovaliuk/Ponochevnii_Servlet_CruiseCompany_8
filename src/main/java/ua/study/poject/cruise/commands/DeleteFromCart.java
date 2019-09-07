package ua.study.poject.cruise.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFromCart implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/deletefromcart.jsp"; // ни какой страницы не будет, а просто второй раз форвард
    }
}
