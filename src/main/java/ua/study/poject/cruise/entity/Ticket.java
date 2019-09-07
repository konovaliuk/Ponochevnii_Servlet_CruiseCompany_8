package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {

    private static final long serialVersionUID = -664016167098701929L;

    private Long id = -1L;
    private Long userId;
    private Long cruiseId;
    private Long ticketclassId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }


    public Long getTicketclassId() {
        return ticketclassId;
    }

    public void setTicketclassId(Long ticketclassId) {
        this.ticketclassId = ticketclassId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id) &&
                userId.equals(ticket.userId) &&
                cruiseId.equals(ticket.cruiseId) &&
                ticketclassId.equals(ticket.ticketclassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, cruiseId, ticketclassId);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", userId: " + userId +
                ", cruiseId: " + cruiseId +
                ", ticketclassId: " + ticketclassId;
    }

}
