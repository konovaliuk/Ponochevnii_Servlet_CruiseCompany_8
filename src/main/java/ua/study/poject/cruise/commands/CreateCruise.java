package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCruise implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession(false) == null) {  // нет сессии - логинимся
            return ConfigurationManager.getProperty("path.page.signin");
        }

        ShipService shipService = new ShipService();

        request.getSession().setAttribute("allShips", shipService.getAllShips());

        if (request.getParameter("createcruiseForm") == null) {  // пришла команда не с формы - отправляем на форму
            return ConfigurationManager.getProperty("path.page.createcruise");
        }

        // id, ship_id, price_first_class, price_second_class, price_third_class, price_fourth_class
        try {
            Long shipId = Long.parseLong(request.getParameter("selectedship"));
            double priceFirstClass = Double.parseDouble(request.getParameter("priceFirstClass"));
            double priceSecondClass = Double.parseDouble(request.getParameter("priceSecondClass"));
            double priceThirdClass = Double.parseDouble(request.getParameter("priceThirdClass"));
            double priceFourthClass = Double.parseDouble(request.getParameter("priceFourthClass"));


            int result = new CruiseService().createCruise(shipId, priceFirstClass, priceSecondClass, priceThirdClass, priceFourthClass);

            if (result <= 0) {
                request.getSession().setAttribute("errorMessage", "Не удалось создать круиз, проверьте правильность заполнения полей");
            } else request.getSession().setAttribute("errorMessage", "Круиз успешно создан");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Не удалось создать круиз, проверьте правильность заполнения полей");
        }
        return ConfigurationManager.getProperty("path.page.createcruise");
    }


}
