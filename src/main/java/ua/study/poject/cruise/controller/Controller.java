package ua.study.poject.cruise.controller;

import ua.study.poject.cruise.commands.Action;
import ua.study.poject.cruise.commands.ActionStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {


    private static final long serialVersionUID = 188511596309056999L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest2(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ActionStorage actionStorage = ActionStorage.getInstance();
        Action action = actionStorage.getAction(request);
        String page = action.execute(request, response);
        request.getSession().setAttribute("originPath", page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void processRequest2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        ActionStorage actionStorage = ActionStorage.getInstance();
        Action action = actionStorage.getAction(request);
        String page = action.execute(request, response);
//        request.getSession().setAttribute("originPath", page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
