package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Service implements Serializable {

    private static final long serialVersionUID = 4040494960958624247L;

    private Long id = -1L;
    private String serviceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return id.equals(service.id) &&
                Objects.equals(serviceName, service.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", serviceName: " + serviceName;
    }
}

