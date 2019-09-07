package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Locale;

public class ChangeLanguage implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter("language");
        Locale.setDefault(new Locale(language));
        if(request.getSession(false) != null){
            request.getSession().setAttribute("language", request.getParameter("language"));
        }
        response.addCookie(new Cookie("language", request.getParameter("language")));

        String path = ConfigurationManager.getProperty("path.page.startpage");

        if(request.getSession().getAttribute("originPath") != null)
            path = (String)request.getSession().getAttribute("originPath");

        return path;
    }
}
