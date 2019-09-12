package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excurision;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewExcursion implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String excursionIdStr = request.getParameter("viewExcursionId");
        if (excursionIdStr == null)
            return ConfigurationManager.getProperty("path.page.viewexcursion");

        Long excursionId;
        try {
            excursionId = Long.parseLong(excursionIdStr);
        } catch (NumberFormatException e){
            return ConfigurationManager.getProperty("path.page.viewexcursion");
        }

        List<Excurision> allExcursions;
        try {
            allExcursions = (ArrayList<Excurision>) request.getSession().getAttribute("allExcursions");
        } catch (ClassCastException e){
            return ConfigurationManager.getProperty("path.page.viewexcursion");
        }

        if(allExcursions != null){
            for (Excurision excurision : allExcursions) {    // Это чтобы опять не ходить в базу - возьмем конкретную экскурсию из листа
                if (excurision.getId().equals(excursionId)) {
                    request.getSession().setAttribute("excursion", excurision);
                    break;
                }
            }
        } else
            request.getSession().setAttribute("excursion", new PortExcursionService().ViewExcursionsInPortById(excursionId));

        return ConfigurationManager.getProperty("path.page.viewexcursion");
    }
}
