package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Empty implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /* в случае ошибки или прямого обращения к контроллеру переадресация на главную страницу */
        return ConfigurationManager.getProperty("path.page.startpage");
    }
}
