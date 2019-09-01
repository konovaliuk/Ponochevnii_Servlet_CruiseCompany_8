package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Excurision implements Serializable {

    private static final long serialVersionUID = 5579012676943126634L;

    private int id;
    private String excursionName;
    private double price;
    private String description;
    private int portId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excurision that = (Excurision) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                portId == that.portId &&
                Objects.equals(excursionName, that.excursionName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, excursionName, price, description, portId);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", excursionName: ").append(excursionName)
                .append(", price: ").append(price)
                .append(", description: ").append(description)
                .append(", portId: ").append(portId)
                .toString();
    }

}
