package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.study.poject.cruise.util.StringStorage.VIEW_EXCURSION_ID;
public class ViewExcursion implements Action {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String ALL_EXCURSIONS = "allExcursions";
        final String EXCURSION = "excursion";

        String excursionIdStr = request.getParameter(VIEW_EXCURSION_ID);
        if (excursionIdStr == null)
            return ConfigurationManager.getProperty("path.page.viewexcursion");

        Long excursionId;
        try {
            excursionId = Long.parseLong(excursionIdStr);
        } catch (NumberFormatException e){
            return ConfigurationManager.getProperty("path.page.viewexcursion");
        }

        List<Excursion> allExcursions;
        try {
            allExcursions = (ArrayList<Excursion>) request.getSession().getAttribute(ALL_EXCURSIONS);
        } catch (ClassCastException e){
            return ConfigurationManager.getProperty("path.page.viewexcursion");
        }

        if(allExcursions != null){
            for (Excursion excurision : allExcursions) {    // Это чтобы опять не ходить в базу - возьмем конкретную экскурсию из листа
                if (excurision.getId().equals(excursionId)) {
                    request.getSession().setAttribute(EXCURSION, excurision);
                    break;
                }
            }
        } else
            request.getSession().setAttribute(EXCURSION, new PortExcursionService().ViewExcursionsInPortById(excursionId));

        return ConfigurationManager.getProperty("path.page.viewexcursion");
    }
}
