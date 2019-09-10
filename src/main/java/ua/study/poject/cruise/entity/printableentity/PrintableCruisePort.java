package ua.study.poject.cruise.entity.printableentity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class PrintableCruisePort implements Serializable {

    private static final long serialVersionUID = -4841436168403509142L;

    private Long cruisePortsId;
    private Date dateIn;
    private Date dateOut;
    private Long portId;
    private String country;
    private String city;

    public Long getCruisePortsId() {
        return cruisePortsId;
    }

    public void setCruisePortsId(Long cruisePortsId) {
        this.cruisePortsId = cruisePortsId;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintableCruisePort that = (PrintableCruisePort) o;
        return Objects.equals(cruisePortsId, that.cruisePortsId) &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(dateOut, that.dateOut) &&
                Objects.equals(portId, that.portId) &&
                Objects.equals(country, that.country) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cruisePortsId, dateIn, dateOut, portId, country, city);
    }

    @Override
    public String toString() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return "CruisePortsId: " + cruisePortsId +
                ", dateIn: " + ((dateIn != null) ? simpleDateFormat.format(dateIn) : "") +
                ", dateOut: " + ((dateOut != null) ? simpleDateFormat.format(dateOut) : "") +
                ", PortId: " + portId +
                ", country: " + country +
                ", city: " + city;
    }
}
