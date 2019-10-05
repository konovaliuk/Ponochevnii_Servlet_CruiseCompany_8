package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Excursion;
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

/**
 * A class that executes logic related to payment for purchases that are added to the cart
 */
public class CartPayService {

    private static final Logger LOGGER = Logger.getLogger(CartPayService.class);
    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    /**
     * The pay method records everything the user has bought and empties the basket.
     * The method is organized transactional, because records have to be done
     * in several tables at the same time
     * @param userId User who buys a Cruise or Excursion
     * @param cruisesInCart List of Cruises added to Cart
     * @param ticketclassesInCart List of ticket classes that match cruises purchased
     * @param excurisionsInCart List of excursions that the user added to the cart
     * @return the number of entries made to all tables or -1 if recording failed
     */
    public int pay(Long userId, List<PrintableCruise> cruisesInCart, List<Ticketclass> ticketclassesInCart, List<Excursion> excurisionsInCart) {

        if (cruisesInCart.size() != ticketclassesInCart.size()) return -1;
        int sum = 0;

        try (Atomizer atomizer = AtomizerFactory.getAtomizer()) {
            if (cruisesInCart.size() != 0) {
                TicketDao ticketDao = daoFactory.getTicketDao(atomizer);
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
                TicketExcursionDao ticketExcursionDao = daoFactory.getTicketExcursionDao(atomizer);
                for (Excursion excurision : excurisionsInCart) {
                    sum += ticketExcursionDao.create(userId, excurision.getId());
                }
            }

            atomizer.recordChanges();
            return sum;

        } catch (Exception e) {
            LOGGER.error("Ошибка при работе с TicketDao или TicketExcursionDao", e);
        }
        return -1;
    }
}
