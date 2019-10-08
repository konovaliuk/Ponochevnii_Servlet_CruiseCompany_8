package ua.study.poject.cruise.service;

import org.junit.Before;
import org.junit.Test;
import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.ServiceDao;
import ua.study.poject.cruise.persistance.dao.ShipserviceDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipserviceServiceTest {

    static AbstractDaoFactory daoFactory;
    static ServiceDao serviceDao;
    static ShipserviceDao shipserviceDao;

    @Before
    public void initBeforeEachMeth() throws GeneralCheckedException {

        shipserviceDao = mock(ShipserviceDao.class);
        serviceDao = mock(ServiceDao.class);

        when(shipserviceDao.findAllIdByShipIdServiceId(1L, 1L)).thenReturn(Arrays.asList(1L));

        when(serviceDao.findByName("Wi-Fi")).thenReturn(getService(1L, "Wi-Fi"));
        when(serviceDao.findById(1L)).thenReturn(getService(1L, "Wi-Fi"));
        when(serviceDao.create(getService(null, "telephone"))).thenReturn(2);
        when(serviceDao.findAll()).thenReturn(Arrays.asList(getService(1L, "Wi-Fi"), getService(2L, "telephone")));

        daoFactory = mock(AbstractDaoFactory.class);
        when(daoFactory.getServiceDao()).thenReturn(serviceDao);
    }

    @Test
    public void getServeceByNameTest() {
        Service result = new ShipserviceService(daoFactory).getServeceByName("Wi-Fi");
        assertEquals(getService(1L, "Wi-Fi"), result);
    }

    @Test
    public void getServeceByIdTest() {
        Service result = new ShipserviceService(daoFactory).getServeceById(1L);
        assertEquals(getService(1L, "Wi-Fi"), result);
    }

    @Test
    public void addNewServiceToSystemTest() {
        int result = new ShipserviceService(daoFactory).addNewServiceToSystem("telephone");
        assertEquals(2, result);
    }

    @Test
    public void getAllServisesInSystemTest() {
        List<Service> result = new ShipserviceService(daoFactory).getAllServisesInSystem();
        assertEquals(Arrays.asList(getService(1L, "Wi-Fi"), getService(2L, "telephone")), result);
    }








    Service getService(Long id, String serviceName) {
        Service service = new Service();
        if (id != null) {
            service.setId(id);
        }
        if (serviceName != null) {
            service.setServiceName(serviceName);
        }
        return service;
    }
}
