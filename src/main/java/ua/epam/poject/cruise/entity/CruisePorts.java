package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class CruisePorts implements Serializable {

    private static final long serialVersionUID = 1521561311324302224L;

    private Long id = -1L;
    private String dateIn;
    private String dateOut;
    private Long cruiseId;
    private Long portId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }


    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
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
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", dateIn: ").append(dateIn)
                .append(", dateOut: ").append(dateOut)
                .append(", cruiseId: ").append(cruiseId)
                .append(", portId: ").append(portId)
                .toString();
    }

}
