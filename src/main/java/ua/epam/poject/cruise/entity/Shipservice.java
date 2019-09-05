package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Shipservice implements Serializable {

    private static final long serialVersionUID = -1089820551304483447L;

    private Long id = -1L;
    private Long shipId;
    private Integer payable;
    private Long serviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }


    public Integer getPayable() {
        return payable;
    }

    public void setPayable(Integer payable) {
        this.payable = payable;
    }


    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipservice that = (Shipservice) o;
        return id.equals(that.id) &&
                shipId.equals(that.shipId) &&
                payable.equals(that.payable) &&
                serviceId.equals(that.serviceId);
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
