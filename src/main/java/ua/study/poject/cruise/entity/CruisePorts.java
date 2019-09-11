package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class CruisePorts implements Serializable {

    private static final long serialVersionUID = 5830376018918459139L;

    private Long id = -1L;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;
    private Long cruiseId;
    private Long portId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDateTime dateIn) {
        this.dateIn = dateIn;
    }


    public LocalDateTime getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDateTime dateOut) {
        this.dateOut = dateOut;
    }


    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }


    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruisePorts that = (CruisePorts) o;
        return id.equals(that.id) &&
                cruiseId.equals(that.cruiseId) &&
                portId.equals(that.portId) &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(dateOut, that.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateIn, dateOut, cruiseId, portId);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "id: " + id +
                ", dateIn: " + ((dateIn != null) ? dateIn.format(dateTimeFormatter) : "") +
                ", dateOut: " + ((dateIn != null) ? dateIn.format(dateTimeFormatter) : "") +
                ", cruiseId: " + cruiseId +
                ", portId: " + portId;
    }

}
