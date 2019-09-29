package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.PortExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.study.poject.cruise.util.StringStorage.SELECTED_PORT_ID;

public class ViewPort implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "viewportMessage";
        final String ALL_EXCURSIONS = "allExcursions";
        final String SELECTED_PORT = "selectedPort";

        PortExcursionService portExcursionService = new PortExcursionService();
        String selectedPortIdStr = request.getParameter(SELECTED_PORT_ID);
        Long selectedPortId = null;
        if (selectedPortIdStr != null)
            try {
                selectedPortId = Long.parseLong(selectedPortIdStr);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute(MESSAGE, "message.viewport.err");
            }

        List<Excursion> allExcursions = portExcursionService.ViewExcursionsInPortByPortId(selectedPortId);
        request.getSession().setAttribute(ALL_EXCURSIONS, allExcursions);
        request.getSession().setAttribute(SELECTED_PORT, portExcursionService.getPortById(selectedPortId));


        return ConfigurationManager.getProperty("path.page.viewport");
    }
}
