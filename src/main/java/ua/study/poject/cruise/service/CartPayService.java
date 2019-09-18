package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Excurision;
import ua.study.poject.cruise.entity.Ticket;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.persistance.dao.TicketDao;
import ua.study.poject.cruise.persistance.dao.TicketExcursionDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.Atomizer;
import ua.study.poject.cruise.persistance.datasource.impl.AtomizerFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.List;


public class CartPayService {

    private static final Logger LOGGER = Logger.getLogger(CartPayService.class);
    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    public int pay(Long userId, List<PrintableCruise> cruisesInCart, List<Ticketclass> ticketclassesInCart, List<Excurision> excurisionsInCart) {

        if (cruisesInCart.size() != ticketclassesInCart.size()) return -1;
        int sum = 0;


        try (Atomizer atomizer = AtomizerFactory.getAtomizer()) {
            if (cruisesInCart.size() != 0) {
                TicketDao ticketDao = daoFactory.getTicketDaoImpl(atomizer);
                for (int i = 0; i < cruisesInCart.size(); i++) {
                    Ticket ticket = new Ticket();
                    ticket.setId(0L);
                    ticket.setCruiseId(cruisesInCart.get(i).getCruiseId());
                    ticket.setTicketclassId(ticketclassesInCart.get(i).getId());
                    ticket.setUserId(userId);
                    sum += ticketDao.create(ticket);
                }
            }

            if (excurisionsInCart.size() != 0) {
                TicketExcursionDao ticketExcursionDao = daoFactory.getTicketExcursionDaoImpl(atomizer);
                for (Excurision excurision : excurisionsInCart) {
                    sum += ticketExcursionDao.create(userId, excurision.getId());
                }
            }

            atomizer.recordChanges();
            return sum;

        } catch (Exception e) {
            LOGGER.error("Ошибка при работе с TicketDao или TicketExcursionDao");
        }
        return -1;
    }
}
