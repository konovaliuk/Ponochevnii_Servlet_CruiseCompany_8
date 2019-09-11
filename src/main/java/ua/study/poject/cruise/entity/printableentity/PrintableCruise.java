package ua.study.poject.cruise.entity.printableentity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class PrintableCruise implements Serializable {

    private static final long serialVersionUID = 6485047061553010123L;

    private Long cruiseId;
    private Long shipId;
    private String shipName;
    private Long portId;
    private String country;
    private String city;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;

    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintableCruise that = (PrintableCruise) o;
        return Objects.equals(cruiseId, that.cruiseId) &&
                Objects.equals(shipId, that.shipId) &&
                Objects.equals(shipName, that.shipName) &&
                Objects.equals(portId, that.portId) &&
                Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(dateOut, that.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cruiseId, shipId, shipName, portId, country, city, dateIn, dateOut);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "CruiseId: " + cruiseId +
                ", shipId: " + shipId +
                ", shipName: " + shipName +
                ", portId: " + portId +
                ", country: " + country +
                ", city: " + city +
                ", dateIn: " + ((dateIn != null) ? dateIn.format(dateTimeFormatter) : "") +
                ", dateOut: " + ((dateOut != null) ? dateOut.format(dateTimeFormatter) : "");
    }
}
