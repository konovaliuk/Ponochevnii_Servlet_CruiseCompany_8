package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.*;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.*;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.Atomizer;
import ua.study.poject.cruise.persistance.datasource.impl.AtomizerFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class CruiseService {

    private static final Logger LOGGER = Logger.getLogger(CruiseService.class);
    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    public List<Ticketclass> getAllTicketClasses(){
        TicketclassDao ticketclassDao = null;
        List<Ticketclass> list = new ArrayList<>();
        try {
            ticketclassDao = daoFactory.getTicketclassDao();
            list = ticketclassDao.findAll();
        }catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (ticketclassDao != null)
                ticketclassDao.close();
        }
        return list;
    }

    public int createCruise(Long selectedShip, double priceFirstClass, double priceSecondClass, double priceThirdClass, double priceFourthClass, List<CruisePorts> cruisePortsList) {
        try (Atomizer atomizer = AtomizerFactory.getAtomizer()) {

            CruiseDao cruiseDao = daoFactory.getCruiseDao(atomizer);
            CruisePortsDao cruisePortsDao = daoFactory.getCruisePortsDao(atomizer);

            Cruise cruise = new Cruise();
            cruise.setShipId(selectedShip);
            cruise.setPriceFirstClass(priceFirstClass);
            cruise.setPriceSecondClass(priceSecondClass);
            cruise.setPriceThirdClass(priceThirdClass);
            cruise.setPriceFourthClass(priceFourthClass);

            int cruiseId = cruiseDao.create(cruise);

            int result;
            for(CruisePorts tempCP : cruisePortsList){
                tempCP.setCruiseId((long)cruiseId);
                result = cruisePortsDao.create(tempCP);
                if(result < 1)
                    throw new GeneralCheckedException("Не удалось записать порты для круиза №" + cruiseId);
            }

            atomizer.recordChanges();

            return cruiseId;

        } catch (Exception e) {
            LOGGER.error("Ошибка при работе с CruiseDao или CruisePortsDao");
        }
        return -1;
    }

    public List<PrintableCruise> viewAllCruises() {
        List<PrintableCruise> list = new ArrayList<>();
        PrintableCruiseDao printableCruiseDao = null;
        PrintableCruisePortDao printableCruisePortDao = null;
        try {
            printableCruiseDao = daoFactory.getPrintableCruiseDao();
            printableCruisePortDao = daoFactory.getPrintableCruisePortDao();
            list = printableCruiseDao.findAllPrintableCruisesWithoutPorts();
            for (PrintableCruise printableCruise : list) {
                printableCruise.setPrintableCruisePorts(printableCruisePortDao.findAllPrintableCruisePortByCruiseId(printableCruise.getCruiseId()));
            }
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (printableCruiseDao != null)
                printableCruiseDao.close();
            if (printableCruisePortDao != null)
                printableCruisePortDao.close();
        }
        return list;
    }
//
//    public List<PrintableCruisePort> findAllPrintableCruisePortByCruiseId(Long cruiseId) {
//        PrintableCruisePortDao printableCruisePortDao = null;
//        List<PrintableCruisePort> list = new ArrayList<>();
//        try {
//            printableCruisePortDao = daoFactory.getPrintableCruisePortDao();
//            list = printableCruisePortDao.findAllPrintableCruisePortByCruiseId(cruiseId);
//        } catch (GeneralCheckedException e) {
//            LOGGER.error(e);
//        } finally {
//            if (printableCruisePortDao != null)
//                printableCruisePortDao.close();
//        }
//        return list;
//    }

    public List<PrintableCruise> getAllPrintableCruisesByShipId(Long shipId) {
        List<PrintableCruise> list = new ArrayList<>();
        PrintableCruiseDao printableCruiseDao = null;
        PrintableCruisePortDao printableCruisePortDao = null;
        try {
            printableCruiseDao = daoFactory.getPrintableCruiseDao();
            printableCruisePortDao = daoFactory.getPrintableCruisePortDao();
            list = printableCruiseDao.findAllPrintableCruisesWithoutPortsByShipId(shipId);
            for (PrintableCruise printableCruise : list) {
                printableCruise.setPrintableCruisePorts(printableCruisePortDao.findAllPrintableCruisePortByCruiseId(printableCruise.getCruiseId()));
            }
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (printableCruiseDao != null)
                printableCruiseDao.close();
            if (printableCruisePortDao != null)
                printableCruisePortDao.close();
        }
        return list;
    }


}
