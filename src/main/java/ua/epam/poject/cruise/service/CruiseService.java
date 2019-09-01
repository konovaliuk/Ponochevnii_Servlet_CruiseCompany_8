package ua.epam.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Cruise;
import ua.epam.poject.cruise.entity.CruisePorts;
import ua.epam.poject.cruise.entity.Ship;
import ua.epam.poject.cruise.entity.printableentity.PrintableCruise;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.CruiseDao;
import ua.epam.poject.cruise.persistance.dao.PrintableCruiseDao;
import ua.epam.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.epam.poject.cruise.persistance.datasource.Atomizer;
import ua.epam.poject.cruise.persistance.datasource.impl.AtomizerFactory;
import ua.epam.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class CruiseService {

    private static final Logger LOGGER = Logger.getLogger(CruiseService.class);

    private AbstractDaoFactory daoFactory = new MySqlDaoFactory();

    void createNewCruise(Ship ship, List<CruisePorts> cruisePortsList, double priceFirstClass, double priceSecondClass, double priceThirdClass, double priceFourthClass){
        try (Atomizer atomizer = AtomizerFactory.getAtomizer()){

                // создать новый круиз
                // сделать записи в таблицу CruisePorts



            atomizer.recordChanges();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

   public List<PrintableCruise> viewAllCruises(){
        List<PrintableCruise> list = new ArrayList<>();
        PrintableCruiseDao printableCruiseDao = null;
        try {
            printableCruiseDao = daoFactory.getPrintableCruiseDaoImpl();
            list = printableCruiseDao.findAllForWebPage();

        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if(printableCruiseDao != null)
                printableCruiseDao.close();
        }
        return list;
    }
}
