package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 1895226117242503460L;

    private int id;
    private int userId;
    private int cruiseId;
    private int ticketclassId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }


    public int getTicketclassId() {
        return ticketclassId;
    }

    public void setTicketclassId(int ticketclassId) {
        this.ticketclassId = ticketclassId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                userId == ticket.userId &&
                cruiseId == ticket.cruiseId &&
                ticketclassId == ticket.ticketclassId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, cruiseId, ticketclassId);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", userId: ").append(userId)
                .append(", cruiseId: ").append(cruiseId)
                .append(", ticketclassId: ").append(ticketclassId)
                .toString();
    }

}
