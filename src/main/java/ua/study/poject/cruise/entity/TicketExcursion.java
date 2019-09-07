package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class TicketExcursion implements Serializable {

    private static final long serialVersionUID = 2737058674972929794L;

    private Long id = -1L;
    private Long excurisionId;
    private Long ticketId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getExcurisionId() {
        return excurisionId;
    }

    public void setExcurisionId(Long excurisionId) {
        this.excurisionId = excurisionId;
    }


    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketExcursion that = (TicketExcursion) o;
        return id.equals(that.id) &&
                excurisionId.equals(that.excurisionId) &&
                ticketId.equals(that.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, excurisionId, ticketId);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", excurisionId: " + excurisionId +
                ", ticketId: " + ticketId;
    }

}
