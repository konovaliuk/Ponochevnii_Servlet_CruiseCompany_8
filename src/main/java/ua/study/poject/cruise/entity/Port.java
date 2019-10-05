package ua.study.poject.cruise.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is an entity that corresponds to a row in the "port" table
 */
public class Port implements Serializable {

    private static final long serialVersionUID = -1748260164937407845L;
    private Long id = -1L;
    private String country;
    private String city;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Port port = (Port) o;
        return id.equals(port.id) &&
                Objects.equals(country, port.country) &&
                Objects.equals(city, port.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", country: " + country +
                ", city: " + city;
    }

}
