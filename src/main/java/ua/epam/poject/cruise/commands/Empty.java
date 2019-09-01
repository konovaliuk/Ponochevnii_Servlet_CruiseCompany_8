package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class Empty implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        /* в случае ошибки или прямого обращения к контроллеру переадресация на главную страницу */
        String page = ConfigurationManager.getProperty("path.page.startpage");
        return page;
    }
}
