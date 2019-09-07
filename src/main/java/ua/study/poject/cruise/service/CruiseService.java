package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Cruise;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableCruiseDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.Atomizer;
import ua.study.poject.cruise.persistance.datasource.impl.AtomizerFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class CruiseService {

    private static final Logger LOGGER = Logger.getLogger(CruiseService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    // List<CruisePorts> cruisePortsList,
    public int createCruise(Long selectedShip, double priceFirstClass, double priceSecondClass, double priceThirdClass, double priceFourthClass) {
        try (Atomizer atomizer = AtomizerFactory.getAtomizer()) {

            // TODO создать новый круиз
            // TODO сделать записи в таблицу CruisePorts
            // TODO ...............
            Cruise cruise = new Cruise();
            cruise.setShipId(selectedShip);
            cruise.setPriceFirstClass(priceFirstClass);
            cruise.setPriceSecondClass(priceSecondClass);
            cruise.setPriceThirdClass(priceThirdClass);
            cruise.setPriceFourthClass(priceFourthClass);

            atomizer.recordChanges();

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<PrintableCruise> viewAllCruises() {
        List<PrintableCruise> list = new ArrayList<>();
        PrintableCruiseDao printableCruiseDao = null;
        try {
            printableCruiseDao = daoFactory.getPrintableCruiseDaoImpl();
            list = printableCruiseDao.findAllForWebPage();

        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (printableCruiseDao != null)
                printableCruiseDao.close();
        }
        return list;
    }

//    public Object viewAllBonusesInEachCruise(User manager) {
//
//    }
}
