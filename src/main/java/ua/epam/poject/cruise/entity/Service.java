package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Service implements Serializable {

    private static final long serialVersionUID = -1344770980255103859L;

    private int id;
    private String serviceName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id &&
                Objects.equals(serviceName, service.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", serviceName: ").append(serviceName)
                .toString();
    }
}

