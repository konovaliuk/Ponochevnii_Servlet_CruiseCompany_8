package ua.study.poject.cruise.service;

import org.junit.Before;
import org.junit.Test;
import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.entity.Port;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.ExcursionDao;
import ua.study.poject.cruise.persistance.dao.PortDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PortExcursionServiceTest {

    static AbstractDaoFactory daoFactory;
    static PortDao portDao;
    static ExcursionDao excursionDao;
    @Before
    public void initBeforeEachMeth() throws GeneralCheckedException {

        portDao = mock(PortDao.class);
        excursionDao = mock(ExcursionDao.class);

        when(portDao.create(getPort(0L, "Spain", "Barcelona"))).thenReturn(1);
        when(portDao.findAll()).thenReturn(Arrays.asList(getPort(1L, "Spain", "Barcelona")));
        when(portDao.findById(1L)).thenReturn(getPort(1L, "Spain", "Barcelona"));

        when(excursionDao.create(getExcursion(0L, "name", "description", 99.99, 1L))).thenReturn(1);
        when(excursionDao.findByPortId(1L)).thenReturn(Arrays.asList(getExcursion(1L, "name", "description", 99.99, 1L)));
        when(excursionDao.findById(1L)).thenReturn(getExcursion(2L, "name2", "description2", 98.98, 2L));

        daoFactory = mock(AbstractDaoFactory.class);
        when(daoFactory.getPortDao()).thenReturn(portDao);
        when(daoFactory.getExcursionDao()).thenReturn(excursionDao);
    }

    @Test
    public void createPortTest(){
        int result = new PortExcursionService(daoFactory).createPort("Spain", "Barcelona");
        assertEquals(result, 1);
    }

    @Test
    public void createExcursionTest(){
        int result = new PortExcursionService(daoFactory).createExcursion("name", 99.99 , "description", 1L);
        assertEquals(result, 1);
    }

    @Test
    public void getAllPortsTest(){
        List<Port> result = new PortExcursionService(daoFactory).getAllPorts();
        assertEquals(result, Arrays.asList(getPort(1L, "Spain", "Barcelona")));
    }

    @Test
    public void viewExcursionsInPortByPortIdTest(){
        List<Excursion> result = new PortExcursionService(daoFactory).viewExcursionsInPortByPortId(1L);
        assertEquals(result, Arrays.asList(getExcursion(1L, "name", "description", 99.99, 1L)));
    }

    @Test
    public void viewExcursionsInPortByIdTest(){
        Excursion result = new PortExcursionService(daoFactory).viewExcursionsInPortById(1L);
        assertEquals(result, getExcursion(2L, "name2", "description2", 98.98, 2L));
    }

    @Test
    public void getPortByIdTest(){
        Port result = new PortExcursionService(daoFactory).getPortById(1L);
        assertEquals(result, getPort(1L, "Spain", "Barcelona"));
    }












    Port getPort(Long id, String country, String city){
        Port port = new Port();
        if(id != null) {
            port.setId(id);
        }
        port.setCountry(country);
        port.setCity(city);
        return port;
    }

    Excursion getExcursion(Long id, String name, String description, double price, Long portId){
        Excursion excursion = new Excursion();
        if(id != null){
            excursion.setId(id);
        }
        excursion.setExcursionName(name);
        excursion.setDescription(description);
        excursion.setPrice(price);
        excursion.setPortId(portId);
        return excursion;
    }
}
