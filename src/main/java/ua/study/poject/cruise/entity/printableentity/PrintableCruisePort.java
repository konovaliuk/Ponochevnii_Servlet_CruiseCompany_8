package ua.study.poject.cruise.entity.printableentity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * PrintableCruisePort is entity which is designed to transmit information about the port to the JSP page
 */
public class PrintableCruisePort implements Serializable {

    private static final long serialVersionUID = -6195210574546484432L;

    private Long cruisePortsId;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;
    private Long portId;
    private String country;
    private String city;

    public Long getCruisePortsId() {
        return cruisePortsId;
    }

    public void setCruisePortsId(Long cruisePortsId) {
        this.cruisePortsId = cruisePortsId;
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "CruisePortsId: " + cruisePortsId +
                ", dateIn: " + ((dateIn != null) ? dateIn.format(dateTimeFormatter) : "") +
                ", dateOut: " + ((dateOut != null) ? dateOut.format(dateTimeFormatter) : "") +
                ", PortId: " + portId +
                ", country: " + country +
                ", city: " + city;
    }
}

