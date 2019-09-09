package ua.study.poject.cruise.entity.printableentity;

import java.io.Serializable;
import java.util.Objects;

public class PrintableServiceOnShip implements Serializable {

    private static final long serialVersionUID = -5159349681961939291L;

    private Long id; // ship_service_id
    private Long serviceId;
    private String serviceName;
    private Integer payable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getPayable() {
        return payable;
    }

    public void setPayable(Integer payable) {
        this.payable = payable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintableServiceOnShip that = (PrintableServiceOnShip) o;
        return Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(serviceName, that.serviceName) &&
                Objects.equals(payable, that.payable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName, payable);
    }

    @Override
    public String toString() {
        return "id: " + id +
                "serviceId: " + serviceId +
                "serviceName: " + serviceName +
                ", payable: " + payable;
    }
}
