package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excurision;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewPort implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PortExcursionService portExcursionService = new PortExcursionService();
        String selectedPortIdStr = request.getParameter("selectedPortId");
        Long selectedPortId = null;
        if (selectedPortIdStr != null)
            try {
                selectedPortId = Long.parseLong(selectedPortIdStr);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("viewportMessage", "message.viewport.err");
            }

        List<Excurision> allExcursions = portExcursionService.ViewExcursionsInPortByPortId(selectedPortId);
        request.getSession().setAttribute("allExcursions", allExcursions);
        request.getSession().setAttribute("selectedPort", portExcursionService.getPortById(selectedPortId));


        return ConfigurationManager.getProperty("path.page.viewport");
    }
}
