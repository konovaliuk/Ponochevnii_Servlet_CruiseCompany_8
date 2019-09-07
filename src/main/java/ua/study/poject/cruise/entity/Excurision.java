package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Excurision implements Serializable {

    private static final long serialVersionUID = 1982287818678270014L;

    private Long id = -1L;
    private String excursionName;
    private double price;
    private String description;
    private Long portId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getExcursionName() {
        return excursionName;
    }

    public void setExcursionName(String excursionName) {
        this.excursionName = excursionName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Excurision that = (Excurision) o;
        return id.equals(that.id) &&
                Double.compare(that.price, price) == 0 &&
                portId.equals(that.portId) &&
                Objects.equals(excursionName, that.excursionName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, excursionName, price, description, portId);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", excursionName: " + excursionName +
                ", price: " + price +
                ", description: " + description +
                ", portId: " + portId;
    }

}
