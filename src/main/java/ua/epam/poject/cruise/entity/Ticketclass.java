package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Ticketclass implements Serializable {

    private static final long serialVersionUID = 5362210079453429384L;

    public static final String TICKET_CLASS_FIRST = "first";
    public static final String TICKET_CLASS_SECOND = "second";
    public static final String TICKET_CLASS_THIRD = "third";
    public static final String TICKET_CLASS_FOURTH = "fourth";

    private int id;
    private String ticketclassName; // ticketclass_name


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return id == that.id &&
                Objects.equals(ticketclassName, that.ticketclassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketclassName);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", ticketclassName: ").append(ticketclassName)
                .toString();
    }

}
