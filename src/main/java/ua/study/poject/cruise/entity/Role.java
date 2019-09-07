package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;


public class Role implements Serializable {

    private static final long serialVersionUID = -4854783357421218496L;

    private Long id = -1L;
    private String role;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_MANAGER = "manager";
    public static final String ROLE_CUSTOMER = "customer";
//
//    public Role(){}
//
//    public Role(Long id, String role) {
//        this.id = id;
//        this.role = role;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id.equals(role1.id) &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", role: " + role;
    }
}
