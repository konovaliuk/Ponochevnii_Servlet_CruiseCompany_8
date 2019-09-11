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

public class CreateCruise implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();
        PortExcursionService portExcursionService = new PortExcursionService();

        request.getSession().setAttribute("allPorts", portExcursionService.getAllPorts());
        request.getSession().setAttribute("allShips", shipService.getAllShips());

        if (request.getParameter("createcruiseForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.createcruise");
        }

        try {
            Long shipId = Long.parseLong(request.getParameter("selectedship"));
            double priceFirstClass = Double.parseDouble(request.getParameter("priceFirstClass"));
            double priceSecondClass = Double.parseDouble(request.getParameter("priceSecondClass"));
            double priceThirdClass = Double.parseDouble(request.getParameter("priceThirdClass"));
            double priceFourthClass = Double.parseDouble(request.getParameter("priceFourthClass"));

            List<CruisePorts> cruisePortsList = new ArrayList<>();


//            LocalDateTime date = LocalDateTime.parse(request.getParameter("datestart"));
//            Timestamp timestamp = Timestamp.valueOf(date);
//            LocalDateTime date2 = timestamp.toLocalDateTime();


            if (validate(request, "datestart", "datestart", "selectedportstart"))
                cruisePortsList.add(createCruisePorts(null, LocalDateTime.parse(request.getParameter("datestart")), Long.parseLong(request.getParameter("selectedportstart"))));

            if (validate(request, "date1in", "date1out", "selectedport1"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("date1in")), LocalDateTime.parse(request.getParameter("date1out")), Long.parseLong(request.getParameter("selectedport1"))));

            if (validate(request, "date2in", "date2out", "selectedport2"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("date2in")), LocalDateTime.parse(request.getParameter("date2out")), Long.parseLong(request.getParameter("selectedport2"))));

            if (validate(request, "date3in", "date3out", "selectedport3"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("date3in")), LocalDateTime.parse(request.getParameter("date3out")), Long.parseLong(request.getParameter("selectedport3"))));

            if (validate(request, "date4in", "date4out", "selectedport4"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("date4in")), LocalDateTime.parse(request.getParameter("date4out")), Long.parseLong(request.getParameter("selectedport4"))));

            if (validate(request, "date5in", "date5out", "selectedport5"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("date5in")), LocalDateTime.parse(request.getParameter("date5out")), Long.parseLong(request.getParameter("selectedport5"))));

            if (validate(request, "date6in", "date6out", "selectedport6"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("date6in")), LocalDateTime.parse(request.getParameter("date6out")), Long.parseLong(request.getParameter("selectedport6"))));

            if (validate(request, "date7in", "date7out", "selectedport7"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("date7in")), LocalDateTime.parse(request.getParameter("date7out")), Long.parseLong(request.getParameter("selectedport7"))));

            if (validate(request, "datestop", "datestop", "selectedportstop"))
                cruisePortsList.add(createCruisePorts(LocalDateTime.parse(request.getParameter("datestop")), null, Long.parseLong(request.getParameter("selectedportstop"))));

            int cruiseId = new CruiseService().createCruise(shipId, priceFirstClass, priceSecondClass, priceThirdClass, priceFourthClass, cruisePortsList);

            if (cruiseId < 1)
                request.getSession().setAttribute("errorMessage", "Не удалось создать круиз, проверьте правильность заполнения полей");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Не удалось создать круиз, проверьте правильность заполнения полей");
        }
        request.getSession().setAttribute("errorMessage", "Круиз успешно создан");

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
