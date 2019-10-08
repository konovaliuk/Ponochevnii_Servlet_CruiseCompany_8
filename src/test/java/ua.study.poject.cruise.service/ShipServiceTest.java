package ua.study.poject.cruise.service;

import org.junit.Before;
import org.junit.Test;
import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.Shipservice;
import ua.study.poject.cruise.entity.printableentity.PrintableServiceOnShip;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableServiceOnShipDao;
import ua.study.poject.cruise.persistance.dao.ServiceDao;
import ua.study.poject.cruise.persistance.dao.ShipDao;
import ua.study.poject.cruise.persistance.dao.ShipserviceDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipServiceTest {

    static AbstractDaoFactory daoFactory;
    static ShipDao shipDao;
    static PrintableServiceOnShipDao printableServiceOnShipDao;
    static PrintableServiceOnShip printableServiceOnShip;
    static ShipserviceDao shipserviceDao;

    @Before
    public void initBeforeEachMeth() throws GeneralCheckedException {
        printableServiceOnShip = new PrintableServiceOnShip();
        printableServiceOnShip.setServiceName("telephone");
        printableServiceOnShip.setServiceId(1L);
        printableServiceOnShip.setShipServiceId(1L);
        printableServiceOnShip.setPayable(1);



        shipDao = mock(ShipDao.class);

        when(shipDao.create(getShip(0L, "Princess", 1300L, 1000L, 1000L, 1000L, 1000L))).thenReturn(1);
        when(shipDao.findAll()).thenReturn(Arrays.asList(getShip(1L, "Princess", 1300L, 1000L, 1000L, 1000L, 1000L)));

        printableServiceOnShipDao = mock(PrintableServiceOnShipDao.class);
        when(printableServiceOnShipDao.findAllServicesByShipId(1L)).thenReturn(Arrays.asList(printableServiceOnShip));

        shipserviceDao = mock(ShipserviceDao.class);
        when(shipserviceDao.isServicePresentOnThisShip(1L, 1L)).thenReturn(true);
        when(shipserviceDao.isServicePresentOnThisShip(1L, 2L)).thenReturn(false);
        when(shipserviceDao.create(getShipservice(0L, 1L, 1L, 1))).thenReturn(1);


        daoFactory = mock(AbstractDaoFactory.class);
        when(daoFactory.getShipDao()).thenReturn(shipDao);
        when(daoFactory.getPrintableServiceOnShipDao()).thenReturn(printableServiceOnShipDao);
        when(daoFactory.getShipserviceDao()).thenReturn(shipserviceDao);
    }

    @Test
    public void createShipTest(){
        int result = new ShipService(daoFactory).createShip("Princess", 1300L, 1000L, 1000L, 1000L, 1000L);
        assertEquals(result, 1);
    }

    @Test
    public void getAllShipsTest(){
        List<Ship> result = new ShipService(daoFactory).getAllShips();
        assertEquals(result, Arrays.asList(getShip(1L, "Princess", 1300L, 1000L, 1000L, 1000L, 1000L)));
    }

    @Test
    public void getAllServicesByShipIdTest(){
        List<PrintableServiceOnShip> result = new ShipService(daoFactory).getAllServicesByShipId(1L);
        assertEquals(result, Arrays.asList(printableServiceOnShip));
    }

    @Test
    public void isServicePresentOnThisShipOkTest(){
        boolean result = new ShipService(daoFactory).isServicePresentOnThisShip(1L, 1L);
        assertEquals(result, true);
    }

    @Test
    public void isServicePresentOnThisShipFailTest(){
        boolean result = new ShipService(daoFactory).isServicePresentOnThisShip(1L, 2L);
        assertEquals(result, false);
    }

    @Test
    public void addNewServiceToShipTest(){
        int result = new ShipService(daoFactory).addNewServiceToShip(1L, 1, 1L);
        assertEquals(result, 1);
    }









    Ship getShip(Long id, String name, Long nStaff, Long nFirst, Long nSecond, Long nThird, Long nFourth){
       Ship ship = new Ship();
       if(id != null) {
           ship.setId(id);
       }
       ship.setShipName(name);
       ship.setNStaff(nStaff);
       ship.setNFirstClass(nFirst);
       ship.setNSecondClass(nSecond);
       ship.setNThirdClass(nThird);
       ship.setNFourthClass(nFourth);

       return ship;
    }

    Shipservice getShipservice(Long id, Long shipId, Long serviceId, Integer payable){
        Shipservice shipservice = new Shipservice();
        if(id != null){
            shipservice.setId(id);
        }
        shipservice.setShipId(shipId);
        shipservice.setServiceId(serviceId);
        shipservice.setPayable(payable);
        return shipservice;
    }

}
