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

    private static final long serialVersionUID = 4941185773431111964L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< doGet >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getPathInfo() = " + req.getPathInfo());
        System.out.println("req.getLocale() = " + req.getLocale());
        System.out.println("req.getParameter language = " + req.getParameter("language"));
        System.out.println("req.getContextPath() = " + req.getContextPath());
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("req.getParameter(\"name\") = " + req.getParameter("name"));
//

        processRequest2(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< doPost >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getPathInfo() = " + req.getPathInfo());

        System.out.println("req.getPathTranslated() = " + req.getPathTranslated());
        System.out.println("req.getContextPath() = " + req.getContextPath());
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("req.getLocale() = " + req.getLocale());
        System.out.println("req.getLocale().getLanguage() = " + req.getLocale().getLanguage());

        System.out.println("req.getParameter(\"command\") = " + req.getParameter("command"));
        System.out.println("req.getParameter(\"excursionName\") = " + req.getParameter("excursionName"));
        System.out.println("req.getParameter(\"description\") = " + req.getParameter("description"));


//
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(" ++++++++++++++++++++++++++++++++++++++++++++++++     request.getParameter(\"command\")" + request.getParameter("command"));

        ActionStorage actionStorage = ActionStorage.getInstance();
        Action action = actionStorage.getAction(request);
        String page = action.execute(request, response);
        request.getSession().setAttribute("originPath", page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void processRequest2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionStorage actionStorage = ActionStorage.getInstance();
        Action action = actionStorage.getAction(request);
        String page = action.execute(request, response);
//        request.getSession().setAttribute("originPath", page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}