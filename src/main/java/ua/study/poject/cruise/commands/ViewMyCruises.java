package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.resource.ConfigurationManager;
import ua.study.poject.cruise.service.CruiseService;
import ua.study.poject.cruise.service.PurchaseService;
import ua.study.poject.cruise.util.StringStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewMyCruises implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PurchaseService purchaseService = new PurchaseService();

        User currentUser = (User) request.getSession().getAttribute(StringStorage.USER_IN_SESSION);
        List<PrintableCruise> myPrintableCruises = purchaseService.findMyPrintableCruises(currentUser);

        List<Excursion> myExcursions = purchaseService.findMyExcursions(currentUser);

        request.getSession().setAttribute("myPrintableCruises", myPrintableCruises);
        request.getSession().setAttribute("myExcursions", myExcursions);
        return ConfigurationManager.getProperty("path.page.viewmycruises");
    }
}
