package ua.study.poject.cruise.entity.printableentity;

import java.io.Serializable;
import java.util.Objects;

public class PrintableCruise implements Serializable {

    private static final long serialVersionUID = 9165387653914291754L;

    private Long cruiseId;
    private String shipName;
    private String country;
    private String city;
    private String dateIn;
    private String dateOut;

    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintableCruise that = (PrintableCruise) o;
        return cruiseId.equals(that.cruiseId) &&
                Objects.equals(shipName, that.shipName) &&
                Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(dateOut, that.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cruiseId, shipName, country, city, dateIn, dateOut);
    }

    @Override
    public String toString() {
        return "CruiseId: " + cruiseId +
                "shipName: " + shipName +
                ", country: " + country +
                ", city: " + city +
                ", dateIn: " + dateIn +
                ", dateOut: " + dateOut;
    }
}
