package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class ShipService implements Serializable {

    private static final long serialVersionUID = 7261858530548175827L;

    private int id;
    private int shipId;
    private int payable;
    private int serviceId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }


    public int getPayable() {
        return payable;
    }

    public void setPayable(int payable) {
        this.payable = payable;
    }


    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipService that = (ShipService) o;
        return id == that.id &&
                shipId == that.shipId &&
                payable == that.payable &&
                serviceId == that.serviceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipId, payable, serviceId);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", shipId: ").append(shipId)
                .append(", payable: ").append(payable)
                .append(", serviceId: ").append(serviceId)
                .toString();
    }

}
