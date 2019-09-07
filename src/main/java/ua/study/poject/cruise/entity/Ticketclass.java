package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Ticketclass implements Serializable {

    private static final long serialVersionUID = -2431025132940517731L;
    private Long id = -1L;
    private String ticketclassName; // ticketclass_name

    public static final String TICKET_CLASS_FIRST = "first";
    public static final String TICKET_CLASS_SECOND = "second";
    public static final String TICKET_CLASS_THIRD = "third";
    public static final String TICKET_CLASS_FOURTH = "fourth";


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTicketclassName() {
        return ticketclassName;
    }

    public void setTicketclassName(String ticketclassName) {
        this.ticketclassName = ticketclassName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticketclass that = (Ticketclass) o;
        return id.equals(that.id) &&
                Objects.equals(ticketclassName, that.ticketclassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketclassName);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", ticketclassName: " + ticketclassName;
    }

}
