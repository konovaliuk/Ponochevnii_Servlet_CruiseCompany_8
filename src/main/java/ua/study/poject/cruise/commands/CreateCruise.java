package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.CruisePorts;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.PortExcursionService;
import ua.study.poject.cruise.service.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ua.study.poject.cruise.util.StringStorage.*;

public class CreateCruise implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String MESSAGE = "createcruiseMessage";
        final String ALL_PORTS = "allPorts";
        final String ALL_SHIPS = "allShips";

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();
        PortExcursionService portExcursionService = new PortExcursionService();

        request.getSession().setAttribute(ALL_PORTS, portExcursionService.getAllPorts());
        request.getSession().setAttribute(ALL_SHIPS, shipService.getAllShips());

        if (request.getParameter(CREATE_CRUISE_FORM) == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.createcruise");
        }

        try {
            Long shipId = Long.parseLong(request.getParameter(SELECTED_SHIP));
            double priceFirstClass = Double.parseDouble(request.getParameter(PRICE_FIRST_CLASS));
            double priceSecondClass = Double.parseDouble(request.getParameter(PRICE_SECOND_CLASS));
            double priceThirdClass = Double.parseDouble(request.getParameter(PRICE_THIRD_CLASS));
            double priceFourthClass = Double.parseDouble(request.getParameter(PRICE_FOURTH_CLASS));

            List<CruisePorts> cruisePortsList = new ArrayList<>();

            if (validate(request, DATE_START, DATE_START, SELECTED_PORT_START))
                cruisePortsList.add(createCruisePorts(null, LocalDateTime.parse(request.getParameter(DATE_START)), Long.parseLong(request.getParameter(SELECTED_PORT_START))));

            if (validate(request, DATE_1_IN, DATE_1_OUT, SELECTED_PORT_1))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_1_IN)), LocalDateTime.parse(request.getParameter(DATE_1_OUT)), Long.parseLong(request.getParameter(SELECTED_PORT_1))));

            if (validate(request, DATE_2_IN, DATE_2_OUT, SELECTED_PORT_2))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_2_IN)), LocalDateTime.parse(request.getParameter(DATE_2_OUT)), Long.parseLong(request.getParameter(SELECTED_PORT_2))));

            if (validate(request, DATE_3_IN, DATE_3_OUT, SELECTED_PORT_3))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_3_IN)), LocalDateTime.parse(request.getParameter(DATE_3_OUT)), Long.parseLong(request.getParameter(SELECTED_PORT_3))));

            if (validate(request, DATE_4_IN, DATE_4_OUT, SELECTED_PORT_4))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_4_IN)), LocalDateTime.parse(request.getParameter(DATE_4_OUT)), Long.parseLong(request.getParameter(SELECTED_PORT_4))));

            if (validate(request, DATE_5_IN, DATE_5_OUT, SELECTED_PORT_5))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_5_IN)), LocalDateTime.parse(request.getParameter(DATE_5_OUT)), Long.parseLong(request.getParameter(SELECTED_PORT_5))));

            if (validate(request, DATE_6_IN, DATE_6_OUT, SELECTED_PORT_6))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_6_IN)), LocalDateTime.parse(request.getParameter(DATE_6_OUT)), Long.parseLong(request.getParameter(SELECTED_PORT_6))));

            if (validate(request, DATE_7_IN, DATE_7_OUT, SELECTED_PORT_7))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_7_IN)), LocalDateTime.parse(request.getParameter(DATE_7_OUT)), Long.parseLong(request.getParameter(SELECTED_PORT_7))));

            if (validate(request, DATE_STOP, DATE_STOP, SELECTED_PORT_STOP))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter(DATE_STOP)), null, Long.parseLong(request.getParameter(SELECTED_PORT_STOP))));

            int cruiseId = new CruiseService().createCruise(shipId, priceFirstClass, priceSecondClass, priceThirdClass, priceFourthClass, cruisePortsList);

            if (cruiseId < 1)
                request.getSession().setAttribute(MESSAGE, "message.createcruise.errcreate");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute(MESSAGE, "message.createcruise.errcreate");
        }
        request.getSession().setAttribute(MESSAGE, "message.createcruise.ok");

        return ConfigurationManager.getProperty("path.page.createcruise");
    }

    private boolean validate(HttpServletRequest request, String dateIn, String dateOut, String portId) {
        return !request.getParameter(dateIn).equals("") && !request.getParameter(dateOut).equals("") && !request.getParameter(portId).equals("");
    }

    private CruisePorts createCruisePorts(LocalDateTime dateIn, LocalDateTime dateOut, Long portId) {
        CruisePorts cruisePorts = new CruisePorts();
        cruisePorts.setDateIn(dateIn);
        cruisePorts.setDateOut(dateOut);
        cruisePorts.setPortId(portId);
        return cruisePorts;
    }

}
